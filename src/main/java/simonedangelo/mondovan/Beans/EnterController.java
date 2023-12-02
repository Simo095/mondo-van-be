package simonedangelo.mondovan.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simonedangelo.mondovan.Address.Province.Province;
import simonedangelo.mondovan.Address.Province.ProvincesService;
import simonedangelo.mondovan.Address.Town.Town;
import simonedangelo.mondovan.Address.Town.TownsService;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.User.Customer.Customer;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.Payload.UsersDTO;
import simonedangelo.mondovan.User.Payload.UsersLoggedDTO;
import simonedangelo.mondovan.User.Payload.UsersLoginDTO;
import simonedangelo.mondovan.User.UsersService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sign_in")
public class EnterController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProvincesService provincesService;
    @Autowired
    private TownsService townsService;

    @PostMapping("/login")
    public UsersLoggedDTO login(@RequestBody UsersLoginDTO login) {
        return new UsersLoggedDTO(usersService.autenticateUsers(login));
    }

    @PostMapping("/customer_register")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Validated UsersDTO userObj, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return usersService.customerRegister(userObj);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());
    }

    @PostMapping("/owner_register")
    @ResponseStatus(HttpStatus.CREATED)
    public Owner createOwner(@RequestBody @Validated UsersDTO userObj, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return usersService.ownersRegister(userObj);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());
    }

    @GetMapping("/prov")
    public Page<Province> getAllProvince(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "120") int size,
                                         @RequestParam(defaultValue = "id") String sort) {
        return provincesService.getAllProvince(page, size, sort);
    }

    @GetMapping("/towns/{abb}")
    public Page<Town> GetTownsByAbbr(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "300") int size,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String abb) {
        List<Town> cityPerProvince = townsService.getByAbbreviation(abb);
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return new PageImpl<>(cityPerProvince, p, cityPerProvince.size());
    }
}
