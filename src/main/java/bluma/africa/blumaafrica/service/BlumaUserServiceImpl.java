package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.data.models.*;
import bluma.africa.blumaafrica.data.repositories.UserRepository;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.*;
import bluma.africa.blumaafrica.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j


public class BlumaUserServiceImpl implements UserService{
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;


    @Override
    public UserResponse createUser(UserRequest request) throws UserAlreadyExist {
        boolean isUserExist = userRepository.findByUsername(request.getUsername()).isPresent();
        if (isUserExist) throw new UserAlreadyExist("user already exist");
        User user = new User();

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setAuthorities(List.of(Authority.USER));
        var savedUser = userRepository.save(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setMessage("Successfully created");
        return response;
    }

    @Override
    public User getUserBy(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(
                        String.format("user with %s is not found",email)));
    }

    @Override
    public User getUserById(Long id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound("user not found"));
    }

    @Override

    public ProfileResponse setProfile(ProfileRequest profileRequest) throws UserNotFound {
        var getUser = getUserById(profileRequest.getUserId());
        Profile userProfile =mapper.map(profileRequest,Profile.class);
        userProfile.setUserId(getUser.getId());
        getUser.setProfile(userProfile);
         userRepository.save(getUser);
        return new ProfileResponse();
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) throws UserNotFound {
        var getUser = getUserById(profileRequest.getUserId());
        return null;

        }

}
