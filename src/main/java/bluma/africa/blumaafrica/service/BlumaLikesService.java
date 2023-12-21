package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateLikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.LikeException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UnknownAuthority;
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

    @Override
    public LikeResponse userCanLikePost(LikeRequest likeRequest) throws BlumaException {
        Authority authority = getUserAuthority(likeRequest.getAuthority());
        ValidateLikeResponse response = validate.validateLikeRequest(likeRequest, authority);
        checkIfUserHasLikePost(likeRequest);
        if (response.isValidate()) {
            Post post = response.getFoundPost();
            Likes createdLike = Mapper.map(likeRequest, authority);
            Likes savedLike = likesRepository.save(createdLike);
            post.setListOfLikeIds(List.of(savedLike.getId()));
            Post sa  = postService.save(post);
            System.out.println(" {} " + sa.getListOfLikeIds());
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
    public String unlikePost(UnlikeRequest unlikeRequest) throws PostNotFound {
        return null;
    }

    @Override
    public void checkIfUserHasLikePost(LikeRequest likeRequest) throws LikeException, PostNotFound {
        validate.checkIfUserHasLike(likeRequest);
    }

    @Override
    public Likes findLikesById(Long id) {
        return likesRepository.findLikesById(id);
    }
}
