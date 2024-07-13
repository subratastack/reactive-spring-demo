package com.example.reactive_spring_demo.services;

import com.example.reactive_spring_demo.model.SoftDrinkDTO;
import reactor.core.publisher.Mono;

public interface SoftDrinkService {

    Mono<SoftDrinkDTO> saveSoftDrink(Mono<SoftDrinkDTO> dto);
    Mono<SoftDrinkDTO> getSoftDrinkById(String id);
}
