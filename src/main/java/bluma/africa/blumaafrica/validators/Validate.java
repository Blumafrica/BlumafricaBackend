package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.data.repositories.*;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.*;
import bluma.africa.blumaafrica.service.ValidateFindUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static bluma.africa.blumaafrica.data.models.Authority.ADMIN;
import static bluma.africa.blumaafrica.data.models.Authority.USER;

@Component
@AllArgsConstructor
public class Validate {
    
    private final AdminRepository adminRepository;
    private final PostRepository postService;
    private LikesRepository likesRepository;
    private ShareRepository shareRepository;
    private UserRepository userRepository;


    public  boolean validateAdminDetails(LoginAsAdminRequest request){
        Admin admin = adminRepository.findAdminById(1L);
        return admin.getPassword().equals(request.getPassword()) && admin.getEmail().equals(request.getEmail());
    }

    public Boolean validatePostDetails(PostRequest postRequest) throws BlumaException {
        Admin admin = adminRepository.findAdminById(1L);
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
        Admin admin = adminRepository.findAdminById(1L);
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

    public Authority validateAuthority(String authority) throws AuthorityException {
        if (USER.name().equalsIgnoreCase(authority) || Authority.ADMIN.name().equalsIgnoreCase(authority))
            if (Authority.ADMIN.name().equalsIgnoreCase(authority))
                return Authority.ADMIN;
            if (USER.name().equalsIgnoreCase(authority))
                return USER;
        throw new AuthorityException("unknown authority");
    }

    public ValidateLikeResponse validateLikeRequestOnShare(LikeRequest request) throws BlumaException {
        Share share = checkIfShareExit(request);
        ValidateLikeResponse response = new ValidateLikeResponse();
        if (USER.name().equalsIgnoreCase(request.getAuthority())) {
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
        Share share = shareRepository.findShareById(Long.valueOf(request.getShareId()));
        if (share != null)
            return share;
        throw new BlumaException("share not found");
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

    public void checkIfUserHasLikeShare(LikeRequest request, String shareId) throws LikeException {
        Share share = shareRepository.findShareById(Long.valueOf(shareId));
        List<Likes> foundLikes = likesRepository.findAll();
        List<Likes> likesList = foundLikes.stream()
                .filter((x) -> Objects.equals(x.getShareId(), Long.valueOf(shareId)))
                .toList();

        Optional<Likes> foundLike = likesList.stream()
                .filter((x) -> x.getUserId().equals(Long.valueOf(request.getUserId())))
                .findAny();

        if (foundLike.isPresent())
            throw new LikeException("user already like");
    }
    public Likes getUserLikeOnShare(LikeRequest request, String shareId){

        List<Likes> foundLikes = likesRepository.findAll();
        List<Likes> likesList = foundLikes.stream()
                .filter((x) -> Objects.equals(x.getShareId(), Long.valueOf(shareId)))
                .toList();

        Optional<Likes> foundLike = likesList.stream()
                .filter((x) -> x.getUserId().equals(Long.valueOf(request.getUserId())))
                .findAny();
        System.out.println("found  like at the repository ===> "+ foundLike);
        return foundLike.orElse(null);
    }
    public ValidateCommentResponse validateCommentOnPostRequest(CreateCommentRequest createCommentRequest) throws AuthorityException, PostNotFound, UserNotFound {
       Post post =  postService.getPostById(Long.valueOf(createCommentRequest.getPostId()));
       Authority userAuthority = validateAuthority(createCommentRequest.getCommenterAuthority());
       if (post != null){
           if (userAuthority.name().equalsIgnoreCase("user"))
               return mapToValidateCommentResponseUser(createCommentRequest, post, userAuthority);
       }throw new PostNotFound("post with id "+ createCommentRequest.getPostId() + "does not exist");
    }

    private ValidateCommentResponse mapToValidateCommentResponseUser(CreateCommentRequest createCommentRequest, Post post, Authority userAuthority) throws UserNotFound {
        Optional<User> user = Optional.ofNullable(userRepository.findById(Long.valueOf(createCommentRequest.getCommenterId()))
                .orElseThrow(() -> new UserNotFound("user does not exist")));
        ValidateCommentResponse response = new ValidateCommentResponse();
        response.setUser(user.get());
        response.setPost(post);
        response.setAuthority(userAuthority);
        return response;
    }


    public void validateCommentOnShare(CreateCommentRequest createCommentRequest) throws ShareException, AuthorityException {
        Share share = shareRepository.findShareById(Long.valueOf(createCommentRequest.getPostId()));
        Authority authority = validateAuthority(createCommentRequest.getCommenterAuthority());
        if (share != null) {
            if (authority == Authority.ADMIN){
                adminRepository.findAdminById(1L);

            }
        }throw new ShareException("shared post with id " + createCommentRequest.getPostId() + " does not exist");
    }


    public User userLoginRequest(LoginRequest request) throws UserNotFound {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
             return user.get();
        }else {throw new UserNotFound("user   email %s does not exist ");}

    }
}
