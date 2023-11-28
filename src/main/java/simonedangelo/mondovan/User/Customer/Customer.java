package simonedangelo.mondovan.User.Customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import simonedangelo.mondovan.User.Enum.Role;
import simonedangelo.mondovan.User.User;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customers")
@Setter
@Getter
@ToString
@JsonIgnoreProperties({"authorities", "credentialsNonExpired", "accountNonExpired", "accountNonLocked", "enabled"})
public class Customer extends User {
    @Enumerated(EnumType.STRING)
    private Role role;

    public Customer() {
        this.role = Role.CUSTOMER;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
