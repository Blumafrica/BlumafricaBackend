package bluma.africa.blumaafrica.config.security.utils;

import java.util.List;

public class SecurityUtils {


    public static List<String> getPublicEndpoints(){
        return List.of(
                "/login",
                "/api/v1/loginAsAdmin",
                "/api/v1/user/register"

        );
    }
}
