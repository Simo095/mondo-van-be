package simonedangelo.mondovan.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simonedangelo.mondovan.Exceptions.BadRequestEx;
import simonedangelo.mondovan.Post.Payload.PostsDTO;
import simonedangelo.mondovan.User.User;

import java.io.IOException;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostsService postsService;

    @GetMapping("/{idPost}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','OWNER')")
    public Post getPost(@PathVariable long idPost) {
        return postsService.getPost(idPost);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','OWNER')")
    public Page<Post> getAllPostController(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "30") int size,
                                           @RequestParam(defaultValue = "id") String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return postsService.getAllPost(p);
    }

    @GetMapping("/my_post")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','OWNER')")
    public Page<Post> getMyPostController(@AuthenticationPrincipal User user,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "16") int size,
                                          @RequestParam(defaultValue = "id") String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return postsService.getMyPost(user, p);
    }
/*

    @GetMapping("/my_friends")
    @PreAuthorize("hasAnyAuthority('CUSTOMER','OWNER')")
    public Page<Post> getMyFriendsPost(@AuthenticationPrincipal User user,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "16") int size,
                                       @RequestParam(defaultValue = "id") String sort) {
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return postsService.getAllMyFriendsPost(user.getFriends(), p);
    }

*/

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public Post savePost(@AuthenticationPrincipal User user,
                         @RequestBody @Validated PostsDTO obj, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return postsService.savePost(obj, user.getId());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else throw new BadRequestEx(bindingResult.getAllErrors());

    }

    @PatchMapping("/upload_img/{idPost}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public String uploadImg(@PathVariable long idPost,
                            @RequestParam("img") MultipartFile img,
                            @AuthenticationPrincipal User loggedUser) throws IOException {
        return postsService.addImg(img, loggedUser.getId(), idPost);
    }

    @DeleteMapping("/{idPost}")
    @PreAuthorize("hasAnyAuthority('OWNER','CUSTOMER')")
    public void deletePost(@PathVariable long idPost,
                           @AuthenticationPrincipal User loggedUser) throws IOException {
        postsService.deletePost(idPost, loggedUser);
    }
}
