<<<<<<< HEAD
package bluma.africa.blumaafrica.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareRequest {

    @NotNull
    private String sharerId;
    @NotNull
    private String postId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String authority;

=======
package bluma.africa.blumaafrica.dtos.requests;public class ShareRequest {
>>>>>>> 9301b9ce747514dde222bdd29dd723ac4584e125
}
