package bluma.africa.blumaafrica.mapper;


import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.dtos.requests.CreateCarnivalFestivalRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import bluma.africa.blumaafrica.dtos.responses.CarnivalMapperResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateShareResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;

import java.time.LocalDateTime;
import java.util.List;

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


    public static Likes map(LikeRequest likeRequest, Authority authority) {
        Likes likes = new Likes();
        likes.setUserAUTHORITY(authority);
        likes.setUserId(Long.parseLong(likeRequest.getUserId()));
        likes.setPostId(Long.valueOf(likeRequest.getPostId()));
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

    public static CarnivalMapperResponse map(CreateCarnivalFestivalRequest request) {
        Carnival_Festival carnivalFestival = new Carnival_Festival();
        Address address = getAddress(request);
        carnivalFestival.setName(request.getName());
        carnivalFestival.setAbout(request.getAbout());
//        carnivalFestival.setFilesUrl(request.getFilesUrl());
        return new CarnivalMapperResponse(carnivalFestival, address);
    }

    private static Address getAddress(CreateCarnivalFestivalRequest request) {
        Address address = new Address();
        address.setCity(request.getCity());
        address.setTime(request.getTime());
        address.setDay(request.getDay());
        address.setState(request.getState());
        address.setStreet(request.getStreet());
        address.setYear(request.getYear());
        address.setMonth(request.getMonth());
        return address;
    }
}
