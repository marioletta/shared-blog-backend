package ee.mario.sharedblogbackend.repository;

import ee.mario.sharedblogbackend.dto.PostDTO;
import ee.mario.sharedblogbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainsIgnoreCase(String searchTerm);


    @Query("SELECT new ee.mario.sharedblogbackend.dto.PostDTO(p.id, p.title) " +
            "FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<PostDTO> searchPostSummaries(@Param("searchTerm") String searchTerm);
}
