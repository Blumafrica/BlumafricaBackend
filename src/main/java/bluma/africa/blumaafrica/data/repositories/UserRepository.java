package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT a from User a WHERE a.username=:username")
    Optional<User> findByUsername(String username);
}
