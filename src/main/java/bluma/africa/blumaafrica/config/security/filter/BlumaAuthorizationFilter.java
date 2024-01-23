package bluma.africa.blumaafrica.config.security.filter;

import bluma.africa.blumaafrica.config.security.Service.JwtService;
import bluma.africa.blumaafrica.config.security.utils.SecurityUtils;
import bluma.africa.blumaafrica.data.models.Admin;
import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.User;
import bluma.africa.blumaafrica.service.AdminService;
import bluma.africa.blumaafrica.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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
    private final AdminService adminService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
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
                    if(getAdminAuthority(username)){
                        Admin admin = adminService.findAdminByEmail(username);
                        var authorities = admin.getAuthority().stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                                .toList();
                        log.info("AdminAuthorities:: {}", authorities);
                        var authentication = new UsernamePasswordAuthenticationToken(admin.getEmail(), null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        User user = userService.getUserBy(username);
                        var authorities = user.getAuthorities().stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                                .toList();
                        log.info("authorities:: {}", authorities);

                        var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                } catch (Exception e) {
                    log.error("Error processing JWT token: {}", e.getMessage());
                }
            } else {
                log.warn("Invalid or missing 'Authorization' header");
            }

            filterChain.doFilter(request, response);
        }
    }
    private boolean getAdminAuthority(String usernameOrEmail){
        Admin admin = adminService.findAdminByEmail(usernameOrEmail);
        return admin != null && admin.getAuthority().contains(Authority.ADMIN);
    }


}
