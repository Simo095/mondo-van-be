package simonedangelo.mondovan.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Notification.Notification;
import simonedangelo.mondovan.Notification.NotificationsRepository;
import simonedangelo.mondovan.Reservation.Payload.ReservationsDTO;
import simonedangelo.mondovan.ServiceStatus.Enum.Status;
import simonedangelo.mondovan.ServiceStatus.ServiceStatus;
import simonedangelo.mondovan.ServiceStatus.ServicesStatusRepository;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.User.UsersRepository;
import simonedangelo.mondovan.Vehicle.Vehicle;
import simonedangelo.mondovan.Vehicle.VehiclesRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationsService {
    @Autowired
    private ReservetionsRepository reservetionsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ServicesStatusRepository servicesStatusRepository;
    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private NotificationsRepository notificationsRepository;

    public User getUserById(long idUser) throws NotFoundEx {
        return usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
    }

    public Vehicle getVehicleByIdOwner(long idOwner) throws NotFoundEx {
        return vehiclesRepository.findByIdOwner(idOwner).orElseThrow(
                () -> new BadRequestEx("this user has no vehicles assigned"));
    }

    public Reservation saveReservation(long idUser, ReservationsDTO objReservation, long idVehicle) throws IOException {
        User u = this.getUserById(idUser);
        Vehicle v = vehiclesRepository.findById(idVehicle).orElseThrow(() -> new NotFoundEx("van not found"));
        List<ServiceStatus> filteredServiceStatus = servicesStatusRepository.findByIdVehicle(v.getId()).stream()
                .filter(serviceStatus -> serviceStatus.getDate().equals(objReservation.start())
                        || serviceStatus.getDate().equals(objReservation.end())
                        || (serviceStatus.getDate().isBefore(objReservation.end()) && serviceStatus.getDate().isAfter(objReservation.start())))
                .collect(Collectors.toList());
        List<ServiceStatus> notAvailableList = filteredServiceStatus.stream().filter(serviceStatus ->
                serviceStatus.getState().equals(Status.NOT_AVAILABLE)).toList();
        notAvailableList.forEach(System.out::println);
        if (notAvailableList.isEmpty()) {
            Reservation r = new Reservation();
            r.setUser(u);
            r.setStartDate(objReservation.start());
            r.setEndDate(objReservation.end());
            r.setState(simonedangelo.mondovan.Reservation.Enums.Status.TAKING_CHARGE);

            filteredServiceStatus.forEach(serviceStatus -> {
                serviceStatus.setState(Status.NOT_AVAILABLE);
                servicesStatusRepository.save(serviceStatus);
            });
            Notification n = new Notification();
            n.setSender(u);
            n.setReceiver(v.getOwner());
            n.setObject("Hai una nuova prenotazione da " + u.getName());
            n.setText(u.getName() + " ha prenotato il tuo van, se vuoi accettare la richiesta clicca qui: ");
            n.setState(simonedangelo.mondovan.Notification.Enum.Status.OUTSTANDING);
            notificationsRepository.save(n);
            List<Notification> nL = v.getOwner().getNotifications();
            nL.add(n);
            v.getOwner().setNotifications(nL);
            return reservetionsRepository.save(r);
        } else {
            throw new BadRequestEx("there are dates already selected");
        }


    }
}
