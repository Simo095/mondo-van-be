package simonedangelo.mondovan.Vehicle.Arrangement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiclesArrangementRepository extends JpaRepository<VehiclesArrangement, Long> {
    @Query("SELECT va FROM VehiclesArrangement va JOIN va.vehicle v WHERE v.id=:idVehicle")
    Optional<VehiclesArrangement> findByIdVehicles(long idVehicle);
}
