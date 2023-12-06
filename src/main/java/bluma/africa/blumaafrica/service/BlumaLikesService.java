package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlumaLikesService implements LikesService{


    private final LikesRepository likesRepository;
    private final UserService userService;
    private final PostService postService;
    @Override
    public LikeResponse userCanLikePost(LikeRequest likeRequest) throws UserNotFound, PostNotFound {
        var User = userService.getUserById(Long.valueOf(likeRequest.getUserId()));
        var post = postService.getPostById(likeRequest.getPostId());
        Likes like = Mapper.map(likeRequest);
        Likes savedLike = likesRepository.save(like);
        post.setListOfLikeIds(List.of(savedLike.getId()));
        return new LikeResponse(savedLike.getId().toString(), likeRequest.getPostId());
    }
}
