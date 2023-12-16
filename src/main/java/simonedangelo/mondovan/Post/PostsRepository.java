package simonedangelo.mondovan.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import simonedangelo.mondovan.Post.Enum.Category;
import simonedangelo.mondovan.User.User;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.author.role='CUSTOMER'")
    List<Post> findByCustomer();

    Page<Post> findByAuthor(User author, Pageable p);

    Page<Post> findByCategory(Category category, Pageable p);

    Page<Post> findByCategoryAndAuthor(Category category, User author, Pageable p);

    @Query("SELECT p FROM Post p WHERE p.author IN(:user)")
    Page<Post> findByFriend(List<User> user, Pageable p);
}
