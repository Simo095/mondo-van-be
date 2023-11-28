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
import simonedangelo.mondovan.Beans.EmailsServices;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Exceptions.UnauthorizedEx;
import simonedangelo.mondovan.Security.JWTTools;
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

    public String getAvatar(MultipartFile file, long idUser) throws IOException {
        User u = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        String s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.setAvatar(s);
        usersRepository.save(u);
        return s;
    }

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
        for (User u : users) {
            if (u.getEmail().equals(userObj.email()) && u.getName().equals(userObj.name())
                    && u.getSurname().equals(userObj.surname())
                    && u.getDayOfBirth().equals(userObj.dayOfBirth())) {
                throw new BadRequestEx("this user already exist");
            }
        }
        Customer c = new Customer();
        c.setName(userObj.name());
        c.setSurname(userObj.surname());
        c.setDayOfBirth(userObj.dayOfBirth());
        c.setAvatar("https://ui-avatars.com/api/?name=" + userObj.name() + "+" + userObj.surname());
        c.setEmail(userObj.email());
        c.setPassword(passwordEncoder.encode(userObj.password()));
        emailService.sendEmail(c.getEmail(),
                "Registration with Van World was successful",
                "Welcome " + c.getName() + " " + c.getSurname() +
                        "\n your customer account has been created successfully");
        return usersRepository.save(c);
    }

    public Owner ownersRegister(UsersDTO userObj) throws IOException {
        List<User> users = usersRepository.findAll();
        for (User u : users) {
            if (u.getEmail().equals(userObj.email()) && u.getName().equals(userObj.name())
                    && u.getSurname().equals(userObj.surname()) && u.getDayOfBirth().equals(userObj.dayOfBirth())) {
                throw new BadRequestEx("This user already exist whit id: " + u.getId());
            }
        }
        Owner o = new Owner();
        o.setName(userObj.name());
        o.setSurname(userObj.surname());
        o.setDayOfBirth(userObj.dayOfBirth());
        o.setAvatar("https://ui-avatars.com/api/?name=" + userObj.name() + "+" + userObj.surname());
        o.setEmail(userObj.email());
        o.setPassword(passwordEncoder.encode(userObj.password()));
        emailService.sendEmail(o.getEmail(),
                "Registration with Van World was successful",
                "Welcome " + o.getName() + " " + o.getSurname() +
                        "\n your owner account has been created successfully");
        return usersRepository.save(o);
    }
}


