package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Share;
import bluma.africa.blumaafrica.data.repositories.ShareRepository;
import bluma.africa.blumaafrica.dtos.requests.LikeShareRequest;
import bluma.africa.blumaafrica.dtos.requests.ShareRequest;
import bluma.africa.blumaafrica.dtos.responses.LikeResponse;
import bluma.africa.blumaafrica.dtos.responses.ShareResponse;
import bluma.africa.blumaafrica.dtos.responses.ValidateShareResponse;
import bluma.africa.blumaafrica.exceptions.AuthorityException;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.ShareException;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlumaShareService implements ShareService{

    private Validate validate;
    private final ShareRepository repository;

    @Override
    public ShareResponse  share(ShareRequest request) throws PostNotFound, AuthorityException {
        ValidateShareResponse response = validate.validateShareRequest(request);
        Share share  = Mapper.map(request , response);
        Share savedShare =  repository.save(share);
        return new ShareResponse(savedShare.getId());
    }

    @Override
    public LikeResponse likeSharedPost(LikeShareRequest request) throws BlumaException {
        validate.validateLikeRequestOnShare(request);
        return null;
    }

    @Override
    public Share findShareById(Long id) throws ShareException {
        return repository.findById(id).orElseThrow(()-> new ShareException("Share not found"));
    }

    @Override
    public Share save(Share foundShare) {
        return repository.save(foundShare);
    }


}
