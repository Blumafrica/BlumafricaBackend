package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminById(Long id);
    Admin findAdminByEmail(String email);
    Optional<Admin> getAdminById(Long id);
}
