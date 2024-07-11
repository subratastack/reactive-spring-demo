package com.example.reactive_spring_demo.controllers;

import com.example.reactive_spring_demo.domain.Beer;
import com.example.reactive_spring_demo.model.BeerDTO;
import com.example.reactive_spring_demo.repositories.BeerRepositoryTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
@TestPropertySource(locations = "classpath:application.properties")
class BeerControllerTest {

    @Value("${app.beer.path}")
    private String beerPath;


    @Value("${app.beer-id.path}")
    private String beerIdPath;

    @Autowired
    WebTestClient webTestClient;

    @Order(1)
    @Test
    void testListBeers() {
        webTestClient.get().uri(beerPath)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Order(2)
    @Test
    void testGetBeerById() {
        webTestClient.get().uri(beerIdPath, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody(BeerDTO.class);
    }

    @Order(997)
    @Test
    void testSaveBeer() {
        webTestClient.post().uri(beerPath)
                .body(Mono.just(BeerRepositoryTest.getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated();
    }

    @Order(998)
    @Test
    void testUpdateBeer() {
        webTestClient.put().uri(beerIdPath, 1)
                .body(Mono.just(BeerRepositoryTest.getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Order(999)
    @Test
    void testDeleteBeer() {
        webTestClient.delete().uri(beerIdPath, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testSaveBeerException() {
        Beer testBeer = BeerRepositoryTest.getTestBeer();
        testBeer.setBeerName("A");

        webTestClient.post().uri(beerPath)
                .body(Mono.just(testBeer), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testGetBeerByIdNotFound() {
        webTestClient.get().uri(beerIdPath, 10)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Order(1000)
    @Test
    void testDeleteBeerNotFound() {
        webTestClient.delete().uri(beerIdPath, 10)
                .exchange()
                .expectStatus().isNotFound();
    }
}