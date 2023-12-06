package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 100, nullable = false)
    private String username;
    @Column(unique = true, length = 100, nullable = false)
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Profile profile;
    @Enumerated(EnumType.STRING)
    private List<Authority> authorities;
}
