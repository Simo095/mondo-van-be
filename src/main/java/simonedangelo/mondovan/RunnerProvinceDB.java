package simonedangelo.mondovan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import simonedangelo.mondovan.Address.Province.ProvincesRepository;
import simonedangelo.mondovan.Address.Province.ProvincesService;
import simonedangelo.mondovan.Address.Town.TownsService;
import simonedangelo.mondovan.Vehicle.VehiclesService;

@Component
public class RunnerProvinceDB implements CommandLineRunner {
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private TownsService townsService;
    @Autowired
    private VehiclesService vehiclesService;

    @Override
    public void run(String... args) throws Exception {
        if (provincesRepository.count() == 0) {
            provincesService.saveProvinceFromFile();
            townsService.saveCityFromFile();
        } else {
            System.err.println("PROVINCE GIA CARICATE");
        }
    }
}
