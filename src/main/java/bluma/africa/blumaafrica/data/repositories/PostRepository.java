package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
