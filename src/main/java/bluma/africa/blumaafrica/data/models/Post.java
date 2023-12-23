package bluma.africa.blumaafrica.data.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;





@Entity(name = "post")
@Setter
@Getter
//@ToString
public class  Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long postOwnerId;
    private String content;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime createdAt;
    private String description;
    private String fileUrl;
    @Enumerated(EnumType.STRING)
    private Authority postOwnerAuthority;
    @ElementCollection( fetch = FetchType.LAZY)
    @CollectionTable(name = " ListOfLikeIds", joinColumns = @JoinColumn(name = "LikesId"))
    @Column(name = " ListOfLikeIds")
//    @OneToOne(cascade =  CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Long> ListOfLikeIds;
    @ElementCollection
    @CollectionTable(name = "ListOfCommentIds", joinColumns = @JoinColumn(name = "commentId"))
    @Column(name = "ListOfCommentIds")
    private List<Long> listOfCommentIds;
    @ElementCollection( fetch = FetchType.EAGER)
    @CollectionTable(name = "ListOfShareIds", joinColumns = @JoinColumn(name = "shareId"))
    @Column(name = "ListOfShareIds")
    private List<Long> listOfShareIds;


}
