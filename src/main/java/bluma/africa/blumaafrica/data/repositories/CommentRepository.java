package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
