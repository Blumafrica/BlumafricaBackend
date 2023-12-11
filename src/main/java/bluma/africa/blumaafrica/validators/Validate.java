package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.repositories.AdminRepository;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.exceptions.BlumaException;
import bluma.africa.blumaafrica.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Validate {
    
    private final AdminRepository adminRepository;
    private final PostService postService;


    public  boolean validateAdminDetails(LoginAsAdminRequest request){
        Admin admin = adminRepository.findAdminById(1L);
        return admin.getPassword().equals(request.getPassword()) && admin.getEmail().equals(request.getEmail());
    }

    public Boolean validatePostDetails(PostRequest postRequest) throws BlumaException {
        Admin admin = adminRepository.findAdminById(1L);
        if (postRequest.getPosterId().equals(admin.getId().toString()) && postRequest.getAuthority().equals(Authority.ADMIN.toString()))
            return true;
        throw new BlumaException("incorrect credentials");
    }


}
