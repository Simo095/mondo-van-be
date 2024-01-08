package simonedangelo.mondovan.Comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;
import simonedangelo.mondovan.Post.Post;
import simonedangelo.mondovan.User.User;

import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_comments", nullable = false)
    long id;
    @Column(name = "texts", length = Length.LOB_DEFAULT)
    private String text;
    @CreationTimestamp
    private Date createdAt;
    private Date updateAt;
    @ManyToOne
    @JoinColumn(name = "id_users")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_posts")
    private Post post;
}
