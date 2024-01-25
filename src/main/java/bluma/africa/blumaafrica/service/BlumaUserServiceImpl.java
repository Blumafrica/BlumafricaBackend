package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.config.security.Service.JwtService;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Profile;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.UserRepository;
import bluma.africa.blumaafrica.dtos.requests.*;
import bluma.africa.blumaafrica.dtos.responses.LoginResponse;
import bluma.africa.blumaafrica.dtos.responses.ProfileResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.EmailException;
import bluma.africa.blumaafrica.exceptions.IncorrectCredentials;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import bluma.africa.blumaafrica.validators.Validate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static bluma.africa.blumaafrica.mapper.Mapper.introductionMessage;

@Service
@AllArgsConstructor
@Slf4j
public class BlumaUserServiceImpl implements UserService {
    private  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private MailService mailService;


    private Validate validate;

    private final ModelMapper mapper;




    @Override
    public UserResponse createUser(UserRequest request) throws UserAlreadyExist,EmailException {
        boolean isUserExist = userRepository.findByUsername(request.getUsername()).isPresent();
        boolean isUserExistByEmail = userRepository.findByEmail(request.getEmail()).isPresent();
        if (isUserExist || isUserExistByEmail) throw new UserAlreadyExist("user already exist");
        User user = new User();
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setAuthorities(List.of(Authority.USER));
        var savedUser = userRepository.save(user);

        sendRegistrationMessage(user);

        String token = jwtService.generateAccessToken(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setToken(token);
        response.setMessage("Successfully created");
        return response;
    }

    private void sendRegistrationMessage(User user) throws EmailException {
        try {
            Recipient recipient = new Recipient();
            recipient.setName(user.getUsername());
            recipient.setEmail(user.getEmail());
            List<Recipient> recipients = List.of(
                    recipient);

             EmailRequest emailRequest = new EmailRequest();
             emailRequest.setRecipients(recipients);
             emailRequest.setHtmlContent(introductionMessage());
             emailRequest.setSubject("SignUp");
             mailService.sendMail(emailRequest);
         }catch (Exception e){
             userRepository.delete(user);
             throw new EmailException("invalid email");
         }
    }


    @Override
    public User getUserBy(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException(
                        String.format("user with %s is not found", email)));
    }

    @Override
    public User getUserById(Long id) throws UserNotFound {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFound("user not found"));
    }


    @Override
    public ProfileResponse setProfile(ProfileRequest profileRequest) throws UserNotFound {
        var getUser = getUserById(profileRequest.getUserId());
        Profile userProfile = mapper.map(profileRequest, Profile.class);
        userProfile.setUserId(getUser.getId());
        getUser.setProfile(userProfile);
        userRepository.save(getUser);
        return new ProfileResponse();
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) throws UserNotFound {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest request) throws UserNotFound, IncorrectCredentials {
        User user = validate.userLoginRequest(request);
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateAccessToken(user);
            LoginResponse response = new LoginResponse();
            response.setUserAuthority(user.getAuthorities().get(0).toString());
            response.setUserId(user.getId().toString());
            response.setAccessToken(token);
            return response;
        }
        throw  new IncorrectCredentials("incorrect password ");
    }


}

