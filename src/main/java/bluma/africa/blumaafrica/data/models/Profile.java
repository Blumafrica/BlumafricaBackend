package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String about;
    private String headline;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String profilePicture;
    private String coverPicture;
    private Long userId;

}
