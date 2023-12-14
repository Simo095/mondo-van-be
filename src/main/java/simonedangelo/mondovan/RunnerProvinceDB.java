package simonedangelo.mondovan;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import simonedangelo.mondovan.Address.AddressesCustomer;
import simonedangelo.mondovan.Address.AddressesOwner;
import simonedangelo.mondovan.Address.AddressesRepository;
import simonedangelo.mondovan.Address.Province.ProvincesRepository;
import simonedangelo.mondovan.Address.Province.ProvincesService;
import simonedangelo.mondovan.Address.Town.TownsRepository;
import simonedangelo.mondovan.Address.Town.TownsService;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Notification.Notification;
import simonedangelo.mondovan.Notification.NotificationsRepository;
import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.ServiceStatus.ServiceStatus;
import simonedangelo.mondovan.ServiceStatus.ServicesStatusRepository;
import simonedangelo.mondovan.User.Admin.Admin;
import simonedangelo.mondovan.User.Customer.Customer;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.UsersRepository;
import simonedangelo.mondovan.Vehicle.Arrangement.VehiclesArrangement;
import simonedangelo.mondovan.Vehicle.Arrangement.VehiclesArrangementRepository;
import simonedangelo.mondovan.Vehicle.Enum.License;
import simonedangelo.mondovan.Vehicle.Enum.Supply;
import simonedangelo.mondovan.Vehicle.Enum.Transmission;
import simonedangelo.mondovan.Vehicle.Enum.Type;
import simonedangelo.mondovan.Vehicle.Vehicle;
import simonedangelo.mondovan.Vehicle.VehiclesRepository;
import simonedangelo.mondovan.Vehicle.VehiclesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddressesRepository addressesRepository;
    @Autowired
    private TownsRepository townsRepository;
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private VehiclesArrangementRepository vehiclesArrangementRepository;
    @Autowired
    private ServicesStatusRepository servicesStatusRepository;
    @Autowired
    private NotificationsRepository notificationsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (provincesRepository.count() == 0) {
            provincesService.saveProvinceFromFile();
            townsService.saveCityFromFile();
        } else {
            System.err.println("PROVINCE GIA CARICATE");
        }
        if (usersRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setName("Admin");
            admin.setSurname("VanWorld");
            admin.setEmail("admin.admin@vanworld");
            admin.setPassword(passwordEncoder.encode("1234"));
            usersRepository.save(admin);


            for (int i = 0; i < 10; i++) {
                Faker f = new Faker(Locale.ITALIAN);
                Random r = new Random();
                Customer c = new Customer();
                AddressesCustomer a = new AddressesCustomer();
                a.setHouseNumber(r.nextInt(1, 100));
                a.setStreet(f.address().streetAddress(false));
                a.setZipCode(r.nextInt(10000, 99999));
                a.setTown(townsRepository.findById(r.nextLong(1, 7300)).orElseThrow(() -> new NotFoundEx("error in runner")));
                addressesRepository.save(a);
                c.setName(f.name().firstName());
                c.setSurname(f.name().lastName());
                c.setDayOfBirth(LocalDate.of(r.nextInt(1950, 2003), r.nextInt(1, 12), r.nextInt(1, 28)));
                c.setEmail(c.getName() + "." + c.getSurname() + "@gmail.com");
                c.setAvatar("https://ui-avatars.com/api/?name=" + c.getName() + "+" + c.getSurname());
                c.setCover("https://ui-avatars.com/api/?name=" + c.getName() + "+" + c.getSurname());
                c.setPassword(passwordEncoder.encode("1234"));
                c.setAddressesCustomer(a);
                usersRepository.save(c);
                Notification n = new Notification();
                n.setObject("Benvenuto nel sito VanWorld");
                n.setText("Complimenti, ora sei un membro di VanWorld e potrai noleggiare Van per le tue vacanze, dai un occhiata ai mezzi disponibili");
                n.setSender(admin);
                n.setState(simonedangelo.mondovan.Notification.Enum.Status.NOT_READ);
                n.setReceiver(c);
                notificationsRepository.save(n);
                Owner o = new Owner();
                AddressesOwner b = new AddressesOwner();
                b.setHouseNumber(r.nextInt(1, 100));
                b.setStreet(f.address().streetAddress(false));
                b.setZipCode(r.nextInt(10000, 99999));
                b.setTown(townsRepository.findById(r.nextLong(1, 7300)).orElseThrow(() -> new NotFoundEx("error in runner")));
                addressesRepository.save(b);
                o.setName(f.name().firstName());
                o.setSurname(f.name().lastName());
                o.setDayOfBirth(LocalDate.of(r.nextInt(1950, 2003), r.nextInt(1, 12), r.nextInt(1, 28)));
                o.setEmail(o.getName() + "." + o.getSurname() + "@gmail.com");
                o.setAvatar("https://ui-avatars.com/api/?name=" + o.getName() + "+" + o.getSurname());
                o.setCover("https://ui-avatars.com/api/?name=" + o.getName() + "+" + o.getSurname());
                o.setPassword(passwordEncoder.encode("1234"));
                o.setAddressesOwner(b);
                usersRepository.save(o);
                Notification e = new Notification();
                n.setObject("Benvenuto nel sito VanWorld");
                n.setText("Complimenti, ora sei un membro di VanWorld e potrai mettere in noleggio il tuo van, continua nella sezione il tuo veicolo");
                n.setSender(admin);
                n.setState(simonedangelo.mondovan.Notification.Enum.Status.NOT_READ);
                n.setReceiver(o);
                notificationsRepository.save(e);
                Vehicle v = new Vehicle();
                v.setOwner(o);
                v.setName(f.funnyName().name());
                v.setBrand(r.nextInt() % 2 == 0 ? "Volkswagen" : "Fiat");
                v.setModel(v.getBrand().equals("Volkswagen") ? "T" + r.nextInt(1, 6) : "Ducato");
                v.setDisplacement(r.nextInt(1600, 2500));
                v.setSits(r.nextInt(2, 6));
                v.setKilometers(r.nextLong(100000, 500000));
                v.setFirstEnrollment(LocalDate.of(r.nextInt(1950, 2003), r.nextInt(1, 12), r.nextInt(1, 28)));
                v.setType(v.getBrand().equals("Volkswagen") ? Type.VAN : Type.CAMPER);
                v.setHeight(r.nextInt(2, 3));
                v.setLength(r.nextInt(2, 4));
                v.setWidth(r.nextInt(1, 2));
                v.setLicense(License.B);
                v.setPricePerDay(r.nextInt(80, 200));
                v.setSupply(Supply.DIESEL);
                v.setTransmission(Transmission.MANUAL);
                v.setShortDescriptions(r.nextInt() % 2 == 0 ? "Vacanze in liberta con " + v.getName() : "Comodita garantita!!");
                vehiclesRepository.save(v);
                for (int j = 0; j < 366; j++) {
                    List<String> lS = v.getAvatar();
                    if (lS.size() <= 2) {
                        String s = "https://res.cloudinary.com/dhwybes2b/image/upload/v1702053853/qtx9eypbotnbyc9w84ko.jpg";
                        lS.add(s);
                        v.setAvatar(lS);
                        vehiclesRepository.save(v);
                    }
                    ServiceStatus s = new ServiceStatus();
                    s.setDate(LocalDate.now().plusDays(j));
                    s.setState(Status.AVAILABLE);
                    s.setVehicle(v);
                    servicesStatusRepository.save(s);
                }
                List<String> descCuci = new ArrayList<>();
                List<String> descBagno = new ArrayList<>();
                List<String> descCame = new ArrayList<>();
                List<String> descAcc = new ArrayList<>();
                descCuci.add("Questa cucina è ideale per i van piccoli o per chi non ha bisogno di molto spazio. È composta da un piano cottura a due fuochi, un lavello e un piccolo frigorifero.");
                descCuci.add("Perfetta per i van più grandi o per chi vuole avere tutto a portata di mano. È composta da un piano cottura a tre o quattro fuochi, un lavello, un frigorifero di grandi dimensioni, un forno a microonde e un forno a gas o elettrico.");
                descCuci.add("Ideale per chi ama cucinare all'aria aperta. È composta da un barbecue, un lavello e un piano di lavoro.");
                descCuci.add("Questa cucina è alimentata da energia solare, il che la rende ecologica e indipendente dalla rete elettrica. È composta da un piano cottura a due fuochi, un lavello e un frigorifero a compressore.");
                descCuci.add("Questa cucina è dotata di un piano cottura a induzione, che è più efficiente e sicuro di un piano cottura a gas o elettrico. È composta da un piano cottura a due o quattro fuochi, un lavello e un frigorifero.");
                descBagno.add("Il bagno di " + v.getName() + " è piccolo ma funzionale. Ha un wc chimico, un lavandino e una doccia. Lo spazio è ben organizzato e tutto è a portata di mano.");
                descBagno.add("Il bagno di " + v.getName() + " è dotato di un wc chimico di ultima generazione, che non produce odori. La doccia è spaziosa e ha un soffione a pioggia. Il lavandino è in ceramica e ha un piccolo specchio.");
                descBagno.add("Il bagno di " + v.getName() + " è stato realizzato su misura per sfruttare al meglio lo spazio disponibile. Il wc è nascosto dietro un pannello scorrevole, mentre la doccia è installata in un angolo.");
                descBagno.add("Il bagno di " + v.getName() + " è essenziale ma confortevole. Ha un wc chimico, un lavandino e una doccia a tenda. Lo spazio è ben illuminato e le pareti sono rivestite in piastrelle.");
                descBagno.add("Il bagno di " + v.getName() + " è spazioso e confortevole. Ha un wc chimico, un lavandino, una doccia con soffione a pioggia e una finestra. Lo spazio è ben organizzato e tutto è a portata di mano.");
                descCame.add("Il letto matrimoniale fisso è la soluzione più classica per la sistemazione dei letti in un van. È solitamente posizionato in posizione longitudinale, occupando tutta la larghezza del van.");
                descCame.add("Il letto matrimoniale trasversale è una soluzione più compatta, ideale per i van di dimensioni ridotte. È solitamente posizionato in posizione trasversale, occupando tutta la lunghezza del van.");
                descCame.add("Il letto a castello è una soluzione per risparmiare spazio in un van. È solitamente posizionato in posizione longitudinale, con il letto superiore sopra il letto inferiore. Questa soluzione è ideale per le famiglie con bambini o per i gruppi di amici.");
                descCame.add("Il letto a scomparsa è una soluzione versatile, che permette di avere un letto matrimoniale in un van di dimensioni ridotte. Il letto è solitamente posizionato in posizione longitudinale, e può essere ripiegato quando non è in uso.");
                descCame.add("Il letto da divano è una soluzione pratica, che permette di avere un letto matrimoniale in un van di dimensioni ridotte. Il divano può essere trasformato in un letto matrimoniale semplicemente reclinando lo schienale e rimuovendo i cuscini.");
                descAcc.add("Il tendalino è un accessorio indispensabile per chi ama il campeggio.");
                descAcc.add("La veranda è un accessorio simile al tendalino, ma è più grande e offre una maggiore protezione dal sole e dalla pioggia.");
                descAcc.add("Il portabici è un accessorio indispensabile per chi ama portare con sé la bici in vacanza.");
                descAcc.add("Il pannello solare è un accessorio che permette di generare energia elettrica dal sole.");
                descAcc.add("Il sistema di riscaldamento è un accessorio indispensabile per chi viaggia in inverno.");
                VehiclesArrangement vA = new VehiclesArrangement();
                vA.setBads(r.nextInt(2, 6));
                vA.setDescriptionBeds(descCame.get(r.nextInt(0, 4)));
                vA.setBathroom(r.nextBoolean());
                vA.setWater(r.nextBoolean());
                vA.setHotWater(r.nextBoolean());
                vA.setKitchen(r.nextBoolean());
                vA.setFridge(r.nextBoolean());
                vA.setGas(r.nextBoolean());
                vA.setDescriptionBathroom(descBagno.get(r.nextInt(0, 4)));
                vA.setDescriptionKitchen(descCuci.get(r.nextInt(0, 4)));
                vA.setDoItMySelf(r.nextBoolean());
                vA.setAccessoriesDescription(descAcc.get(r.nextInt(0, 4)));
                vA.setVehicle(v);
                vehiclesArrangementRepository.save(vA);
            }

            System.err.println("Finito!");
        }
    }
}
