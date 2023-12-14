package simonedangelo.mondovan.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.ToString;
import simonedangelo.mondovan.User.Customer.Customer;

import java.util.List;

@Entity
@Table(name = "addresses_customers")
public class AddressesCustomer extends Address {
    @OneToMany(mappedBy = "addressesCustomer")
    @ToString.Exclude
    @JsonIgnore
    private List<Customer> customers;

}
