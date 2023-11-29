package bluma.africa.blumaafrica.mapper;


import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;

import java.time.LocalDateTime;

public class Mapper {
    public static Post map(PostRequest postRequest) {
        Post post = new Post();
        post.setContent(postRequest.getText());
        post.setDescription(postRequest.getDescription());
        post.setPostOwner(postRequest.getPostOwner());
        post.setCreatedAt(LocalDateTime.now());
        post.setFileUrl(postRequest.getFileUrl());
        return post;
    }
}
