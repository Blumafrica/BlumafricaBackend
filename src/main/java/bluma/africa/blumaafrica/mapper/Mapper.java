package bluma.africa.blumaafrica.mapper;


import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.exceptions.BlumaException;

import java.time.LocalDateTime;

public class Mapper {
    public static Post map(PostRequest postRequest) throws BlumaException {
        Post post = new Post();
        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setPostOwnerId(postRequest.getPosterId());
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



}
