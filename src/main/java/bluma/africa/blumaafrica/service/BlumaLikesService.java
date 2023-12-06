package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.repositories.LikesRepository;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlumaLikesService implements LikesService{


    private final LikesRepository likesRepository;
    @Override
    public Likes userCanLikePost(LikeRequest likeRequest) {
        Likes like = Mapper.map(likeRequest);
        Likes savedLike = likesRepository.save(like);
        return savedLike;
    }
}
