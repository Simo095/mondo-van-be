package simonedangelo.mondovan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import simonedangelo.mondovan.Address.Province.ProvincesRepository;
import simonedangelo.mondovan.Address.Province.ProvincesService;
import simonedangelo.mondovan.Address.Town.TownsService;

@Component
@Order(1)
public class RunnerProvinceDB implements CommandLineRunner {
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private TownsService townsService;

    @Override
    public void run(String... args) throws Exception {
        if (provincesRepository.count() == 0) {
            provincesService.saveProvinceFromFile();
            townsService.saveCityFromFile();
            System.err.println("PROVINCE E CITTA` OK");
        } else {
            System.err.println("PROVINCE GIA CARICATE");
        }
    }
}
