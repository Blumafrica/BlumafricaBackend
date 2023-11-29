package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
