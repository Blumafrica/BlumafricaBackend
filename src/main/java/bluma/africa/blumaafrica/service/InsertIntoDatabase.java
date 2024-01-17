package bluma.africa.blumaafrica.service;
import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class InsertIntoDatabase {

    private final AdminRepository repository;

    @PostConstruct
    public void createAdmin() {
        Admin admin = new Admin();
        admin.setAuthority(List.of(Authority.ADMIN));
        admin.setId(1L);
        admin.setEmail("mariiam22222@gmail.com");
        admin.setPassword("Mariam@21");
        repository.save(admin);

    }
}