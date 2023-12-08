package simonedangelo.mondovan.ServiceStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.Vehicle.Vehicle;

import java.time.LocalDate;

@Entity
@Table(name = "availability")
@Getter
@Setter

public class ServiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_availabilities")
    private long id;
    @Column(name = "dates")
    private LocalDate date;
    @Column(name = "service_status")
    @Enumerated(EnumType.STRING)
    private Status state;
    @ManyToOne
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;
}
