package simonedangelo.mondovan;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import simonedangelo.mondovan.Address.AddressesCustomer;
import simonedangelo.mondovan.Address.AddressesOwner;
import simonedangelo.mondovan.Address.AddressesRepository;
import simonedangelo.mondovan.Address.Town.TownsRepository;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Notification.Notification;
import simonedangelo.mondovan.Notification.NotificationsRepository;
import simonedangelo.mondovan.Post.Enum.Category;
import simonedangelo.mondovan.Post.Post;
import simonedangelo.mondovan.Post.PostsRepository;
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
@Order(2)
public class PersonRunner implements CommandLineRunner {
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
    @Autowired
    private PostsRepository postsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usersRepository.count() == 0) {
            //ADMIN
            Admin admin = new Admin();
            admin.setName("Admin");
            admin.setSurname("VanWorld");
            admin.setAvatar("http://res.cloudinary.com/dhwybes2b/image/upload/v1702910304/ogbuqa5xrdoq1lyt4aan.png");
            admin.setCover("http://res.cloudinary.com/dhwybes2b/image/upload/v1702910304/ogbuqa5xrdoq1lyt4aan.png");
            admin.setEmail("admin.admin@vanworld.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            usersRepository.save(admin);
            
            //ADMIN
            for (int i = 0; i < 10; i++) {
                Faker f = new Faker(Locale.ITALIAN);
                Random r = new Random();
                Customer c = new Customer();
                AddressesCustomer a = new AddressesCustomer();
                Notification n = new Notification();
                Owner o = new Owner();
                AddressesOwner b = new AddressesOwner();
                Notification e = new Notification();
                Vehicle v = new Vehicle();
                VehiclesArrangement vA = new VehiclesArrangement();
                List<String> listaMarche = new ArrayList<>();
                List<String> descCuci = new ArrayList<>();
                List<String> descBagno = new ArrayList<>();
                List<String> descCame = new ArrayList<>();
                List<String> descAcc = new ArrayList<>();

                descCuci.add("Questa cucina è ideale per i van piccoli o per chi non ha bisogno di molto spazio. È composta da un piano cottura a due fuochi, un lavello e un piccolo frigorifero.");
                descCuci.add("Perfetta per i van più grandi o per chi vuole avere tutto a portata di mano. È composta da un piano cottura a tre o quattro fuochi, un lavello, un frigorifero di grandi dimensioni, un forno a microonde e un forno a gas o elettrico.");
                descCuci.add("Ideale per chi ama cucinare all'aria aperta. È composta da un barbecue, un lavello e un piano di lavoro.");
                descCuci.add("Questa cucina è alimentata da energia solare, il che la rende ecologica e indipendente dalla rete elettrica. È composta da un piano cottura a due fuochi, un lavello e un frigorifero a compressore.");
                descCuci.add("Questa cucina è dotata di un piano cottura a induzione, che è più efficiente e sicuro di un piano cottura a gas o elettrico. È composta da un piano cottura a due o quattro fuochi, un lavello e un frigorifero.");
                descBagno.add("Il bagno è piccolo ma funzionale. Ha un wc chimico, un lavandino e una doccia. Lo spazio è ben organizzato e tutto è a portata di mano.");
                descBagno.add("Il bagno è dotato di un wc chimico di ultima generazione, che non produce odori. La doccia è spaziosa e ha un soffione a pioggia. Il lavandino è in ceramica e ha un piccolo specchio.");
                descBagno.add("Il bagno è stato realizzato su misura per sfruttare al meglio lo spazio disponibile. Il wc è nascosto dietro un pannello scorrevole, mentre la doccia è installata in un angolo.");
                descBagno.add("Il bagno è essenziale ma confortevole. Ha un wc chimico, un lavandino e una doccia a tenda. Lo spazio è ben illuminato e le pareti sono rivestite in piastrelle.");
                descBagno.add("Il bagno è spazioso e confortevole. Ha un wc chimico, un lavandino, una doccia con soffione a pioggia e una finestra. Lo spazio è ben organizzato e tutto è a portata di mano.");
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

                listaMarche.add("Mercedes-Benz");
                listaMarche.add("Volkswagen");
                listaMarche.add("Fiat");
                listaMarche.add("Jeep");
                listaMarche.add("Mercedes-Benz");
                listaMarche.add("Volkswagen");
                listaMarche.add("Fiat");
                listaMarche.add("Jeep");
                listaMarche.add("Jeep");
                listaMarche.add("Volkswagen");

                a.setHouseNumber(r.nextInt(1, 100));
                a.setStreet(f.address().streetAddress(false));
                a.setZipCode(r.nextInt(10000, 99999));
                a.setTown(townsRepository.findById(r.nextLong(1, 7300)).orElseThrow(() -> new NotFoundEx("error in runner")));
                addressesRepository.save(a);
                c.setName(f.name().firstName());
                c.setSurname(f.name().lastName());
                c.setDayOfBirth(LocalDate.of(r.nextInt(1950, 2003), r.nextInt(1, 12), r.nextInt(1, 28)));
                c.setEmail(c.getName().toLowerCase() + "." + c.getSurname().toLowerCase() + "@gmail.com");
                c.setAvatar("https://api.dicebear.com/7.x/personas/svg?seed=" + c.getName());
                c.setCover("https://ui-avatars.com/api/?name=" + c.getName() + "+" + c.getSurname());
                c.setPassword(passwordEncoder.encode("1234"));
                c.setAddressesCustomer(a);
                usersRepository.save(c);

                n.setObject("Benvenuto nel sito VanWorld");
                n.setText("Complimenti, ora sei un membro di VanWorld e potrai noleggiare Van per le tue vacanze, dai un occhiata ai mezzi disponibili");
                n.setSender(admin);
                n.setState(simonedangelo.mondovan.Notification.Enum.Status.NOT_READ);
                n.setReceiver(c);
                notificationsRepository.save(n);

                switch (i) {
                    case 0: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Al confine del Mediterraneo");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702570498/euwbwmiro8prqdegcvgj.jpg");
                        p.setText("Bellissimo viaggio fino alla fine dell'Europa con il Van preso a noleggio su VanWorld");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 1: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Un orso inaspettato nel nostro bnb!");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702562146/mezphz8ur9fcc5asicau.jpg");
                        p.setText("Durante il nostro recente soggiorno in un bnb con van, abbiamo avuto un incontro ravvicinato con un ospite un po' particolare: un orso impagliato");
                        p.setCategory(Category.RECOMMENDED_TRIPS);
                        postsRepository.save(p);
                        break;
                    }
                    case 2: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Posto fantastico!!");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702570812/bovtmg1k1qmerawujxs3.jpg");
                        p.setText("Durante il nostro recente viaggio in spagna abbiamo trovato questo posto stupendo. Un campeggio riva costa dove soggiornare con il van preso con VanWorld");
                        p.setCategory(Category.TRAVELERS_STORY);
                        postsRepository.save(p);
                        break;
                    }
                    case 3: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Montagne alberate");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702570670/iev7abphlpg4incbyeek.jpg");
                        p.setText("Con il Van preso in noleggio abbiamo passato un meraviglio week end immersi nel verde delle montagne!");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 4: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Il Castello di Contignaco");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/08/20230830-Emilia-Romagna-Salsomaggiore-Castello-di-Cotignaco.jpg");
                        p.setText("Fu costruito all’inizio dell’XI secolo da Adalberto Pallavicino e fu conquistato dagli Aldighieri di Parma nel 1315, che miravano a controllare le saline e sfruttare l’acqua delle Terme di Salsomaggiore per la produzione di sale. Gli Aldighieri vantano una presunta discendenza dal celebre poeta Dante Alighieri.\n" +
                                "\n" +
                                "La leggenda narra che proprio il sommo poeta soggiornò in questo maestoso maniero durante gli anni dell’esilio, lasciando un’impronta indelebile nella storia del castello. Il castello si presta a tutta quelle attività come degustazioni di vini o cene romantiche ed è una tappa ideale per chi, dopo una lunga giornata di cure termale vuole rifugiarsi in un luogo dal panorama mozzafiato.");
                        p.setCategory(Category.RECOMMENDED_TRIPS);
                        postsRepository.save(p);
                        break;
                    }
                    case 5: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("La Rotta dei Due Mari, da Polignano a Mare a Taranto");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/11/20231025-Puglia-Rotta-dei-Due-Mari-Polignano-a-Mare-Cala-Paura.jpg");
                        p.setText("Sei uno a cui piace camminare da solo o in compagnia? Lentamente, dall’Adriatico allo Ionio puoi affrontare un facile percorso a tappe. Si chiama Rotta dei Due Mari (qui il sito ufficiale), si trova in Puglia e attraversa grotte, trulli e masserie. Nel mezzo i borghi, nessuno uguale all’altro e ognuno particolare a modo suo. Calza gli scarponi, indossa lo zaino: e accendi il motore del camper. Questa è molto di più di una semplice vacanza. Il sentiero è delineato dai muretti a secco che cingono ulivi secolari e qualche trullo che funge da ricovero per gli attrezzi agricoli del contadino. Una segnaletica rossa e blu in vernice ecologica indica il percorso di 136 chilometri che si articola in sei tappe. Dall’Adriatico allo Ionio si cammina lentamente lungo la ciclovia dell’acquedotto pugliese attraversando il Parco Naturale delle Pianelle, la più grande riserva naturale dell’area fino a raggiungere il mare di Taranto.\n" +
                                "\n" +
                                "A piedi da Polignano a Mare a Taranto, antica colonia della Magna Grecia anche chiamata “città dei due mari” per la sua posizione, stretta tra Mar Grande e Mar Piccolo. Nel mezzo i borghi.");
                        p.setCategory(Category.TRAVELERS_STORY);
                        postsRepository.save(p);
                        break;
                    }
                    case 6: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Ciclovia del Sole, l'Emilia-Romagna tra pedalate spensierate!!");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2022/05/20220527_Ciclovia-del-Sole-fra-Crevalcore-e-Osteria-Nuova-di-Sala-Bolognese2.jpg");
                        p.setText("È stata inaugurata nell’aprile del 2021 e da subito ha richiamato a sé numerosi amanti della bicicletta: dai ciclisti della domenica agli sportivi in allenamento, dai viaggiatori sui pedali di lunga percorrenza alle famiglie con bambini in gita. La Ciclovia del Sole collega in circa quaranta chilometri Mirandola alla frazione Osteria Nuova di Sala Bolognese attraversando gli orizzonti piatti della pianura emiliana e ripercorrendo una parte del tracciato dell’ex ferrovia Bologna-Verona. " +
                                "Il percorso, che si sviluppa in buona parte su pista ciclabile e a volte su stradine a basso traffico, è ben segnalato e ha quale simbolo guida un vivace sole color giallo. Ai lati della pista si alternano distese di campi coltivati, zone di interesse naturalistico, antichi borghi e piccole cittadine tutti contrassegnati da ritmi lenti e spiccate tradizioni locali. " +
                                "Da Osteria Nuova di Sala Bolognese con altri dieci chilometri di pedalata su un percorso di collegamento provvisorio consentono di arrivare trionfalmente alla Città dei Portici, Bologna. Di seguito vi proponiamo la nostra esperienza lungo la Ciclovia del Sole effettuata con la modalità camper più bici.");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 7: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Langhe in camper tra i vigneti da Barolo ad Alba");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2022/11/20221026-Piemonte-Langhe-Barolo-Veduta-panoramica-verso-il-borgo.jpg");
                        p.setText("L'autunno e la primavera sono i periodi migliori per girare i borghi delle Langhe in camper. Da Barolo ad Alba un itinerario fra vigne, musei e cantine. Le mezze stagioni sono il periodo migliore per gironzolare in camper sulle colline delle Langhe tra vigne, borghi e piccoli musei. Con il foliage autunnale o le belle giornate di sole primaverili è piacevole osservare la strada che sale leggermente per raggiungere i paesi raggomitolati sui pendii. Durante il nostro itinerario da Barolo ad Alba sostiamo nel confortevole agricampeggio La Rosa del Borgo a La Morra.");
                        p.setCategory(Category.TRAVELERS_STORY);
                        postsRepository.save(p);
                        break;
                    }
                    case 8: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Cosa vedere a Venezia a Natale, sogno d'acqua e pietra");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/05/2023-05-09-Venezia_Lo-squero-di-San-Trovaso_28.jpg");
                        p.setText("Luci sulla laguna: in occasione delle festività di fine anno il fascino di canali e campielli si accende di un’atmosfera unica. Scopriamo, o riscopriamo, una città senza pari: vivete l’autentico spirito di Venezia approfittando delle tante proposte culturali che offre e... non scordate il vostro camper! E’ il sogno di milioni di persone, da secoli. È la meta agognata di schiere di pittori, scrittori, fotografi e di appassionati viaggiatori provenienti da ogni parte del mondo, “un sogno di acqua e di pietra” come chiosò Wolfgang Goethe. Per chi scrive risulta addirittura imbarazzante scegliere le parole giuste per evitare di rivestire Venezia di retorica e raccontare questa bellezza impareggiabile e sfacciata senza cadere nell’ovvio.");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 9: {
                        Post p = new Post();
                        p.setAuthor(c);
                        p.setTitle("Alta Pusteria in camper e bici: giro alle Tre Cime di Lavaredo");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/09/20230627-Trentino-Alto-Adige-San-Candido-e-Sesto-Val-Campo-di-Dentro-Escursionisti-in-mountain-bike-Mauro-Toccaceli.jpg");
                        p.setText("I centri storici apprezzati dalla nobiltà austroungarica, le opportunità per l’outdoor, i paesaggi dolomitici tutelati dall’Unesco. La vallata che si estende all’estremo nordest altoatesino invita a una vacanza en plein air davvero per tutti. È una bella giornata d’estate e l’isola pedonale di San Candido è animata da un via vai di vacanzieri in cerca di tranquillità e aria buona. Non a caso: l’Alta Val Pusteria è una delle mete più gettonate del territorio altoatesino. Un tempo questa era terra austriaca, e lo dimostra l’uso del tedesco come lingua principale. Già nella seconda metà dell’Ottocento, con la costruzione della ferrovia che univa Fortezza a Maribor, nell’attuale Slovenia, la zona divenne luogo di villeggiatura frequentato dall’élite austro-ungarica. " +
                                "La bellezza delle sue località, insieme alla natura del Parco Naturale Tre Cime, ne fanno una meta ideale per gli amanti dell’outdoor. D’inverno è un paradiso per lo sci di fondo con oltre 200 chilometri di piste, nella stagione estiva è il regno delle passeggiate a piedi e in bicicletta su percorsi d’ogni tipo e difficoltà: il tutto al cospetto delle Dolomiti.");
                        p.setCategory(Category.RECOMMENDED_TRIPS);
                        postsRepository.save(p);
                        break;
                    }
                }


                b.setHouseNumber(r.nextInt(1, 100));
                b.setStreet(f.address().streetAddress(false));
                b.setZipCode(r.nextInt(10000, 99999));
                b.setTown(townsRepository.findById(r.nextLong(1, 7300)).orElseThrow(() -> new NotFoundEx("error in runner")));
                addressesRepository.save(b);
                o.setName(f.name().firstName());
                o.setSurname(f.name().lastName());
                o.setDayOfBirth(LocalDate.of(r.nextInt(1950, 2003), r.nextInt(1, 12), r.nextInt(1, 28)));
                o.setEmail(o.getName().toLowerCase() + "." + o.getSurname().toLowerCase() + "@gmail.com");
                o.setAvatar("https://api.dicebear.com/7.x/personas/svg?seed=" + o.getName());
                o.setCover("https://ui-avatars.com/api/?name=" + o.getName() + "+" + o.getSurname());
                o.setPassword(passwordEncoder.encode("1234"));
                o.setAddressesOwner(b);
                usersRepository.save(o);

                e.setObject("Benvenuto nel sito VanWorld");
                e.setText("Complimenti, ora sei un membro di VanWorld e potrai mettere in noleggio il tuo van, continua nella sezione il tuo veicolo");
                e.setSender(admin);
                e.setState(simonedangelo.mondovan.Notification.Enum.Status.NOT_READ);
                e.setReceiver(o);
                notificationsRepository.save(e);

                switch (i) {
                    case 0: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Al confine del Mediterraneo");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702570498/euwbwmiro8prqdegcvgj.jpg");
                        p.setText("Bellissimo viaggio fino alla fine dell'Europa con il Van preso a noleggio su VanWorld");
                        p.setCategory(Category.RECOMMENDED_TRIPS);
                        postsRepository.save(p);
                        break;
                    }
                    case 1: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Un orso inaspettato nel nostro bnb!");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702562146/mezphz8ur9fcc5asicau.jpg");
                        p.setText("Durante il nostro recente soggiorno in un bnb con van, abbiamo avuto un incontro ravvicinato con un ospite un po' particolare: un orso impagliato");
                        p.setCategory(Category.TRAVELERS_STORY);
                        postsRepository.save(p);
                        break;
                    }
                    case 2: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Posto fantastico!!");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702570812/bovtmg1k1qmerawujxs3.jpg");
                        p.setText("Durante il nostro recente viaggio in spagna abbiamo trovato questo posto stupendo. Un campeggio riva costa dove soggiornare con il van preso con VanWorld");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 3: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Montagne alberate");
                        p.setImg("http://res.cloudinary.com/dhwybes2b/image/upload/v1702570670/iev7abphlpg4incbyeek.jpg");
                        p.setText("Con il Van preso in noleggio abbiamo passato un meraviglio week end immersi nel verde delle montagne!");
                        p.setCategory(Category.TRAVELERS_STORY);
                        postsRepository.save(p);
                        break;
                    }
                    case 4: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Il Castello di Contignaco");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/08/20230830-Emilia-Romagna-Salsomaggiore-Castello-di-Cotignaco.jpg");
                        p.setText("Fu costruito all’inizio dell’XI secolo da Adalberto Pallavicino e fu conquistato dagli Aldighieri di Parma nel 1315, che miravano a controllare le saline e sfruttare l’acqua delle Terme di Salsomaggiore per la produzione di sale. Gli Aldighieri vantano una presunta discendenza dal celebre poeta Dante Alighieri.\n" +
                                "\n" +
                                "La leggenda narra che proprio il sommo poeta soggiornò in questo maestoso maniero durante gli anni dell’esilio, lasciando un’impronta indelebile nella storia del castello. Il castello si presta a tutta quelle attività come degustazioni di vini o cene romantiche ed è una tappa ideale per chi, dopo una lunga giornata di cure termale vuole rifugiarsi in un luogo dal panorama mozzafiato.");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 5: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("La Rotta dei Due Mari, da Polignano a Mare a Taranto");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/11/20231025-Puglia-Rotta-dei-Due-Mari-Polignano-a-Mare-Cala-Paura.jpg");
                        p.setText("Sei uno a cui piace camminare da solo o in compagnia? Lentamente, dall’Adriatico allo Ionio puoi affrontare un facile percorso a tappe. Si chiama Rotta dei Due Mari (qui il sito ufficiale), si trova in Puglia e attraversa grotte, trulli e masserie. Nel mezzo i borghi, nessuno uguale all’altro e ognuno particolare a modo suo. Calza gli scarponi, indossa lo zaino: e accendi il motore del camper. Questa è molto di più di una semplice vacanza. Il sentiero è delineato dai muretti a secco che cingono ulivi secolari e qualche trullo che funge da ricovero per gli attrezzi agricoli del contadino. Una segnaletica rossa e blu in vernice ecologica indica il percorso di 136 chilometri che si articola in sei tappe. Dall’Adriatico allo Ionio si cammina lentamente lungo la ciclovia dell’acquedotto pugliese attraversando il Parco Naturale delle Pianelle, la più grande riserva naturale dell’area fino a raggiungere il mare di Taranto.\n" +
                                "\n" +
                                "A piedi da Polignano a Mare a Taranto, antica colonia della Magna Grecia anche chiamata “città dei due mari” per la sua posizione, stretta tra Mar Grande e Mar Piccolo. Nel mezzo i borghi.");
                        p.setCategory(Category.RECOMMENDED_TRIPS);
                        postsRepository.save(p);
                        break;
                    }
                    case 6: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Ciclovia del Sole, l'Emilia-Romagna tra pedalate spensierate!!");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2022/05/20220527_Ciclovia-del-Sole-fra-Crevalcore-e-Osteria-Nuova-di-Sala-Bolognese2.jpg");
                        p.setText("È stata inaugurata nell’aprile del 2021 e da subito ha richiamato a sé numerosi amanti della bicicletta: dai ciclisti della domenica agli sportivi in allenamento, dai viaggiatori sui pedali di lunga percorrenza alle famiglie con bambini in gita. La Ciclovia del Sole collega in circa quaranta chilometri Mirandola alla frazione Osteria Nuova di Sala Bolognese attraversando gli orizzonti piatti della pianura emiliana e ripercorrendo una parte del tracciato dell’ex ferrovia Bologna-Verona. " +
                                "Il percorso, che si sviluppa in buona parte su pista ciclabile e a volte su stradine a basso traffico, è ben segnalato e ha quale simbolo guida un vivace sole color giallo. Ai lati della pista si alternano distese di campi coltivati, zone di interesse naturalistico, antichi borghi e piccole cittadine tutti contrassegnati da ritmi lenti e spiccate tradizioni locali. " +
                                "Da Osteria Nuova di Sala Bolognese con altri dieci chilometri di pedalata su un percorso di collegamento provvisorio consentono di arrivare trionfalmente alla Città dei Portici, Bologna. Di seguito vi proponiamo la nostra esperienza lungo la Ciclovia del Sole effettuata con la modalità camper più bici.");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 7: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Langhe in camper tra i vigneti da Barolo ad Alba");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2022/11/20221026-Piemonte-Langhe-Barolo-Veduta-panoramica-verso-il-borgo.jpg");
                        p.setText("L'autunno e la primavera sono i periodi migliori per girare i borghi delle Langhe in camper. Da Barolo ad Alba un itinerario fra vigne, musei e cantine. Le mezze stagioni sono il periodo migliore per gironzolare in camper sulle colline delle Langhe tra vigne, borghi e piccoli musei. Con il foliage autunnale o le belle giornate di sole primaverili è piacevole osservare la strada che sale leggermente per raggiungere i paesi raggomitolati sui pendii. Durante il nostro itinerario da Barolo ad Alba sostiamo nel confortevole agricampeggio La Rosa del Borgo a La Morra.");
                        p.setCategory(Category.TRAVELERS_STORY);

                        postsRepository.save(p);
                        break;
                    }
                    case 8: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Cosa vedere a Venezia a Natale, sogno d'acqua e pietra");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/05/2023-05-09-Venezia_Lo-squero-di-San-Trovaso_28.jpg");
                        p.setText("Luci sulla laguna: in occasione delle festività di fine anno il fascino di canali e campielli si accende di un’atmosfera unica. Scopriamo, o riscopriamo, una città senza pari: vivete l’autentico spirito di Venezia approfittando delle tante proposte culturali che offre e... non scordate il vostro camper! E’ il sogno di milioni di persone, da secoli. È la meta agognata di schiere di pittori, scrittori, fotografi e di appassionati viaggiatori provenienti da ogni parte del mondo, “un sogno di acqua e di pietra” come chiosò Wolfgang Goethe. Per chi scrive risulta addirittura imbarazzante scegliere le parole giuste per evitare di rivestire Venezia di retorica e raccontare questa bellezza impareggiabile e sfacciata senza cadere nell’ovvio.");
                        p.setCategory(Category.MY_VAN);
                        postsRepository.save(p);
                        break;
                    }
                    case 9: {
                        Post p = new Post();
                        p.setAuthor(o);
                        p.setTitle("Alta Pusteria in camper e bici: giro alle Tre Cime di Lavaredo");
                        p.setImg("https://www.pleinair.it/wp-content/uploads/2023/09/20230627-Trentino-Alto-Adige-San-Candido-e-Sesto-Val-Campo-di-Dentro-Escursionisti-in-mountain-bike-Mauro-Toccaceli.jpg");
                        p.setText("I centri storici apprezzati dalla nobiltà austroungarica, le opportunità per l’outdoor, i paesaggi dolomitici tutelati dall’Unesco. La vallata che si estende all’estremo nordest altoatesino invita a una vacanza en plein air davvero per tutti. È una bella giornata d’estate e l’isola pedonale di San Candido è animata da un via vai di vacanzieri in cerca di tranquillità e aria buona. Non a caso: l’Alta Val Pusteria è una delle mete più gettonate del territorio altoatesino. Un tempo questa era terra austriaca, e lo dimostra l’uso del tedesco come lingua principale. Già nella seconda metà dell’Ottocento, con la costruzione della ferrovia che univa Fortezza a Maribor, nell’attuale Slovenia, la zona divenne luogo di villeggiatura frequentato dall’élite austro-ungarica. " +
                                "La bellezza delle sue località, insieme alla natura del Parco Naturale Tre Cime, ne fanno una meta ideale per gli amanti dell’outdoor. D’inverno è un paradiso per lo sci di fondo con oltre 200 chilometri di piste, nella stagione estiva è il regno delle passeggiate a piedi e in bicicletta su percorsi d’ogni tipo e difficoltà: il tutto al cospetto delle Dolomiti.");
                        p.setCategory(Category.RECOMMENDED_TRIPS);
                        postsRepository.save(p);
                        break;
                    }
                }

                v.setOwner(o);
                v.setName(f.funnyName().name());
                v.setBrand(listaMarche.get(i));
                v.setModel(v.getBrand().equals("Volkswagen") ? "T" + r.nextInt(1, 6) : v.getBrand().equals("Mercedes-Benz") ? "Sprinter" : v.getBrand().equals("Jeep") ? "Renegade" : v.getBrand().equals("Fiat") ? "Ducato" : "Multipla");
                v.setDisplacement(r.nextInt(1600, 2500));
                v.setSits(r.nextInt(2, 6));
                v.setKilometers(r.nextLong(100000, 500000));
                v.setFirstEnrollment(LocalDate.of(r.nextInt(1950, 2003), r.nextInt(1, 12), r.nextInt(1, 28)));
                if (v.getBrand().equals("Volkswagen")) {
                    v.setType(Type.VAN);
                    if (i % 2 == 0) {
                        v.setSupply(Supply.DIESEL);
                        v.setLicense(License.B);
                        v.setTransmission(Transmission.MANUAL);
                    } else {
                        v.setSupply(Supply.ELECTRIC);
                        v.setLicense(License.C);
                        v.setTransmission(Transmission.AUTO);
                    }
                } else if (v.getBrand().equals("Mercedes-Benz")) {
                    v.setType(Type.VAN);
                    v.setSupply(Supply.HYBRID);
                    v.setLicense(License.B);
                    v.setTransmission(Transmission.SEMI_AUTO);
                } else if (v.getBrand().equals("Jeep")) {
                    v.setType(Type.CAMPERIZED_JEEP);
                    v.setSupply(Supply.LPG_DIESEL);
                    v.setLicense(License.C);
                    v.setTransmission(Transmission.MANUAL);
                } else if (v.getBrand().equals("Fiat")) {
                    if (i % 2 == 0) {
                        v.setType(Type.CAMPER);
                        v.setSupply(Supply.DIESEL);
                        v.setLicense(License.B);
                        v.setTransmission(Transmission.MANUAL);
                    } else {
                        v.setType(Type.ROOFTOOP_CAR);
                        v.setSupply(Supply.GASOLINE);
                        v.setLicense(License.B);
                        v.setTransmission(Transmission.MANUAL);
                    }
                }

                v.setHeight(r.nextInt(2, 3));
                v.setLength(r.nextInt(2, 4));
                v.setWidth(r.nextInt(1, 2));
                v.setPricePerDay(r.nextInt(80, 200));
                v.setShortDescriptions(r.nextInt() % 2 == 0 ? "Vacanze in liberta con " + v.getName() : "Comodita garantita, ovunque e comunuque!");
                List<String> lS = List.of("https://res.cloudinary.com/dhwybes2b/image/upload/v1702053853/qtx9eypbotnbyc9w84ko.jpg", "https://res.cloudinary.com/dhwybes2b/image/upload/v1702053853/qtx9eypbotnbyc9w84ko.jpg", "https://res.cloudinary.com/dhwybes2b/image/upload/v1702053853/qtx9eypbotnbyc9w84ko.jpg");
                v.setAvatar(lS);
                vehiclesRepository.save(v);

                for (int j = 0; j < 366; j++) {
                    ServiceStatus s = new ServiceStatus();
                    s.setDate(LocalDate.now().plusDays(j));
                    s.setState(Status.AVAILABLE);
                    s.setVehicle(v);
                    servicesStatusRepository.save(s);
                }

                vA.setBeds(r.nextInt(2, 6));
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
                v.setAnnouncement("Noleggia il mio " + v.getType() + " per la tua prossima vacanza! Questo van è perfetto per tutte le attività che desideri svolgere in vacanza. È spazioso e confortevole, con " + vA.getBeds() + " posti letto e " + v.getSits() + " posti a sedere.");
                vehiclesRepository.save(v);
            }
            System.err.println("Finito!");
        }
    }
}
