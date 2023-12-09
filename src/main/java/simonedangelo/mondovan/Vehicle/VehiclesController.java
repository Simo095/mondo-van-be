package simonedangelo.mondovan.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.Vehicle.Payload.VehiclesDTO;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {
    @Autowired
    private VehiclesService vehiclesService;

    @GetMapping("/my_vehicle")
    @PreAuthorize("hasAuthority('OWNER')")
    public Vehicle getVehicle(@AuthenticationPrincipal Owner owner) {
        return vehiclesService.getVehicleByIdOwner(owner.getId());
    }

    @DeleteMapping("/my_vehicle")
    @PreAuthorize("hasAuthority('OWNER')")
    public void deleteVehicle(@AuthenticationPrincipal Owner owner) {
        vehiclesService.deleteVehicle(owner.getId());
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public Page<Vehicle> getAllVehicle(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "16") int size,
                                       @RequestParam(defaultValue = "id") String sort) {
        return vehiclesService.getPageVehicles(page, size, sort);
    }

    @GetMapping("/{province}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public Page<Vehicle> findByAddressOwner(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "16") int size,
                                            @RequestParam(defaultValue = "id") String sort,
                                            @PathVariable String province) {
        List<Vehicle> vehiclesList = vehiclesService.vehiclesByProvince(province);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @GetMapping("/available")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public Page<Vehicle> vehicleAvailable(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "16") int size,
                                          @RequestParam(defaultValue = "id") String sort) throws Exception {
        List<Vehicle> vehiclesList = vehiclesService.getByAvailability();
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }


    @PostMapping("/register_vehicle")
    @PreAuthorize("hasAuthority('OWNER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle saveVehicles(@AuthenticationPrincipal User user, @RequestBody @Validated VehiclesDTO objV, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return vehiclesService.saveVehicle(objV, user.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());

    }
    
    @PatchMapping("/upload_img")
    @PreAuthorize("hasAuthority('OWNER')")
    public String uploadImg(@RequestParam("img") MultipartFile registrationDocument, @AuthenticationPrincipal User loggedUser) throws IOException {
        System.out.println(registrationDocument.getSize());
        System.out.println(registrationDocument.getContentType());
        return vehiclesService.addAvatarVehicles(registrationDocument, loggedUser.getId());
    }

    @DeleteMapping("/remove_img")
    @PreAuthorize("hasAuthority('OWNER')")
    public void removeImg(@RequestParam("urlImg") String remove, @AuthenticationPrincipal User loggedUser) throws IOException {
        vehiclesService.removeAvatarVehicles(loggedUser.getId(), remove);
    }

}
