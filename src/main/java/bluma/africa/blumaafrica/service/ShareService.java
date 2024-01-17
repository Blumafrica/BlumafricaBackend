package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Share;
import bluma.africa.blumaafrica.dtos.requests.EditShareRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeRequest;
import bluma.africa.blumaafrica.dtos.requests.LikeShareRequest;
import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.dtos.responses.ShareResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.ShareException;
import org.springframework.stereotype.Service;

public interface ShareService {

    ShareResponse share(ShareRequest request) throws PostNotFound, AuthorityException;

    LikeResponse likeSharedPost(LikeShareRequest request) throws BlumaException;
    Share findShareById(Long id) throws ShareException;

    Share save(Share foundShare);

    Long editShare(EditShareRequest request) throws ShareException, AuthorityException;
}
