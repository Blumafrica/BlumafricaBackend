package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import org.springframework.stereotype.Service;

@Service
public interface LikesService {

    Likes userCanLikePost(LikeRequest likeRequest);
}
