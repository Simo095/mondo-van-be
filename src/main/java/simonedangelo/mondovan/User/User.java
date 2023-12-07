package simonedangelo.mondovan.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import simonedangelo.mondovan.Notification.Notification;
import simonedangelo.mondovan.Reservation.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @JsonIgnore
    private String password;
    @Column(name = "names")
    private String name;
    @Column(name = "surnames")
    private String surname;
    @Column(name = "url_imgs")
    private String avatar;
    @Column(name = "url_cover")
    private String cover;
    @Column(name = "days_of_birth")
    private LocalDate dayOfBirth;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Reservation reservation;
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
    @CreationTimestamp
    private Date createdAt;

    public User() {
        this.notifications = new ArrayList<>();
    }
}
