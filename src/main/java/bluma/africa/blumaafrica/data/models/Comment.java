package bluma.africa.blumaafrica.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


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
    @GeneratedValue(strategy = IDENTITY)
    private Long commentId;
    private String commentText;
    @ManyToOne
    private User commenterId;
    @ManyToOne
    private Post postId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }

}
