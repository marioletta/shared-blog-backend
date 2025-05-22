package ee.mario.sharedblogbackend.controller;

import ee.mario.sharedblogbackend.entity.Post;
import ee.mario.sharedblogbackend.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class PostController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepository postRepository;

    @GetMapping("posts")
    public ResponseEntity<Page<Post>> getPost(Pageable pageable) {
        return ResponseEntity.ok().body(postRepository.findAll(pageable));
    }

    @GetMapping("public-posts")
    public ResponseEntity<List<Post>> getPublicPosts() {
        List<Post> posts = postRepository.findAll();

        System.out.println(modelMapper);
        List<Post> postsPublic = List.of(modelMapper.map(posts, Post[].class));

        return ResponseEntity.ok().body(postsPublic);
    }

    @PostMapping("posts")
    public Post addPost(@RequestBody Post post) {
        //TODO: KUI ON OLEMAS ID, siis viskab errori
        if (post.getId() != null) {
            throw new RuntimeException("Cannot add with ID!");
        }
        return postRepository.save(post);
    }

    @GetMapping("post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok().body(postRepository.findById(id).orElseThrow());
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<List<Post>> deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok().body(postRepository.findAll());
    }

    @PutMapping("posts")
    public ResponseEntity<List<Post>> editPost(@RequestBody Post post) {
        //TODO: KUI ON PUUDU ID, siis viskab errori
        if (post.getId() == null) {
            throw new RuntimeException("Cannot add without ID!");
        }
        postRepository.save(post);
        return ResponseEntity.ok().body(postRepository.findAll());
    }




}
