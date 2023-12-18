package simonedangelo.mondovan.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Reservation.Payload.ReservationsDTO;
import simonedangelo.mondovan.User.User;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    @Autowired
    private ReservationsService reservationsService;

    @PostMapping("/{idVehicle}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveVehicles(@PathVariable long idVehicle,
                                    @AuthenticationPrincipal User user,
                                    @RequestBody @Validated ReservationsDTO objR, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return reservationsService.saveReservation(user.getId(), objR, idVehicle);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());

    }

    @DeleteMapping("/{idReservation}")
    @PreAuthorize("hasAuthority('OWNER')")

    public Reservation deleteReservationVehicles(@PathVariable long idReservation,
                                                 @AuthenticationPrincipal User user) {
        return reservationsService.deleteReservation(idReservation);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('OWNER')")
    public Page<Reservation> vehicleAvailable(@AuthenticationPrincipal User u,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "16") int size,
                                              @RequestParam(defaultValue = "id") String sort) throws Exception {
        List<Reservation> reservations = reservationsService.getByVehicleFromOwner(u.getId());
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(reservations, p, reservations.size());
    }

    @GetMapping("/my_reservations")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public Page<Reservation> reservationByUser(@AuthenticationPrincipal User u,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "16") int size,
                                               @RequestParam(defaultValue = "id") String sort) throws Exception {
        List<Reservation> reservations = reservationsService.getByUser(u.getId());
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(reservations, p, reservations.size());
    }

}
