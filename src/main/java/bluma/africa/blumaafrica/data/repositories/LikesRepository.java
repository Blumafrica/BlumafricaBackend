package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Likes;
import bluma.africa.blumaafrica.dtos.responses.GetAllPostLikesResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Likes findLikesById(Long aLong);
    List<Likes> findLikesByPostId(Long postId);
}
