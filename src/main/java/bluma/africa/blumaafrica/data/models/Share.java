package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "share")
@Setter
@Getter
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long UserId;
    private Long postId;
}
