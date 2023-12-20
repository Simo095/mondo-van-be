package simonedangelo.mondovan.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Notification.Payload.NotificationsDTO;
import simonedangelo.mondovan.User.User;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER','ADMIN')")
    public List<Notification> getNotificationByUser(@AuthenticationPrincipal User u) {
        return notificationService.notificationsByUser(u);
    }

    @PostMapping("/for_reservation")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER','ADMIN')")
    public Notification sendNotification(@AuthenticationPrincipal User u,
                                         @RequestBody @Validated NotificationsDTO obj, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return notificationService.createNotificationsFromReservation(obj, u);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());
    }

    /*---------INUTILIZZATI*/
    @GetMapping("/{idNotification}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER','ADMIN')")
    public Notification getNotification(@AuthenticationPrincipal User u,
                                        @PathVariable long idNotification) {
        return notificationService.getNotificationsById(u, idNotification);
    }


    @PostMapping("/response")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER','ADMIN')")
    public Notification sendNotificationResponse(@AuthenticationPrincipal User u,
                                                 @RequestBody @Validated NotificationsDTO obj, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return notificationService.responseNotification(obj, u);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());
    }

    /*---------INUTILIZZATI*/
    @PatchMapping("/{idNotification}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER','ADMIN')")
    public Notification modifyStatusNotification(@AuthenticationPrincipal User u,
                                                 @PathVariable long idNotification) {
        return notificationService.modifyStatus(u, idNotification);
    }

    @DeleteMapping("/{idNotification}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNotification(@AuthenticationPrincipal User u,
                                   @PathVariable long idNotification) {
        notificationService.deleteNotification(u, idNotification);
    }
}
