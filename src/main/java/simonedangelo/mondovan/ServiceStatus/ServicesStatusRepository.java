package simonedangelo.mondovan.ServiceStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesStatusRepository extends JpaRepository<ServiceStatus, Long> {
    @Query("SELECT s,s.date FROM ServiceStatus s JOIN s.vehicle v WHERE v.id=:idVehicle ORDER BY s.date")
    List<ServiceStatus> findByIdVehicle(long idVehicle);


    @Query("SELECT s,s.date FROM ServiceStatus s JOIN s.vehicle v WHERE s.date BETWEEN :start AND :end ORDER BY s.date")
    Optional<List<ServiceStatus>> findByRangeDate(LocalDate start, LocalDate end);
}
