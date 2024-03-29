package bluma.africa.blumaafrica.config;

import bluma.africa.blumaafrica.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan({"bluma.africa.blumaafrica"})
@EntityScan("bluma.africa.blumaafrica")
@EnableJpaRepositories("bluma.africa.blumaafrica.data.repositories")
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public UserDetailsService userDetailsService(UserService userService){
        return (username )-> getUserByUsername(userService, username);
    }

    private User getUserByUsername(UserService userService, String username) {
        var user = userService.getUserBy(username);
        var authorities = user.getAuthorities();
        var userAuthorities =
                authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.name()))
                        .toList();
        return new User(username, user.getPassword(), userAuthorities);

    }
}
