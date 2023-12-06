package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT a from User a WHERE a.username=:username")
//    @Query("SELECT u from User u where u.email=:email")
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
