package com.study.springwebfluxstart.controller;

import com.study.springwebfluxstart.Post;
import com.study.springwebfluxstart.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;

    private static long tempId = 1;

    @GetMapping
    public Flux<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Post> getPostById(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PostMapping
    public Mono<Post> createPost(@RequestBody Post post) {
//        post.setId(tempId++);
        System.out.println(post.toString());
        return postRepository.save(post);
    }
}
