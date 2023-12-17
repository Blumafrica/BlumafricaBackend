package bluma.africa.blumaafrica.mapper;


import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.exceptions.BlumaException;

import java.time.LocalDateTime;

public class Mapper {
    public static Post map(PostRequest postRequest) throws BlumaException {
        Post post = new Post();
        post.setContent(postRequest.getContent());
        post.setDescription(postRequest.getDescription());
        post.setPostOwnerId(Long.valueOf(postRequest.getPosterId()));
        post.setCreatedAt(LocalDateTime.now());
        setAuthority(postRequest, post);
        post.setFileUrl(postRequest.getFileUrl());
        return post;
    }

    private static void setAuthority(PostRequest postRequest, Post post) throws BlumaException {
        if(Authority.ADMIN.name().equalsIgnoreCase(postRequest.getAuthority())) post.setPostOwnerAuthority(Authority.ADMIN);
        else if (Authority.USER.name().equalsIgnoreCase(postRequest.getAuthority())) post.setPostOwnerAuthority(Authority.USER);
        else throw new BlumaException("unknown authority");
    }


    public static Likes map(LikeRequest likeRequest, String authority) {
        Likes likes = new Likes();
        if (authority.equalsIgnoreCase("user"))
           likes.setUserAUTHORITY(Authority.USER);
        if (authority.equalsIgnoreCase("admin"))
            likes.setUserAUTHORITY(Authority.ADMIN);
        likes.setUserId(Long.parseLong(likeRequest.getUserId()));
        likes.setPostId(Long.valueOf(likeRequest.getPostId()));
      return likes;
    }
}
