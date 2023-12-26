package bluma.africa.blumaafrica.data.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity(name = "likes")
@ToString
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long postId;
    @Enumerated(EnumType.STRING)
    private Authority userAUTHORITY;
    private boolean isLiked;
}
