package simonedangelo.mondovan.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.User.Customer.Customer;
import simonedangelo.mondovan.User.Owner.Owner;
import simonedangelo.mondovan.User.Payload.UsersDTO;
import simonedangelo.mondovan.User.Payload.UsersLoggedDTO;
import simonedangelo.mondovan.User.Payload.UsersLoginDTO;

import java.io.IOException;

@RestController
@RequestMapping("/sign_in")
public class UsersEnterController {
    @Autowired
    private UsersService usersService;

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
        System.out.println("hey");
        if (!bindingResult.hasErrors()) {
            try {
                return usersService.ownersRegister(userObj);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());
    }
}
