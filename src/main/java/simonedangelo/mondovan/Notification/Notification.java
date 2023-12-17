package simonedangelo.mondovan.Notification;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import simonedangelo.mondovan.Notification.Enum.Status;
import simonedangelo.mondovan.User.User;

import java.util.Date;

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
    private String object;
    private String text;
    private Status state;
    @ManyToOne
    @JoinColumn(name = "id_receiver")
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "id_sender")
    private User sender;
    @CreationTimestamp
    private Date createAd;

}
