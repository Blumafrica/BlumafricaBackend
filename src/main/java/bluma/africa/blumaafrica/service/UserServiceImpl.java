package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.UserRepository;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createUser(UserRequest request) {
        User user = new User();

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);

        var savedUser = repository.save(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setMessage("Successfully created");
        return response;
    }
}
