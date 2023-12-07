package simonedangelo.mondovan.ServiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import simonedangelo.mondovan.User.Owner.Owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/availability")
public class ServicesStatusController {
    @Autowired
    private ServicesStatusService servicesStatusService;

    @GetMapping("my_availability")
    @PreAuthorize("hasAuthority('OWNER')")
    public Map<Long, String> calendarAvailability(@AuthenticationPrincipal Owner owner) throws Exception {
        return servicesStatusService.calendarAvailable(owner.getId());
    }

    @GetMapping("/my_date_available")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public Page<ServiceStatus> vehicleAvailableByRangeDate(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "16") int size,
                                                           @RequestParam(defaultValue = "id") String sort,
                                                           @RequestParam() LocalDate start,
                                                           @RequestParam() LocalDate end) throws Exception {
        List<ServiceStatus> vehiclesList = servicesStatusService.getByAvailabilityAndRangeDate(start, end);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(vehiclesList, p, vehiclesList.size());
    }

    @PatchMapping("/{idServiceStatus}")
    @PreAuthorize("hasAuthority('OWNER')")
    public String modifyAvailability(@AuthenticationPrincipal Owner owner, @PathVariable long idServiceStatus) throws Exception {
        servicesStatusService.modifySetAvailabilities(owner.getId(), idServiceStatus);
        return "Modify Status";
    }
}
