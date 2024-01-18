package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.requests.GetAllPostLikesRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.UnlikeRequest;
import bluma.africa.blumaafrica.dtos.responses.GetAllPostLikesResponse;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.LikeException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import org.springframework.stereotype.Service;


public interface LikesService {

    LikeResponse userCanLikePost(LikeRequest likeRequest) throws BlumaException;

    String unlikePost(UnlikeRequest unlikeRequest) throws PostNotFound, LikeException;
    void  checkIfUserHasLikePost(LikeRequest likeRequest) throws LikeException, PostNotFound;
    Likes findLikesById(Long id);

    LikeResponse likeSharedPost(LikeRequest request) throws BlumaException;

    GetAllPostLikesResponse getAllPostLikes(GetAllPostLikesRequest request);

    void unLikeShare(UnlikeRequest unlikeRequest) throws PostNotFound, LikeException;
}
