package simonedangelo.mondovan.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.ToString;
import simonedangelo.mondovan.User.Owner.Owner;

import java.util.List;

@Entity
@Table(name = "addresses_owners")
public class AddressesOwner extends Address {
    @OneToMany(mappedBy = "addressesOwner")
    @ToString.Exclude
    @JsonIgnore
    private List<Owner> owners;
}
