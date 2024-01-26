package bluma.africa.blumaafrica.config.security;

import bluma.africa.blumaafrica.config.security.Service.JwtService;
import bluma.africa.blumaafrica.config.security.filter.BlumaAuthenticationFilter;
import bluma.africa.blumaafrica.config.security.filter.BlumaAuthorizationFilter;
import bluma.africa.blumaafrica.config.security.utils.SecurityUtils;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.service.AdminService;
import bluma.africa.blumaafrica.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final AdminService adminService;
    private final BlumaAuthorizationFilter blumaAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .addFilterAt(login(), UsernamePasswordAuthenticationFilter.class).addFilterBefore(blumaAuthorizationFilter, BlumaAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                                .requestMatchers(HttpMethod.POST, getPublicEndpoints()).permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/user", "/api/v1/user/**").hasAnyAuthority(Authority.USER.name())
                                .requestMatchers("/api/v1/user/register",

                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**").permitAll()

                                .anyRequest().authenticated()
                )
                .build();
    }

    private BlumaAuthenticationFilter login() {
        return new BlumaAuthenticationFilter(
                authenticationManager, jwtService, userService);
    }
    private static String[] getPublicEndpoints() {
        return SecurityUtils.getPublicEndpoints()
                .toArray(String[]::new);
    }

}
