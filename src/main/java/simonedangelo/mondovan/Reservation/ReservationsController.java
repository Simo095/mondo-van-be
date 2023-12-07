package simonedangelo.mondovan.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
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
}
