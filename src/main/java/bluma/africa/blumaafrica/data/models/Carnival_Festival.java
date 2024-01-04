package bluma.africa.blumaafrica.data.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Carnival_Festival {
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
