package simonedangelo.mondovan.Notification;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Notification.Enum.Status;
import simonedangelo.mondovan.User.User;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_notifications", nullable = false)
    private long id;
    private User sender;
    private User receiver;
    private String object;
    private String text;
    private Status state;
    @ManyToOne
    @JoinColumn(name = "id_users")
    private User user;

}
