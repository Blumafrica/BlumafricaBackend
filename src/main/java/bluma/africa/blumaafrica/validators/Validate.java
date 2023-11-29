package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import org.springframework.stereotype.Component;

@Component

public class Validate {

    private static Admin admin;

    public Validate(Admin admin) {
        Validate.admin = admin;
    }


    public static boolean validateAdminDetails(LoginAsAdminRequest request){
        System.out.println("on validate"+admin);
        return request.getPassword().equals(admin.getEmail()) && request.getEmail().equals(admin.getEmail());
    }
}
