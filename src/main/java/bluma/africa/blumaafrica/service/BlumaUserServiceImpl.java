package bluma.africa.blumaafrica.service;

import bluma.africa.blumaafrica.config.security.Service.JwtService;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Profile;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.data.repositories.UserRepository;
import bluma.africa.blumaafrica.dtos.requests.EmailRequest;
import bluma.africa.blumaafrica.dtos.requests.ProfileRequest;
import bluma.africa.blumaafrica.dtos.requests.Recipient;
import bluma.africa.blumaafrica.dtos.requests.UserRequest;
import bluma.africa.blumaafrica.dtos.responses.ProfileResponse;
import bluma.africa.blumaafrica.dtos.responses.UserResponse;
import bluma.africa.blumaafrica.exceptions.EmailException;
import bluma.africa.blumaafrica.exceptions.UserAlreadyExist;
import bluma.africa.blumaafrica.exceptions.UserNotFound;
import jakarta.annotation.PostConstruct;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private MailService mailService;

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


//    try {
//        EmailRequest emailRequest = new EmailRequest();
//        emailRequest.setRecipients(List.of(new Recipient(request.getEmail())));
//        emailRequest.setHtmlContent(introductionMessage());
//        emailRequest.setSubject("SignUp");
//        mailService.sendMail(emailRequest);
//    }catch (Exception e){
//        userRepository.delete(user);
//        throw new EmailException("invalid email");
//    }

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

        String token = jwtService.generateAccessToken(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setToken(token);
        response.setMessage("Successfully created");
        return response;
    }


    @Override
    public User getUserBy(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException(
                        String.format("user with %s is not found", email)));
    }

    @Override
    public User getUserById(Long id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound("user not found"));
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







    }

