package simonedangelo.mondovan.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, Long> {
    Province findByName(String name);
}
