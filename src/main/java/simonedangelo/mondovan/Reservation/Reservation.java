package simonedangelo.mondovan.Reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Reservation.Enums.Status;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.Vehicle.Vehicle;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_reservations", nullable = false)
    private long id;
    @Column(name = "start_dates")
    private LocalDate startDate;
    @Column(name = "end_dates")
    private LocalDate endDate;
    @Column(name = "status")
    private Status state;
    @ManyToOne
    @JoinColumn(name = "id_vehicles")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "id_users")
    private User user;

}
