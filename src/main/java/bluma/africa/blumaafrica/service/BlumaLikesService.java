package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlumaLikesService implements LikesService{


    private final LikesRepository likesRepository;
    private final AdminService adminService;
    private final PostService postService;
    @Override
    public LikeResponse userCanLikePost(LikeRequest likeRequest) throws BlumaException {
        var response = getUserAuthority(likeRequest.getAuthority());
        if (response.equalsIgnoreCase("user") || response.equalsIgnoreCase("Admin")) {
            System.out.println(response);
            Post post = postService.getPostById(likeRequest.getPostId());
            Likes like = Mapper.map(likeRequest, response);
            Likes savedLike = likesRepository.save(like);
            post.setListOfLikeIds(List.of(savedLike.getId()));
            System.out.println(post.getListOfLikeIds());
            System.out.println(like);
            return new LikeResponse(savedLike.getId().toString(), likeRequest.getPostId());
        }
        throw new BlumaException("unknown authority");

    }

    @Override
    public String unlikePost(UnlikeRequest unlikeRequest) throws PostNotFound {
        Likes likes = likesRepository.findLikesById(Long.valueOf(unlikeRequest.getLikeId()));
        Post foundPost = adminService.findPostById(Long.parseLong(unlikeRequest.getPostId()));
        if (likes != null)
            if (validateRequest(likes,unlikeRequest ))
                unlike(foundPost, likes.getId());
        System.out.println(foundPost);
        return "successful";
    }

    private void unlike(Post foundPost, Long likeId) {
        if (foundPost.getListOfLikeIds().contains(likeId))
            foundPost.getListOfLikeIds().remove(likeId);

    }

    private boolean validateRequest(Likes like,UnlikeRequest unlikeRequest ){
        if (like.getPostId().toString().equals(unlikeRequest.getPostId()) && like.getUserId().toString().equals(unlikeRequest.getUserId()))
            return true;
        return false;
    }
    private String getUserAuthority(String authority) throws BlumaException {
        if (Authority.USER.name().equalsIgnoreCase(authority))
            return "user";
        if (Authority.ADMIN.name().equalsIgnoreCase(authority))
            return "admin";
        throw new BlumaException("unknown authority");

     }
}
