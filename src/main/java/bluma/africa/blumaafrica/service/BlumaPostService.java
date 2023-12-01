package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.PostRepository;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlumaPostService implements PostService{
    private final PostRepository postRepository;


    @Override
    public PostResponse saveUserPost(Post post) throws UserNotFound {
        var savedPost = postRepository.save(post);

         PostResponse postResponse = new PostResponse();
         postResponse.setTimePosted(savedPost.getCreatedAt());
         postResponse.setPostId(savedPost.getId());
         postResponse.setPostOwnerId(savedPost.getPostOwnerId());
        return postResponse;
    }
}
