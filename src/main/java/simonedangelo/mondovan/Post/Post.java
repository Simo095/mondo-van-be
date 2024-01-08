package simonedangelo.mondovan.Post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;
import simonedangelo.mondovan.Comment.Comment;
import simonedangelo.mondovan.Post.Enum.Category;
import simonedangelo.mondovan.User.User;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_posts", nullable = false)
    long id;
    @Column(name = "titles")
    private String title;
    @Column(name = "texts", length = Length.LOB_DEFAULT)
    private String text;
    @Column(name = "url_imgs")
    private String img;
    @Column(name = "categories")
    @Enumerated(EnumType.STRING)
    private Category category;

    @CreationTimestamp
    private Date createdAt;

    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


}
