package simonedangelo.mondovan.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/{idUser}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','OWNER')")
    public User getUser(@AuthenticationPrincipal User user, @PathVariable long idUser) {
        return usersService.getUserById(idUser);
    }

    @PatchMapping("/upload_avatar")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile registrationDocument, @AuthenticationPrincipal User loggedUser) throws IOException {
        System.out.println(registrationDocument.getSize());
        System.out.println(registrationDocument.getContentType());
        return usersService.addAvatar(registrationDocument, loggedUser.getId());
    }

    @PatchMapping("/{idFriends}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public User addFriends(@AuthenticationPrincipal User loggedUser, @PathVariable long idFriends) throws IOException {
        return usersService.addFriend(idFriends, loggedUser);
    }

    @DeleteMapping("/{idFriends}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public User deleteFriends(@AuthenticationPrincipal User loggedUser, @PathVariable long idFriends) throws IOException {
        return usersService.removeFriend(idFriends, loggedUser);
    }

    @PatchMapping("/upload_cover")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public String uploadCover(@RequestParam("cover") MultipartFile registrationDocument, @AuthenticationPrincipal User loggedUser) throws IOException {
        System.out.println(registrationDocument.getSize());
        System.out.println(registrationDocument.getContentType());
        return usersService.addCover(registrationDocument, loggedUser.getId());
    }
}
