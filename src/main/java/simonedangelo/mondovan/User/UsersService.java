package simonedangelo.mondovan.User;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import simonedangelo.mondovan.Address.AddressesCustomer;
import simonedangelo.mondovan.Address.AddressesOwner;
import simonedangelo.mondovan.Address.AddressesRepository;
import simonedangelo.mondovan.Address.Town.Town;
import simonedangelo.mondovan.Address.Town.TownsRepository;
import simonedangelo.mondovan.Beans.EmailsServices;
import simonedangelo.mondovan.Beans.Security.JWTTools;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Exceptions.UnauthorizedEx;
import simonedangelo.mondovan.Notification.Enum.Status;
import simonedangelo.mondovan.Notification.Notification;
import simonedangelo.mondovan.Notification.NotificationsRepository;
import simonedangelo.mondovan.User.Customer.Customer;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.Payload.UsersDTO;
import simonedangelo.mondovan.User.Payload.UsersLoginDTO;

import java.io.IOException;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private EmailsServices emailService;
    @Autowired
    private TownsRepository townsRepository;
    @Autowired
    private AddressesRepository addressesRepository;
    @Autowired
    private NotificationsRepository notificationsRepository;

    public String addAvatar(MultipartFile file, long idUser) throws IOException {
        User u = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        String s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.setAvatar(s);
        usersRepository.save(u);
        return s;
    }

    public String addCover(MultipartFile file, long idUser) throws IOException {
        User u = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        String s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.setCover(s);
        usersRepository.save(u);
        return s;
    }


    public User addFriend(long idFriend, User u) {
        User f = usersRepository.findById(idFriend).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        User user = usersRepository.findById(u.getId()).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        List<Long> lU = user.getFriends();
        lU.add(f.getId());
        u.setFriends(lU);

        return usersRepository.save(u);
    }

    public User removeFriend(long idFriend, User u) {
        User f = usersRepository.findById(idFriend).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        User user = usersRepository.findById(u.getId()).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        List<Long> lU = user.getFriends();
        lU.remove(f.getId());
        u.setFriends(lU);
        usersRepository.save(user);
        return user;
    }


/*    public List<User> getMyFriend(User u) {
        User f = usersRepository.findById(u.getId()).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        return f.getFriends();
    }*/


    public User getUserById(long idUser) throws NotFoundEx {
        return usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
    }

    public User getUserByEmail(String email) throws NotFoundEx {
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundEx("The searched user by email does not exist"));
    }

    public String autenticateUsers(UsersLoginDTO login) {
        User u = usersRepository.findByEmail(login.email()).orElseThrow(() -> new NotFoundEx("email not found"));
        if (passwordEncoder.matches(login.password(), u.getPassword())) {
            return jwtTools.createToken(u);
        } else {
            throw new UnauthorizedEx("incorrected credentials");
        }
    }

    public Page<User> getPageUsers(int page, int size, String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return usersRepository.findAll(p);
    }

    public Customer customerRegister(UsersDTO userObj) throws IOException {
        List<User> users = usersRepository.findAll();
        User admin = this.getUserByEmail("admin.admin@vanworld.com");
        for (User u : users) {
            if (u.getEmail().equals(userObj.email())) {
                throw new BadRequestEx("this user already exist");
            }
        }
        Town t = townsRepository.findById(userObj.idTown()).orElseThrow(() -> {
            System.out.println(userObj.idTown());
            return new NotFoundEx("server got problems whit towns");
        });
        AddressesCustomer a = new AddressesCustomer();
        a.setHouseNumber(userObj.houseNumber());
        a.setStreet(userObj.street());
        a.setZipCode(userObj.zipCode());
        a.setTown(t);
        addressesRepository.save(a);
        Customer c = new Customer();
        c.setAddressesCustomer(a);
        c.setName(userObj.name());
        c.setSurname(userObj.surname());
        c.setDayOfBirth(userObj.dayOfBirth());
        c.setAvatar("https://ui-avatars.com/api/?name=" + userObj.name() + "+" + userObj.surname());
        c.setCover("https://ui-avatars.com/api/?name=" + userObj.name() + "+" + userObj.surname());
        c.setEmail(userObj.email());
        c.setPassword(passwordEncoder.encode(userObj.password()));
        if (c.getEmail().equals("simone.dangelo636@gmail.com")) {
            emailService.sendEmail(c.getEmail(),
                    "Registration with Van World was successful",
                    "Welcome " + c.getName() + " " + c.getSurname() +
                            "\n your customer account has been created successfully");
        }
        usersRepository.save(c);
        Notification n = new Notification();
        n.setObject("Benvenuto nel sito VanWorld");
        n.setText("Complimenti, ora sei un membro di VanWorld e potrai noleggiare Van per le tue vacanze, dai un occhiata ai mezzi disponibili");
        n.setSender(admin);
        n.setState(Status.NOT_READ);
        n.setReceiver(c);
        notificationsRepository.save(n);
        return c;
    }

    public Owner ownersRegister(UsersDTO userObj) throws IOException {
        List<User> users = usersRepository.findAll();
        User admin = this.getUserByEmail("admin.admin@vanworld.com");
        for (User u : users) {
            if (u.getEmail().equals(userObj.email())) {
                throw new BadRequestEx("This user already exist whit id: " + u.getId());
            }
        }
        Town t = townsRepository.findById(userObj.idTown()).orElseThrow(() -> {
            System.out.println(userObj.idTown());
            return new NotFoundEx("server got problems whit towns");
        });
        AddressesOwner a = new AddressesOwner();
        a.setHouseNumber(userObj.houseNumber());
        a.setStreet(userObj.street());
        a.setZipCode(userObj.zipCode());
        a.setTown(t);
        addressesRepository.save(a);
        Owner o = new Owner();
        o.setAddressesOwner(a);
        o.setName(userObj.name());
        o.setSurname(userObj.surname());
        o.setDayOfBirth(userObj.dayOfBirth());
        o.setAvatar("https://ui-avatars.com/api/?name=" + userObj.name() + "+" + userObj.surname());
        o.setCover("https://ui-avatars.com/api/?name=" + userObj.name() + "+" + userObj.surname());
        o.setEmail(userObj.email());
        o.setPassword(passwordEncoder.encode(userObj.password()));
        if (o.getEmail().equals("simone.dangelo636@gmail.com")) {
            emailService.sendEmail(o.getEmail(),
                    "Registration with Van World was successful",
                    "Welcome " + o.getName() + " " + o.getSurname() +
                            "\n your owner account has been created successfully");
        }
        usersRepository.save(o);
        Notification n = new Notification();
        n.setObject("Benvenuto nel sito VanWorld");
        n.setText("Complimenti, ora sei un membro di VanWorld e potrai mettere in noleggio il tuo van, continua nella sezione il tuo veicolo");
        n.setSender(admin);
        n.setState(Status.NOT_READ);
        n.setReceiver(o);
        notificationsRepository.save(n);
        return o;
    }
}


