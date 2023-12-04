package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import bluma.africa.blumaafrica.data.repositories.PostRepository;
import bluma.africa.blumaafrica.dtos.responses.PostResponse;
import bluma.africa.blumaafrica.exceptions.PostNotFound;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlumaPostService implements PostService{
    private final PostRepository postRepository;


    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findByPostOwnerAuthority(Authority userAuthority) {
        return postRepository.findByPostOwnerAuthority(userAuthority);
    }

    @Override
    public PostResponse saveUserPost(Post post) throws UserNotFound {
        var savedPost = postRepository.save(post);

         PostResponse postResponse = new PostResponse();
         postResponse.setTimePosted(savedPost.getCreatedAt());
         postResponse.setPostId(savedPost.getId());
         postResponse.setPostOwnerId(savedPost.getPostOwnerId());
        return postResponse;
    }

    @Override
    public Post getPostById(Long id) throws PostNotFound {
            return postRepository.findById(id)
                    .orElseThrow(() -> new PostNotFound("Post not found with id: " + id));
        }

    @Override
    public PostResponse deletePostById(Long postId) throws PostNotFound {
        Post getPost = postRepository.findById(postId).
                 orElseThrow(() -> new PostNotFound("Post not found with id: " + postId));
        var extractId = getPost.getId();
        postRepository.deleteById(extractId);
        PostResponse response = new PostResponse();
        response.setMessage("Post Successfully deleted");
        return response;
    }

    @Override
    public List<Post> getUserPosts(String userId) {
        long convertId = Long.parseLong(userId);
       List<Post> foundPosts = findByPostOwnerAuthority(Authority.USER);
       return foundPosts.stream()
               .filter(x -> x.getPostOwnerId() == convertId)
               .toList();
    }


}



