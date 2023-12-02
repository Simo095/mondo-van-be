package simonedangelo.mondovan.Address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import simonedangelo.mondovan.Address.Town.Town;

@Getter
@Setter
@ToString
@Entity
@Table(name = "addresses")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_addresses", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_towns")
    private Town town;
    private String street;
    private int houseNumber;
    private int zipCode;

}
