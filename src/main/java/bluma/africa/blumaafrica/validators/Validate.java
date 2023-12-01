package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class Validate {
    
    private final Admin admin;

    public Validate(Admin admin) {
        this.admin = admin;
    }

    public  boolean validateAdminDetails(LoginAsAdminRequest request){
        return admin.getPassword().equals(request.getPassword()) && admin.getEmail().equals(request.getEmail());
    }
}
