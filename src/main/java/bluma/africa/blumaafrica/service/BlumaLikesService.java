package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.dtos.requests.GetAllPostLikesRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.GetAllPostLikesResponse;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateLikeResponse;
import bluma.africa.blumaafrica.exceptions.*;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BlumaLikesService implements LikesService{


    private final LikesRepository likesRepository;
    private final Validate validate;
    private final PostService postService;
    private final ShareService service;

    @Override
    public LikeResponse userCanLikePost(LikeRequest likeRequest) throws BlumaException {
        Authority authority = getUserAuthority(likeRequest.getAuthority());
        ValidateLikeResponse response = validate.validateLikeRequest(likeRequest, authority);
        checkIfUserHasLikePost(likeRequest);
        if (response.isValidate()) {
            Post post = response.getFoundPost();
            Likes createdLike = Mapper.map(likeRequest,likeRequest.getPostId(), authority);
            Likes savedLike = likesRepository.save(createdLike);
            System.out.println(savedLike);
            return new  LikeResponse(savedLike.getId().toString(), post.getId().toString());
        }else {throw new BlumaException("invalid like request");}
    }



    private Authority getUserAuthority(String authority) throws UnknownAuthority {
        if (authority.equalsIgnoreCase(Authority.ADMIN.name()))
            return Authority.ADMIN;
        if (authority.equalsIgnoreCase(Authority.USER.name()))
            return Authority.USER;
        throw  new UnknownAuthority("Unknown authority");
    }

    @Override
    public String unlikePost(UnlikeRequest unlikeRequest) throws PostNotFound, LikeException {
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setPostId(unlikeRequest.getPostId());
        likeRequest.setAuthority("admin");
        likeRequest.setUserId(unlikeRequest.getUserId());
        likeRequest.setPostId(unlikeRequest.getPostId());
        Likes response = validate.checkIfUserHasLikePost(likeRequest);
        if (response != null)
          return removeLike(response.getId());
        throw new LikeException("user has not like yet");
    }

    private String removeLike( Long  likeId) {
        likesRepository.deleteById(likeId);
        return  "unlike";
    }

    @Override
    public void checkIfUserHasLikePost(LikeRequest likeRequest) throws LikeException, PostNotFound {
        validate.checkIfUserHasLike(likeRequest);
    }

    @Override
    public Likes findLikesById(Long id) {
        return likesRepository.findLikesById(id);
    }

    @Override
    public LikeResponse likeSharedPost(LikeRequest request) throws BlumaException {
        ValidateLikeResponse response = validate.validateLikeRequestOnShare(request);
        if (response.isValidate()) {
            validate.checkIfUserHasLikeShare(request, request.getShareId());
            Likes like = Mapper.map(request,request.getShareId() , response.getFoundShare().getShareOwnerAuthority());
            Likes savedLike = likesRepository.save(like);
            return new LikeResponse(savedLike.getId().toString(), "");
        }else {throw new ShareException("error occurs while share");}
    }

    @Override
    public GetAllPostLikesResponse getAllPostLikes(GetAllPostLikesRequest request) {
      List<Likes> foundLikes = likesRepository.findLikesByPostId(Long.valueOf(request.getPostId()));
      return new GetAllPostLikesResponse(foundLikes);
    }
}
