package bluma.africa.blumaafrica.service;


import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.PostRepository;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminResponse;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.BlumaException;
;
import bluma.africa.blumaafrica.mapper.Mapper;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlumaAdminService implements AdminService {

    private final PostRepository postRepository;
    private final Validate validate;





    @Override
    public LoginAsAdminResponse logInAsAdmin(LoginAsAdminRequest request) throws BlumaException {
        boolean response = validate.validateAdminDetails(request);
        if (response) return new LoginAsAdminResponse(request.getEmail());
        throw  new BlumaException("incorrect details");
    }



    @Override
    public PostResponse post(PostRequest postRequest) throws BlumaException {
        Post post = Mapper.map(postRequest);
        Post savedPost  = postRepository.save(post);
        System.out.println("saved post ==> "+savedPost);
        System.out.println("time posted ==> " + savedPost.getCreatedAt());
        return convertToResponse(savedPost);
    }


    private PostResponse convertToResponse(Post post){
        PostResponse response = new PostResponse();
        response.setPostId(post.getId());
        response.setTimePosted(post.getCreatedAt());
        response.setPostOwnerId(post.getPostOwnerId());
        System.out.println("response created ==> "+ response);
        System.out.println("time posted "+response.getTimePosted());
        return response;
    }



}
