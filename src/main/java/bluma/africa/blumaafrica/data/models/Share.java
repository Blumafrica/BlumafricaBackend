package bluma.africa.blumaafrica.data.models;

import bluma.africa.blumaafrica.data.models.Authority;
import bluma.africa.blumaafrica.data.models.Post;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "share")
@Setter
@Getter
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long shareOwnerId;
    private String content;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime createdAt;
    private String description;
    @Enumerated(EnumType.STRING)
    private Authority shareOwnerAuthority;
    @OneToOne
    private Post post;

}
