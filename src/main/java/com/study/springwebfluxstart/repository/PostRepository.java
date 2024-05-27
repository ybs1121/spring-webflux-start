package com.study.springwebfluxstart.repository;

import com.study.springwebfluxstart.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PostRepository extends ReactiveCrudRepository<Post, Long> {
    Flux<Post> findByTitle(String title);
}