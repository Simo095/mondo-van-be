package simonedangelo.mondovan.Address.Town;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import simonedangelo.mondovan.Address.Province.ProvincesRepository;
import simonedangelo.mondovan.Address.Province.ProvincesService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TownsService {
    @Autowired
    private TownsRepository townsRepository;
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private ProvincesService provincesService;

    @Value("${file.comuni}")
    private String filePath;

    public List<String[]> readFile(File file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCityFromFile() {

        File comuniItalianiFile = new File(filePath);
        List<String[]> listaComuni = new ArrayList<>(this.readFile(comuniItalianiFile));
        AtomicInteger i = new AtomicInteger();
        listaComuni.forEach(elem -> {
            for (String e : elem) {
                if (!e.contains("Codice Provincia (Storico)(1);Progressivo del Comune (2);Denominazione in italiano")) {
                    if (e.contains("#RIF!")) {
                        i.getAndIncrement();
                        Town c = new Town();
                        String[] split = e.split(";");
                        if (provincesRepository.findByName(split[3]) != null) {
                            c.setProvince(provincesRepository.findByName(split[3]));
                        }
                        c.setName(split[2]);
                        c.setCodeTown(Integer.parseInt("00" + i));
                        c.setCodeProv(Integer.parseInt(split[0]));
                        townsRepository.save(c);
                    } else {
                        Town c = new Town();
                        String[] split = e.split(";");
                        if (provincesRepository.findByName(split[3]) != null) {
                            c.setProvince(provincesRepository.findByName(split[3]));
                        } else {
                            switch (split[3]) {
                                case ("Forlì-Cesena"): {
                                    c.setProvince(provincesRepository.findByName("Forli-Cesena"));
                                    break;
                                }
                                case ("Verbano-Cusio-Ossola"): {
                                    c.setProvince(provincesRepository.findByName("Verbania"));
                                    break;
                                }
                                case ("Valle d'Aosta/Vallée d'Aoste"): {
                                    c.setProvince(provincesRepository.findByName("Aosta"));
                                    break;
                                }
                                case ("Monza e della Brianza"): {
                                    c.setProvince(provincesRepository.findByName("Monza-Brianza"));
                                    break;
                                }
                                case ("Bolzano/Bozen"): {
                                    c.setProvince(provincesRepository.findByName("Bolzano"));
                                    break;
                                }
                                case ("La Spezia"): {
                                    c.setProvince(provincesRepository.findByName("La-Spezia"));
                                    break;
                                }
                                case ("Reggio nell'Emilia"): {
                                    c.setProvince(provincesRepository.findByName("Reggio-Emilia"));
                                    break;
                                }
                                case ("Pesaro e Urbino"): {
                                    c.setProvince(provincesRepository.findByName("Pesaro-Urbino"));
                                    break;
                                }
                                case ("Ascoli Piceno"): {
                                    c.setProvince(provincesRepository.findByName("Ascoli-Piceno"));
                                    break;
                                }
                                case ("Reggio Calabria"): {
                                    c.setProvince(provincesRepository.findByName("Reggio-Calabria"));
                                    break;
                                }
                                case ("Sud Sardegna"): {
                                    c.setProvince(provincesRepository.findByName("Sassari"));
                                    break;
                                }
                                case ("Vibo Valentia"): {
                                    c.setProvince(provincesRepository.findByName("Vibo-Valentia"));
                                    break;
                                }
                            }
                        }
                        c.setName(split[2]);
                        c.setCodeTown(Integer.parseInt(split[1]));
                        c.setCodeProv(Integer.parseInt(split[0]));
                        townsRepository.save(c);
                    }
                }
            }
        });
    }

    public List<Town> getByAbbreviation(String abbreviation) {
        return townsRepository.findByAbbreviation(abbreviation);
    }
}
