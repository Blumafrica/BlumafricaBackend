package bluma.africa.blumaafrica.config.security.Service;

import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtService {

    public String generateAccessToken(User user){
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(Authority::name)
                .collect(Collectors.toList());
        return JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(86400L, ChronoUnit.SECONDS))
                .withIssuer("Blumafrica .")
                .withSubject(user.getEmail())
                .withClaim("claims",authorities)
                .sign(Algorithm.HMAC256("secret"));

    }
    public String generateAccessTokenForAdmin(Admin admin){
        List<String> authorities = admin.getAuthority()
                .stream()
                .map(Authority::name)
                .collect(Collectors.toList());
        return JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(86400L, ChronoUnit.SECONDS))
                .withIssuer("Blumafrica .")
                .withSubject(admin.getEmail())
                .withClaim("claims",authorities)
                .sign(Algorithm.HMAC256("secret"));

    }
    public String extractUsernameFromToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("Blumafrica .")
                .build();

        DecodedJWT decodedJWT =verifier.verify(token);
        return decodedJWT.getSubject();
    }

}
