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
import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.ServiceStatus.Payload.ServicesStatusDTO;
import simonedangelo.mondovan.ServiceStatus.ServiceStatus;
import simonedangelo.mondovan.ServiceStatus.ServicesStatusRepository;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.User.UsersRepository;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesDTO;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesServicesStatusDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiclesService {
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ServicesStatusRepository servicesStatusRepository;
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
        v.setTransmission(obj.transmission());
        v.setShortDescriptions(obj.shortDescriptions());
        vehiclesRepository.save(v);
        for (int i = 0; i < 366; i++) {
            ServiceStatus a = new ServiceStatus();
            a.setDate(LocalDate.now().plusDays(i));
            a.setState(Status.AVAILABLE);
            a.setVehicle(v);
            servicesStatusRepository.save(a);
        }
        return v;
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateByProvinceAndBeds(LocalDate start, LocalDate end, int beds, String province) {
        if (vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateProvinceAndBeds(start, end, beds, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getAvatar(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateOnly(LocalDate start, LocalDate end) {
        if (vehiclesRepository.findByRangeDateOnly(start, end).isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateOnly(start, end)
                .stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getAvatar(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndBeds(LocalDate start, LocalDate end, int beds) {
        if (vehiclesRepository.findByRangeDateAndBeds(start, end, beds).isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndBeds(start, end, beds).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getAvatar(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        } else {
            return collectionVehicleAvailable;
        }
    }

    public List<VehiclesServicesStatusDTO> getByAvailabilityAndRangeDateAndProvince(LocalDate start, LocalDate end, String province) {
        if (vehiclesRepository.findByRangeDateAndProvince(start, end, province).isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        }
        List<VehiclesServicesStatusDTO> collectionVehicleAvailable = vehiclesRepository.findByRangeDateAndProvince(start, end, province).stream()
                .map(vehicle -> {
                    List<ServicesStatusDTO> listServicesDTO = vehicle.getServicesStatus().stream()
                            .filter(serviceStatus -> (serviceStatus.getDate().equals(start) ||
                                    serviceStatus.getDate().equals(end) ||
                                    (serviceStatus.getDate().isBefore(end) && serviceStatus.getDate().isAfter(start))))
                            .map(serviceStatus -> new ServicesStatusDTO(serviceStatus.getId(), serviceStatus.getDate(), serviceStatus.getState()))
                            .collect(Collectors.toList());
                    return new VehiclesServicesStatusDTO(vehicle.getId(), vehicle.getName(), vehicle.getModel(), vehicle.getBrand(), vehicle.getShortDescriptions(), vehicle.getAvatar(), listServicesDTO);
                })
                .filter(vDTO -> !vDTO.listStatus().stream().anyMatch(servicesStatusDTO -> servicesStatusDTO.state().equals(Status.NOT_AVAILABLE)))
                .collect(Collectors.toList());
        if (collectionVehicleAvailable.isEmpty()) {
            throw new NotFoundEx("No avaiability found");
        } else {
            return collectionVehicleAvailable;
        }
    }


    public Page<Vehicle> getPageVehicles(int page, int size, String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return vehiclesRepository.findAll(p);
    }

    public List<Vehicle> vehiclesByProvince(String province) {
        return vehiclesRepository.findByAddressOwner(province).orElseThrow(
                () -> new NotFoundEx("there are no vans available in this city")
        );
    }

    public List<Vehicle> getByAvailability() {
        return vehiclesRepository.findByAvailability().orElseThrow(
                () -> new NotFoundEx("there are no vans available")
        );
    }


    public Vehicle getVehicleByIdOwner(long idOwner) throws NotFoundEx {
        return vehiclesRepository.findByIdOwner(idOwner).orElseThrow(
                () -> new BadRequestEx("this user has no vehicles assigned"));
    }

    public String addAvatarVehicles(MultipartFile file, long idOwner) throws IOException {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        String s = "";
        List<String> lS = v.getAvatar();
        if (lS.size() <= 2) {
            s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
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
        String s = "";
        if (v.getRegistrationDocument().isEmpty()) {
            s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            v.setRegistrationDocument(s);
            vehiclesRepository.save(v);
        } else {
            throw new BadRequestEx("photo already uploaded");
        }
        return s;
    }

    public void removeAvatarVehicles(long idOwner) {
        Vehicle v = this.getVehicleByIdOwner(idOwner);
        if (v.getAvatar().isEmpty()) {
            throw new BadRequestEx("no photos present");
        }
    }
}
