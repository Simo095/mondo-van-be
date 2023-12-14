package simonedangelo.mondovan.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import simonedangelo.mondovan.User.User;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiver(User u);

  /*  @Query("")
    List<Notification> findByIdSender(long idSender);

    @Query("")
    List<Notification> findByIdReceiver(long idReceiver);
 */
}