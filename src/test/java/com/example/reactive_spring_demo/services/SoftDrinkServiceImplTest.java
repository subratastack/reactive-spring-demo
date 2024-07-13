package com.example.reactive_spring_demo.services;

import com.example.reactive_spring_demo.domain.SoftDrink;
import com.example.reactive_spring_demo.mappers.SoftDrinkMapper;
import com.example.reactive_spring_demo.model.SoftDrinkDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SoftDrinkServiceImplTest {

    @Autowired
    SoftDrinkService softDrinkService;

    @Autowired
    SoftDrinkMapper softDrinkMapper;

    SoftDrinkDTO softDrinkDTO;

    @BeforeEach
    void setUp() {
        softDrinkDTO = softDrinkMapper.softDrinkToSoftDrinkDto(getTestSoftDrink());
    }

    @Test
    void saveSoftDrink() {
        Mono<SoftDrinkDTO> savedSoftDrink = softDrinkService.saveSoftDrink(Mono.just(softDrinkDTO));

        savedSoftDrink.subscribe(System.out::println);
    }

    public static SoftDrink getTestSoftDrink() {
        return SoftDrink.builder()
                .softDrinkName("Coca-Cola")
                .softDrinkStyle("drinks")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("123213")
                .build();
    }
}