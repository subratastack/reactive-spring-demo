package com.example.reactive_spring_demo.services;

import com.example.reactive_spring_demo.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {

    Flux<BeerDTO> listBeers();
}
