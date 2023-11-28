package simonedangelo.mondovan.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({"password"})
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_users")
    private long id;
    @Column(name = "emails")
    private String email;
    @Column(name = "pws")
    private String password;
    @Column(name = "names")
    private String name;
    @Column(name = "surnames")
    private String surname;
    @Column(name = "url_imgs")
    private String avatar;
    @Column(name = "days_of_birth")
    private LocalDate dayOfBirth;
    @CreationTimestamp
    private Date createdAt;
}
