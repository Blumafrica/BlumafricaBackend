package bluma.africa.blumaafrica.validators;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.dtos.requests.LoginAsAdminRequest;
import bluma.africa.blumaafrica.dtos.requests.PostRequest;
import bluma.africa.blumaafrica.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Validate {
    
    private final Admin admin;
    private final PostService postService;
//    private final

//    public Validate(Admin admin) {
//        this.admin = admin;
//    }

    public  boolean validateAdminDetails(LoginAsAdminRequest request){
        return admin.getPassword().equals(request.getPassword()) && admin.getEmail().equals(request.getEmail());
    }

    public void validatePostDetails(PostRequest postRequest) {
//        if (postRequest.getPosterId().equals(admin.getId()) && postRequest.getPostId().equals(postService.getPostById(postRequest.getPostId())) ){
//
//        }
    }

//    public void validatePostRequest(PostRequest postRequest) {
//       if (postRequest.getId() && postRequest.getText() && postRequest.getDescription() && postRequest.getAuthority() && )
//    }
}
