package com.study.springwebfluxstart.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveExample {
    @Test
     void  ReactiveExample (){
        // Mono : 0개 혹은 1개의 아이템을 발행
        Mono<String> mono = Mono.just("Hello, Mono");
        mono.subscribe(System.out::println);

        //Flux: 0개부터 N개의 아이템을 발행
        Flux<String> flux = Flux.just("Hello, Flux");
        flux.subscribe(System.out::println);


    }
}
