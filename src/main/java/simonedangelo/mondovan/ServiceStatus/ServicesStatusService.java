package simonedangelo.mondovan.ServiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.Vehicle.Vehicle;
import simonedangelo.mondovan.Vehicle.VehiclesService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ServicesStatusService {
    @Autowired
    private ServicesStatusRepository servicesStatusRepository;
    @Autowired
    private VehiclesService vehiclesService;

    public Map<Long, String> calendarAvailable(long idOwner) throws Exception {
        Vehicle v = vehiclesService.getVehicleByIdOwner(idOwner);
        List<ServiceStatus> availability = servicesStatusRepository.findByIdVehicle(v.getId());
        if (availability == null || availability.isEmpty()) {
            throw new NotFoundEx("no availability for this vehicle");
        } else {
            Map<Long, String> map = new TreeMap<>();
            int i = 0;
            for (ServiceStatus a : availability) {
                map.put(a.getId(), a.getState() + "," + a.getDate());
                i++;
            }
            return map;
        }
    }

    public ServiceStatus modifySetAvailabilities(long idOwner, long idServiceStatus) {
        Vehicle v = vehiclesService.getVehicleByIdOwner(idOwner);
        ServiceStatus s = servicesStatusRepository.findById(idServiceStatus).orElseThrow(
                () -> new NotFoundEx("no availability for this id"));
        System.out.println(v.getId());
        System.out.println(s.getVehicle().getId());
        if (v.getId() == s.getVehicle().getId()) {
            if (s.getState() == Status.AVAILABLE) {
                s.setState(Status.NOT_AVAILABLE);
                return servicesStatusRepository.save(s);
            } else if (s.getState() == Status.NOT_AVAILABLE) {
                s.setState(Status.AVAILABLE);
                return servicesStatusRepository.save(s);
            } else {
                throw new BadRequestEx("Something went wrong");
            }
        } else {
            throw new BadRequestEx("this use cant update this availabilities");
        }
    }

    public List<ServiceStatus> getByAvailabilityAndRangeDate(LocalDate start, LocalDate end) {
        return servicesStatusRepository.findByRangeDate(start, end).orElseThrow(
                () -> new NotFoundEx("there are no vans available in this range")
        );
    }
}
