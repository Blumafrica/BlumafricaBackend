package bluma.africa.blumaafrica.config.security;

import bluma.africa.blumaafrica.config.security.Service.JwtService;
import bluma.africa.blumaafrica.config.security.filter.BlumaAuthenticationFilter;

import bluma.africa.blumaafrica.config.security.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
//    private final BlumaAuthorizationFilter blumaAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(c -> c.disable())
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedMethods(List.of("POST", "PUT", "GET"));
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                })
                .addFilterAt(new BlumaAuthenticationFilter(authenticationManager, jwtService), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(blumaAuthorizationFilter, BlumaAuthorizationFilter.class)
                .authorizeHttpRequests(c -> c.requestMatchers(HttpMethod.POST, getPublicEndpoints()).permitAll())
//                        requestMatchers(HttpMethod.GET, "/api/v1/user", "/api/v1/user/**").hasAnyAuthority(Authority.USER.name()))

                .build();

    }

    private static String[] getPublicEndpoints() {
        return SecurityUtils.getPublicEndpoints()
                .toArray(String[]::new);
    }

}
