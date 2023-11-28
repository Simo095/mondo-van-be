package simonedangelo.mondovan.User.Payload;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UsersLoggedDTO(String token, Collection<? extends GrantedAuthority> role) {
}
