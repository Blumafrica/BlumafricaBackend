package bluma.africa.blumaafrica.mapper;


import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;

import java.time.LocalDateTime;

public class Mapper {
    public static Post map(PostRequest postRequest) {
        Post post = new Post();
        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setPostOwnerId(postRequest.getPosterId());
        post.setCreatedAt(LocalDateTime.now());
        post.setPostOwnerAuthority(postRequest.getAuthority());
        post.setFileUrl(postRequest.getFileUrl());
        return post;
    }
}
