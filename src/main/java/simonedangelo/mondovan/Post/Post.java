package simonedangelo.mondovan.Post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;
import simonedangelo.mondovan.User.User;

import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_post", nullable = false)
    long id;
    @Column(name = "titles")
    private String title;
    @Column(name = "texts", length = Length.LOB_DEFAULT)
    private String text;
    @Column(name = "url_img")
    private String img;
    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;
    @CreationTimestamp
    private Date createdAt;
    private Date updateAt;


}
