package simonedangelo.mondovan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import simonedangelo.mondovan.Province.ProvincesRepository;
import simonedangelo.mondovan.Province.ProvincesService;

@Component
public class RunnerProvinceDB implements CommandLineRunner {
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private ProvincesRepository provincesRepository;

    @Override
    public void run(String... args) throws Exception {
        if (provincesRepository.count() == 0) {
            provincesService.saveProvinceFromFile();
        } else {
            System.err.println("PROVINCE GIA CARICATE");
        }

    }
}
