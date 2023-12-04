package bluma.africa.blumaafrica.config.security.utils;

import java.util.List;

public class SecurityUtils {


    public static List<String> getPublicEndpoints(){
        return List.of(
                "/login",
                "/api/v1/loginAsAdmin",
                "/api/v1/user/register",
                "/api/v1/post",
                "/api/v1/deletePost/{id}",
                "/api/v1/getAdminPost",
                "/api/v1/user/post",
                "/api/v1/user/{postId}/editPost/",
                "/api/v1/{postId}/deletePost/"
        );
    }
}
