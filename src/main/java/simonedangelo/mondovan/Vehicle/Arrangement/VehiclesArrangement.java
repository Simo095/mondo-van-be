package simonedangelo.mondovan.Vehicle.Arrangement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Vehicle.Vehicle;

@Entity
@Table(name = "arrangements")
@Getter
@Setter
@ToString
public class VehiclesArrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_arrangement", nullable = false)
    private long id;
    private int bads;
    @Column(name = "desc_beds")
    private String descriptionBeds;
    private boolean bathroom;
    @Column(name = "hot_water")
    private boolean hotWater;
    private boolean water;
    private boolean wc;
    private boolean kitchen;
    private boolean fridge;
    private boolean gas;
    @Column(name = "desc_kitchen")
    private String descriptionKitchen;
    @Column(name = "desc_bathroom")
    private String descriptionBathroom;
    @Column(name = "desc_accessories")
    private String accessoriesDescription;
    @Column(name = "set_up_by_me")
    private boolean doItMySelf;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;

}
