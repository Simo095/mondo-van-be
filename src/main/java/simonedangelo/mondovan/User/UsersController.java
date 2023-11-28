package simonedangelo.mondovan.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','OWNER')")
    public User getMe(@AuthenticationPrincipal User user) {
        return usersService.getUserById(user.getId());
    }
}
