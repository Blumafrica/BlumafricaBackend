package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Share;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShareRepository extends JpaRepository<Share, Long> {
    Share findShareById(Long id);

    Optional<Share> findById(Long id);
}
