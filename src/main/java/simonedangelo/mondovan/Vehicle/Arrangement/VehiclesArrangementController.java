package simonedangelo.mondovan.Vehicle.Arrangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesArrangementDTO;

import java.io.IOException;

@RestController
@RequestMapping("vehicles_arrangement")
public class VehiclesArrangementController {
    @Autowired
    private VehiclesArrangementService vehiclesArrangementService;

    @GetMapping("my_vehicle")
    @PreAuthorize("hasAuthority('OWNER')")
    public VehiclesArrangement getVehicle(@AuthenticationPrincipal Owner owner) {
        return vehiclesArrangementService.getVehicleArrangementByIdOwner(owner.getId());
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public Page<VehiclesArrangement> getAllVehicle(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "16") int size,
                                                   @RequestParam(defaultValue = "id") String sort) {
        return vehiclesArrangementService.getVehicleArrangement(page, size, sort);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('OWNER')")
    public VehiclesArrangement saveVehiclesArrangement(@AuthenticationPrincipal User user,
                                                       @RequestBody @Validated VehiclesArrangementDTO objV, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return vehiclesArrangementService.saveVehiclesArrangement(user.getId(), objV);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());

    }

    @PutMapping()
    @PreAuthorize("hasAuthority('OWNER')")
    public VehiclesArrangement modifyVehiclesArrangement(@AuthenticationPrincipal User user,
                                                         @RequestBody @Validated VehiclesArrangementDTO objV, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return vehiclesArrangementService.updateVehiclesArrangement(user.getId(), objV);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());

    }

}
