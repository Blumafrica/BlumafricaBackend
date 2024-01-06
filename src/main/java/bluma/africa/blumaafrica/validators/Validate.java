package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.data.repositories.AdminRepository;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.data.repositories.PostRepository;
import bluma.africa.blumaafrica.data.repositories.ShareRepository;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.ValidateEditShareResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateLikeResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateShareResponse;
import bluma.africa.blumaafrica.exceptions.*;
import bluma.africa.blumaafrica.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class Validate {
    
    private final AdminRepository adminService;
    private final PostRepository postService;
    private LikesRepository likesRepository;
    private ShareRepository shareRepository;


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
            return  createValidateLikeResponse(true, foundPost, foundPost.getPostOwnerId());
        throw new BlumaException("invalid like request");


    }

    private ValidateLikeResponse validateAdminLikeRequest(LikeRequest likeRequest) throws BlumaException {
        Admin admin = adminService.findAdminById(1L);
        Post foundPost = postService.getPostById(Long.valueOf(likeRequest.getPostId()));
        System.out.println("at validatingLike request  foundPost ==> " + foundPost);
        if (likeRequest.getUserId().equals(admin.getId().toString()) && foundPost != null )
            return createValidateLikeResponse(true, foundPost, foundPost.getPostOwnerId());
        throw new BlumaException("invalid like request");
    }

    private ValidateLikeResponse createValidateLikeResponse(boolean b, Post foundPost, Long postOwnerId) {
        ValidateLikeResponse response = new ValidateLikeResponse();
        response.setValidate(true);
        response.setFoundPost(foundPost);
        response.setUserId(postOwnerId);
        return response;
    }
    public Likes checkIfUserHasLikePost(LikeRequest likeRequest) {
        List<Likes> likes = likesRepository.findAll();
        List<Likes> postLikes = likes.stream()
                .filter((x) -> Objects.equals(x.getPostId(), Long.valueOf(likeRequest.getPostId())))
                .toList();

        Optional<Likes> foundLike = postLikes.stream()
                .filter((x)-> Objects.equals(x.getUserId(), Long.valueOf(likeRequest.getPostId())))
                .findAny();
        if (foundLike.isPresent())
            return foundLike.get();
        return null;
    }

    public void checkIfUserHasLike(LikeRequest likeRequest) throws LikeException {
        List<Likes> likes = likesRepository.findAll();
        List<Likes> postLikes = likes.stream()
                .filter((x) -> Objects.equals(x.getPostId(), Long.valueOf(likeRequest.getPostId())))
                .toList();

        Optional<Likes> foundLike = postLikes.stream()
                .filter((x)-> Objects.equals(x.getUserId(), Long.valueOf(likeRequest.getPostId())))
                .findAny();
        if (foundLike.isPresent())
            throw new LikeException("user already like this post");
    }

    public ValidateShareResponse validateShareRequest(ShareRequest request) throws PostNotFound, AuthorityException {
       Post foundPost = postService.getPostById(Long.valueOf(request.getPostId()));
       if (foundPost == null)
           throw new PostNotFound("Post not found");
       Authority authority = validateAuthority(request.getAuthority());
       return new ValidateShareResponse(foundPost, authority);
    }

    private Authority validateAuthority(String authority) throws AuthorityException {
        if (Authority.USER.name().equalsIgnoreCase(authority) || Authority.ADMIN.name().equalsIgnoreCase(authority))
            if (Authority.ADMIN.name().equalsIgnoreCase(authority))
                return Authority.ADMIN;
            if (Authority.USER.name().equalsIgnoreCase(authority))
                return Authority.USER;
        throw new AuthorityException("unknown authority");
    }

    public ValidateLikeResponse validateLikeRequestOnShare(LikeRequest request) throws BlumaException {
        Share share = checkIfShareExit(request);
        ValidateLikeResponse response = new ValidateLikeResponse();
        if (Authority.USER.name().equalsIgnoreCase(request.getAuthority())) {
            response.setValidate(true);
            response.setFoundShare(share);
            response.setUserId(share.getShareOwnerId());
            return response;
        } else if (Authority.ADMIN.name().equalsIgnoreCase(request.getAuthority()))
            response.setValidate(true);
            response.setFoundShare(share);
            response.setUserId(share.getShareOwnerId());
            return response;

    }

    private Share checkIfShareExit(LikeRequest request) throws BlumaException {
        Share share = shareRepository.findShareById(Long.valueOf(request.getPostId()));
        if (share != null)
            return share;
        throw new BlumaException("share not found");
    }

    public void validateCarnivalAndFestivalRequest(CreateCarnivalFestivalRequest request) throws AuthorityException {
        Authority authority = validateAuthority(request.getAuthority());
        if (authority != Authority.ADMIN)
            throw new AuthorityException("not authorize to post");
    }

    public ValidateEditShareResponse validateEditShare(EditShareRequest request) throws AuthorityException, ShareException {
        Share foundShare = shareRepository.findShareById(Long.valueOf(request.getPostId()));
        Authority authority = validateAuthority(request.getPosterAuthority());
        if (foundShare != null)
             if (foundShare.getShareOwnerId() == Long.valueOf(request.getPosterId()) && foundShare.getShareOwnerAuthority() == authority) {
                 return new ValidateEditShareResponse(foundShare);
             }else {  throw new ShareException("has not authority to edit post");}
        throw new ShareException("shared post  not found");
    }

    public void checkIfUserHasLikeShare(LikeRequest request, String shareId) {
        Share share = shareRepository.findShareById(Long.valueOf(shareId));

    }
}
