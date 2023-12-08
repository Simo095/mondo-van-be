package simonedangelo.mondovan.Vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Reservation.Reservation;
import simonedangelo.mondovan.ServiceStatus.ServiceStatus;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.Vehicle.Arrangement.VehiclesArrangement;
import simonedangelo.mondovan.Vehicle.Enum.License;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Transmission;
import simonedangelo.mondovan.Vehicle.Enum.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@ToString
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_vehicles", nullable = false)
    private long id;
    @Column(name = "names")
    private String name;
    @Column(name = "models")
    private String model;
    @Column(name = "brands")
    private String brand;
    @Column(name = "plates")
    private String plate;
    @Column(name = "registration_documents")
    private String registrationDocument;
    @Column(name = "short_desc")
    private String shortDescriptions;
    @Column(name = "avatars")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> avatar;

    @Column(name = "first_enrollments")
    private LocalDate firstEnrollment;

    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private License license;
    @Column(name = "transmissions")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private Supply supply;

    private int displacement;
    private long kilometers;
    private double height;
    private double width;
    private double length;

    @OneToOne
    @JoinColumn(name = "id_owners")
    @JsonIgnore
    private Owner owner;
    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private List<ServiceStatus> servicesStatus;
    @JsonIgnore
    @OneToOne(mappedBy = "vehicle")
    private VehiclesArrangement vehiclesArrangement;
    @JsonIgnore
    @OneToOne(mappedBy = "vehicle")
    private Reservation reservation;

    public Vehicle() {
        this.avatar = new ArrayList<>();
    }

}
