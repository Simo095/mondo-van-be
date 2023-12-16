package simonedangelo.mondovan.Post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import simonedangelo.mondovan.Exceptions.NotFoundEx;
import simonedangelo.mondovan.Post.Enum.Category;
import simonedangelo.mondovan.Post.Payload.PostsDTO;
import simonedangelo.mondovan.User.User;
import simonedangelo.mondovan.User.UsersRepository;

import java.io.IOException;
import java.util.List;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Post savePost(PostsDTO obj, long idUser) throws IOException {
        User u = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        Post p = new Post();
        p.setAuthor(u);
        p.setText(obj.text());
        p.setTitle(obj.title());
        p.setCategory(obj.category());
        return postsRepository.save(p);
    }

    public String addImg(MultipartFile file, long idUser, long idPost) throws IOException {
        User u = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundEx("The searched user does not exist"));
        String s = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.getPosts().forEach(post -> {
            if (post.id == idPost) {
                post.setImg(s);
                postsRepository.save(post);
            }
        });
        return s;
    }

    public Post getPost(long idPost) {
        return postsRepository.findById(idPost).orElseThrow(() -> new NotFoundEx("Post not found"));
    }

    public void deletePost(long idPost, User user) {
        Post p = postsRepository.findById(idPost).orElseThrow(() -> new NotFoundEx("Post not found"));
        if (p.getAuthor().getId() == user.getId()) {
            postsRepository.delete(p);
        }
    }

    public Page<Post> getAllPost(Pageable p) {
        return postsRepository.findAll(p);
    }

    public Page<Post> getMyPost(User author, Pageable p) {
        return postsRepository.findByAuthor(author, p);
    }

    public Page<Post> getAllMyFriendsPost(List<User> user, Pageable p) {
        return postsRepository.findByFriend(user, p);
    }

    public Page<Post> getPostByCategory(Category category, Pageable p) {
        return postsRepository.findByCategory(category, p);
    }

    public Page<Post> getMyPostByCategory(Category category, User author, Pageable p) {
        return postsRepository.findByCategoryAndAuthor(category, author, p);
    }

    public List<Post> getPostHome() {
        return postsRepository.findByCustomer();
    }
}
