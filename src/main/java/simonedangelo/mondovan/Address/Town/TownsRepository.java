package simonedangelo.mondovan.Address.Town;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TownsRepository extends JpaRepository<Town, Long> {
    @Query("SELECT c FROM Town c FULL JOIN c.province p WHERE LOWER(p.abbreviation)=LOWER(:abbreviation)")
    List<Town> findByAbbreviation(String abbreviation);
}
