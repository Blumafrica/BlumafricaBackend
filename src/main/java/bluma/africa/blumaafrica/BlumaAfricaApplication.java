package bluma.africa.blumaafrica;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@OpenAPIDefinition(
        info = @Info(
                title = "Blumafrica",
                version = "v1",
                description = "This app provides REST APIs documentation for Blumafrca",
                contact = @Contact(
                        name = "Bluma support",
                        email = "info@Blumafrica.com"
                )
        ),
        servers = {
                @Server(
                        description = "current",
                        url = "/"
                ),
        },

        security = {
                @SecurityRequirement(
                        name = "Bearer Auth"
                )
        }
)
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class  BlumaAfricaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlumaAfricaApplication.class, args);
    }

}
