package bluma.africa.blumaafrica.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 100, nullable = false)
    private String username;
    @Column(unique = true, length = 100, nullable = false)
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = true)
    private Profile profile;
}
