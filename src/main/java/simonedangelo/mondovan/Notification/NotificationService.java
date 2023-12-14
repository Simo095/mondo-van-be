package simonedangelo.mondovan.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Notification.Enum.Status;
import simonedangelo.mondovan.Notification.Payload.NotificationsDTO;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.User.UsersRepository;
import simonedangelo.mondovan.Vehicle.VehiclesRepository;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationsRepository notificationsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private VehiclesRepository vehiclesRepository;

    public List<Notification> notificationsByUser(User u) {
        return notificationsRepository.findByReceiver(u);
    }

    public Notification getById(long id) {
        return notificationsRepository.findById(id).orElseThrow(() -> new NotFoundEx("Notification not found"));
    }

    public Notification getNotificationsById(User u, long idNotification) {
        Notification n = this.getById(idNotification);
        if (n.getReceiver().getId() == u.getId()) {
            n.setState(Status.READ);
            notificationsRepository.save(n);
            return n;
        } else throw new BadRequestEx("not your notification");
    }

    public Notification createNotificationsFromReservation(NotificationsDTO obj, User sender) throws IOException {
        User receiver = vehiclesRepository.findById(Long.valueOf(obj.receiver())).orElseThrow(() -> new NotFoundEx("vehicle not found")).getOwner();
        Notification n = new Notification();
        n.setState(Status.NOT_READ);
        n.setSender(sender);
        n.setText(obj.text());
        n.setObject(obj.object());
        n.setReceiver(receiver);
        return notificationsRepository.save(n);
    }

    public Notification responseNotification(NotificationsDTO obj, User sender) throws IOException {
        User receiver = usersRepository.findByEmail(obj.receiver()).orElseThrow(() -> new NotFoundEx("user not founds"));
        Notification n = new Notification();
        n.setState(Status.NOT_READ);
        n.setSender(sender);
        n.setText(obj.text());
        n.setObject(obj.object());
        n.setReceiver(receiver);
        return notificationsRepository.save(n);
    }


    public void deleteNotification(User u, long idNotification) {
        Notification n = this.getById(idNotification);
        if (n.getReceiver().getId() == u.getId()) {
            notificationsRepository.delete(n);
        } else throw new BadRequestEx("not your notification");
    }

    public Notification modifyStatus(User u, long idNotification) {
        Notification n = this.getById(idNotification);
        if (n.getReceiver().getId() == u.getId() && n.getState() == Status.NOT_READ) {
            n.setState(Status.READ);
            return notificationsRepository.save(n);
        } else if (n.getReceiver().getId() == u.getId() && n.getState() == Status.READ) {
            n.setState(Status.NOT_READ);
            return notificationsRepository.save(n);
        }
        throw new BadRequestEx("not your notification");
    }
}
