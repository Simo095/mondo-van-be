package simonedangelo.mondovan.Reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne
    @JoinColumn(name = "id_availabilities")
    @JsonIgnore
    private Vehicle vehicle;
    @OneToOne
    @JoinColumn(name = "id_users")
    @JsonIgnore
    private User user;

}
