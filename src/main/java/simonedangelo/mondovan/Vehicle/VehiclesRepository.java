package simonedangelo.mondovan.Vehicle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehiclesRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v JOIN v.owner o WHERE o.id=:idOwner")
    Optional<Vehicle> findByIdOwner(long idOwner);

    @Query("SELECT v FROM Vehicle v JOIN v.owner o JOIN o.addressesOwner a JOIN a.town t JOIN t.province p WHERE p.name=:province")
    Optional<List<Vehicle>> findByAddressOwner(String province);

    @Query("SELECT v FROM Vehicle v JOIN v.owner o JOIN o.addressesOwner a JOIN a.town t JOIN t.province p WHERE p.region=:region")
    Page<Vehicle> findByRegionOwner(String region, Pageable p);

    @Query("SELECT v,s.date FROM Vehicle v JOIN v.servicesStatus s WHERE s.state='AVAILABLE' AND s.date=CURRENT_DATE() ORDER BY s.date")
    Optional<List<Vehicle>> findByAvailability();

    @Query("SELECT v FROM Vehicle v JOIN v.servicesStatus s WHERE s.state='AVAILABLE' AND s.date BETWEEN:start AND :end AND v.vehiclesArrangement.bads=:beds AND v.owner.addressesOwner.town.province.abbreviation=:province")
    List<Vehicle> findByRangeDateProvinceAndBeds(LocalDate start, LocalDate end, int beds, String province);

    @Query("SELECT v FROM Vehicle v JOIN v.servicesStatus s WHERE s.state='AVAILABLE' AND s.date BETWEEN:start AND :end AND v.vehiclesArrangement.bads=:beds")
    List<Vehicle> findByRangeDateAndBeds(LocalDate start, LocalDate end, int beds);

    @Query("SELECT v FROM Vehicle v JOIN v.servicesStatus s WHERE s.state='AVAILABLE' AND s.date BETWEEN:start AND :end AND v.owner.addressesOwner.town.province.abbreviation=:province ")
    List<Vehicle> findByRangeDateAndProvince(LocalDate start, LocalDate end, String province);

    @Query("SELECT v FROM Vehicle v JOIN v.servicesStatus s WHERE s.state='AVAILABLE' AND s.date BETWEEN:start AND :end")
    List<Vehicle> findByRangeDateOnly(LocalDate start, LocalDate end);

}
