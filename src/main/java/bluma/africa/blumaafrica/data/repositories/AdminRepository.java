package bluma.africa.blumaafrica.data.repositories;

import bluma.africa.blumaafrica.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminById(Long id);
}
