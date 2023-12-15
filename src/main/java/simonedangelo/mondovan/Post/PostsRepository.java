package simonedangelo.mondovan.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.author.role='CUSTOMER'")
    List<Post> findByCustomer();
}
