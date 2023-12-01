package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT a from User a WHERE a.username=:username")
    Optional<User> findByUsername(String username);
}
