package simonedangelo.mondovan.Vehicle;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.User.UsersRepository;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesDTO;

import java.io.IOException;
import java.util.List;

@Service
public class VehiclesService {
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Vehicle saveVehicle(VehiclesDTO obj, long idOwner) throws IOException {
        User u = usersRepository.findById(idOwner).orElseThrow(
                () -> new NotFoundEx("this user does not exist"));
        Owner o = null;
        if (u instanceof Owner) {
            o = (Owner) u;
        } else {
            throw new BadRequestEx("the user logged isn't a OWNER");
        }

        Vehicle v = new Vehicle();
        v.setOwner(o);
        v.setName(obj.name());
        v.setModel(obj.model());
        v.setBrand(obj.brand());

        v.setDisplacement(obj.displacement());
        v.setKilometers(obj.kilometers());
        v.setFirstEnrollment(obj.firstEnrollment());

        v.setType(obj.type());
        v.setHeight(obj.height());
        v.setLength(obj.length());
        v.setWidth(obj.width());
        v.setLicense(obj.license());
        v.setPlate(obj.plate());
        v.setSupply(obj.supply());
        return vehiclesRepository.save(v);
    }

    public Page<Vehicle> getPageVehicles(int page, int size, String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return vehiclesRepository.findAll(p);
    }

    public Vehicle getVehicleByIdOwner(long idOwner) throws NotFoundEx {
        return vehiclesRepository.findByIdOwner(idOwner).orElseThrow(
                () -> new BadRequestEx("this user has no vehicles assigned"));
    }

    public String addAvatarVehicles(MultipartFile file, long idOwner) throws IOException {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        String s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        List<String> lS = v.getAvatar();
        if (lS.size() <= 2) {
            lS.add(s);
            v.setAvatar(lS);
            vehiclesRepository.save(v);
        } else {
            throw new BadRequestEx("three photos already uploaded");
        }
        return s;
    }

    public String addRegistrationDocumentVehicles(MultipartFile file, long idOwner) throws IOException {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        String s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        v.setRegistrationDocument(s);
        vehiclesRepository.save(v);
        return s;
    }

    public void removeAvatarVehicles(long idOwner) {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        if (v.getAvatar().isEmpty()) {
            throw new BadRequestEx("no photos present");
        }
    }
}
