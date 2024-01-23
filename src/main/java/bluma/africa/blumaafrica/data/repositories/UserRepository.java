package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    User findUserById(Long id);

    User findByEmailAndPassword(String email, String password);
}
