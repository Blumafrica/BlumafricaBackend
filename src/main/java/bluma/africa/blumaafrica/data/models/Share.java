package bluma.africa.blumaafrica.data.models;

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
    @ElementCollection
    @CollectionTable(name = " ListOfLikeIds", joinColumns = @JoinColumn(name = "LikesId"))
    @Column(name = " ListOfLikeIds")
    private List<Long> ListOfLikeIds = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "ListOfCommentIds", joinColumns = @JoinColumn(name = "commentId"))
    @Column(name = "ListOfCommentIds")
    private List<Long> listOfCommentIds;
    @ElementCollection( fetch = FetchType.EAGER)
    @CollectionTable(name = "ListOfShareIds", joinColumns = @JoinColumn(name = "shareId"))
    @Column(name = "ListOfShareIds")
    private List<Long> listOfShareIds;
    @OneToOne
    private Post post;

}
