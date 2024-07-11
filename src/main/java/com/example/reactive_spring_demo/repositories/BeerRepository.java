package com.example.reactive_spring_demo.repositories;

import com.example.reactive_spring_demo.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
