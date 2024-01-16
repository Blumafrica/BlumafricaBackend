package bluma.africa.blumaafrica.mapper;


import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import bluma.africa.blumaafrica.dtos.responses.ValidateCommentResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateShareResponse;
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


    public static  Likes map(LikeRequest likeRequest, String post, Authority authority) {
        Likes likes = new Likes();
        likes.setUserAUTHORITY(authority);
        likes.setUserId(Long.parseLong(likeRequest.getUserId()));
        likes.setShareId(Long.valueOf(post));
        likes.setLiked(true);
      return likes;
    }

    public static Share map(ShareRequest request, ValidateShareResponse response) {
        Share share = new Share();
        share.setDescription(request.getTitle());
        share.setPost(response.getPost());
        share.setCreatedAt(LocalDateTime.now());
        share.setContent(request.getContent());
        share.setShareOwnerId(Long.valueOf(request.getSharerId()));
        share.setShareOwnerAuthority(response.getAuthority());
        return share;
    }


    public static Comment map( ValidateCommentResponse response) {
        Comment comment =new Comment();
        comment.setCommenterId(response.getUser().getId());
        comment.setUserAuthority(response.getAuthority());
        comment.setPostId(response.getPost());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setCommenterId(response.getUser().getId());
        return comment;
    }
}
