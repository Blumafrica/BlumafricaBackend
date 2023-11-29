package bluma.africa.blumaafrica.config.security.manager;

import bluma.africa.blumaafrica.exceptions.UserFailAuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class BlumaAuthenticationManager implements AuthenticationManager {

    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass()))
            return authenticationProvider.authenticate(authentication);
        throw  new UserFailAuthenticationException("unable to authenticate request");
    }
}
