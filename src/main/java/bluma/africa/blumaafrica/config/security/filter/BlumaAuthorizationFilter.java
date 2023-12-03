package bluma.africa.blumaafrica.config.security.filter;

import bluma.africa.blumaafrica.config.security.Service.JwtService;
import bluma.africa.blumaafrica.config.security.utils.SecurityUtils;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class BlumaAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isRequestToPublicEndpoint = request.getMethod().equals("POST") &&
                SecurityUtils.getPublicEndpoints().contains(request.getServletPath());

        if (isRequestToPublicEndpoint) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader("Authorization");
            log.info("auth header:: {}", authorizationHeader);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring("Bearer ".length());

                try {
                    String username = jwtService.extractUsernameFromToken(token);
                    log.info("username:: {}", username);

                    User user = userService.getUserBy(username);
                    var authorities = user.getAuthorities().stream()
                            .map(authority -> new SimpleGrantedAuthority(authority.name()))
                            .toList();
                    log.info("authorities:: {}", authorities);

                    var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } catch (Exception e) {
                    log.error("Error processing JWT token: {}", e.getMessage());
                }
            } else {
                log.warn("Invalid or missing 'Authorization' header");
            }

            filterChain.doFilter(request, response);
        }
    }


}
