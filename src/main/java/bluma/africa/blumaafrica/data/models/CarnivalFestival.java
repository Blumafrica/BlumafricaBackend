package bluma.africa.blumaafrica.data.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CarnivalFestival {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Address address;
    private String about;
//    @ElementCollection
//    @CollectionTable(name = "files", joinColumns = @JoinColumn(name = "files"))
//    @Column(name = "files")
//    private List<String> filesUrl;


}
