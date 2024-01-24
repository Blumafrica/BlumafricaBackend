package bluma.africa.blumaafrica.data.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
@Getter
@Setter
@Entity
@Table(name = "comments")
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String commentText;
    private Long commenterId;
    @ManyToOne
    private Post postId;
    @OneToOne
    private Share shareId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Enumerated(value = EnumType.STRING)
    private Authority userAuthority;

    @PrePersist
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

}
