package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.AdminRepository;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.ValidateLikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.LikeException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.service.AdminService;
import bluma.africa.blumaafrica.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Validate {
    
    private final AdminRepository adminService;
    private final PostService postService;
    private LikesRepository likesRepository;


    public  boolean validateAdminDetails(LoginAsAdminRequest request){
        Admin admin = adminService.findAdminById(1L);
        return admin.getPassword().equals(request.getPassword()) && admin.getEmail().equals(request.getEmail());
    }

    public Boolean validatePostDetails(PostRequest postRequest) throws BlumaException {
        Admin admin = adminService.findAdminById(1L);
        if (postRequest.getPosterId().equals(admin.getId().toString()) && postRequest.getAuthority().equals(Authority.ADMIN.toString()))
            return true;
        throw new BlumaException("incorrect credentials");
    }


    public ValidateLikeResponse validateLikeRequest(LikeRequest likeRequest, Authority authority) throws BlumaException {
        if (authority == Authority.ADMIN)
            return validateAdminLikeRequest(likeRequest);
        return  validateUserLikeRequest(likeRequest);
    }

    private ValidateLikeResponse validateUserLikeRequest(LikeRequest likeRequest) throws BlumaException {
        Post foundPost = postService.getPostById(Long.valueOf(likeRequest.getPostId()));
        System.out.println("at validatingLike request  foundPost ==> " + foundPost);
        if (foundPost.getPostOwnerId().equals(Long.valueOf(likeRequest.getUserId())) && foundPost != null )
            return  new ValidateLikeResponse(true, foundPost, foundPost.getPostOwnerId());
        throw new BlumaException("invalid like request");


    }

    private ValidateLikeResponse validateAdminLikeRequest(LikeRequest likeRequest) throws BlumaException {
        Admin admin = adminService.findAdminById(1L);
        Post foundPost = postService.getPostById(likeRequest.getPostId());
        System.out.println("at validatingLike request  foundPost ==> " + foundPost);
        if (likeRequest.getUserId().equals(admin.getId().toString()) && foundPost != null )
            return  new ValidateLikeResponse(true, foundPost, foundPost.getPostOwnerId());
        throw new BlumaException("invalid like request");
    }

    public void checkIfUserHasLike(LikeRequest likeRequest) throws LikeException, PostNotFound {
        Post post = postService.getPostById(likeRequest.getPostId());
        List<Long> likeIds = post.getListOfLikeIds();
        for (Long id: likeIds){
            if (likesRepository.findLikesById(id).getUserId().equals(Long.valueOf(likeRequest.getUserId())))
                throw new LikeException("user already like");

        }
//        post.getListOfLikeIds().stream()
//                .forEach(x -> adminService.findPostById(x))
//                .;
    }
}
