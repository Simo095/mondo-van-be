package simonedangelo.mondovan.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesDTO;

import java.io.IOException;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {
    @Autowired
    private VehiclesService vehiclesService;

    @PostMapping("/register_vehicle")
    @PreAuthorize("hasAuthority('OWNER')")
    public Vehicle saveVehicles(@AuthenticationPrincipal User user, @RequestBody @Validated VehiclesDTO objV, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return vehiclesService.saveVehicle(objV, user.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());

    }

    @PatchMapping("/upload")
    @PreAuthorize("hasAuthority('OWNER')")
    public String uploadAvatar(@RequestParam("registrationDocument") MultipartFile registrationDocument, @AuthenticationPrincipal User loggedUser) throws IOException {
        System.out.println(registrationDocument.getSize());
        System.out.println(registrationDocument.getContentType());
        return vehiclesService.addRegistrationDocumentVehicles(registrationDocument, loggedUser.getId());
    }
}
