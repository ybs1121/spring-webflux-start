package com.study.springwebfluxstart.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;


@WebFluxTest(CustomerControllerTest.class)
//@SpringBootTest
//@AutoConfigureWebTestClient
public class CustomerControllerTest {

    @Autowired
    private WebTestClient webTestClient;//http 비동기 요청


    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void 한건찾기_테스트() throws Exception {
        Mono<Customer> just = Mono.just(new Customer("TESET", "NAME"));
        when(customerRepository.findById(1L)).thenReturn(just);
        Mono<Customer> byId = customerRepository.findById(1L);
        System.out.println(byId.block());
        webTestClient.get().uri("/customer/{id}", 1)
                .exchange()
                .expectBody()
                .jsonPath("$.firstname").isEqualTo("TESET")
                .jsonPath("$.lastname").isEqualTo("NAME");


    }


}
