package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

@Service
public interface LikesService {

    LikeResponse userCanLikePost(LikeRequest likeRequest) throws UserNotFound, PostNotFound;
}
