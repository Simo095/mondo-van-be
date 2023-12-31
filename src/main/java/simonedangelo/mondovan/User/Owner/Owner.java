package simonedangelo.mondovan.User.Owner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import simonedangelo.mondovan.Address.AddressesOwner;
import simonedangelo.mondovan.User.Enum.Role;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.Vehicle.Vehicle;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "owners")
@Getter
@Setter
@JsonIgnoreProperties({"authorities", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "enabled", "vehicle"})
public class Owner extends User {
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "id_addresses")
    @JsonIgnore
    private AddressesOwner addressesOwner;
    @OneToOne(mappedBy = "owner")
    private Vehicle vehicle;

    public Owner() {
        this.role = Role.OWNER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
