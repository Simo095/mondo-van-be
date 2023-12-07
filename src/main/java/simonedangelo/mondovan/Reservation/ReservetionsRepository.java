package simonedangelo.mondovan.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservetionsRepository extends JpaRepository<Reservation, Long> {
}
