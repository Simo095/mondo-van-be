package simonedangelo.mondovan.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v JOIN v.owner o WHERE o.id=:idOwner")
    Optional<Vehicle> findByIdOwner(long idOwner);
}
