package com.study.springwebfluxstart.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final Sinks.Many<Customer> customerSink;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerSink = Sinks.many().multicast().onBackpressureBuffer();
    }

    @GetMapping("/customer")
    public Flux<Customer> findAll() {
        return customerRepository.findAll().log();
    }

    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/flux-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> fluxStream() {
        return Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/customer/{id}")
    private Mono<Customer> findById(@PathVariable(name = "id") Long id) {
        return customerRepository.findById(id);
    }


    @GetMapping(value = "/customer/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<ServerSentEvent<Customer>> findAllSSE() {
        //ServerSentEvent 쓰면 MediaType.TEXT_EVENT_STREAM_VALUE 생략가능

        return customerSink.asFlux().map(c -> ServerSentEvent.builder(c).build())
                .doOnCancel(() -> {customerSink.asFlux().blockLast();});
    }

    @PostMapping(value = "/customer")
    public Mono<Customer> save() {
        return customerRepository.save(new Customer("John", "Doe"))
                .doOnNext(c -> customerSink.tryEmitNext(c));
    }
}
