package ee.mario.sharedblogbackend.repository;

import ee.mario.sharedblogbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
