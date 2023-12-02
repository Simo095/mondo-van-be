package simonedangelo.mondovan.Address.Province;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvincesService {
    @Autowired
    private ProvincesRepository provincesRepository;
    @Value("${file.province}")
    private String filePath;

    public List<String[]> readFile(File file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Province> getAllProvince(int page, int size, String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return provincesRepository.findAll(p);
    }

    public void saveProvinceFromFile() {
        File provinceFile = new File(filePath);
        List<String[]> listaProvince = new ArrayList<>(this.readFile(provinceFile));
        listaProvince.forEach(elem -> {
            for (String e : elem) {
                if (!e.contains("Sigla;Provincia;Regione")) {
                    Province p = new Province();
                    String[] split = e.split(";");
                    p.setAbbreviation(split[0]);
                    p.setName(split[1]);
                    p.setRegion(split[2]);
                    provincesRepository.save(p);
                }
            }
        });
    }
}
