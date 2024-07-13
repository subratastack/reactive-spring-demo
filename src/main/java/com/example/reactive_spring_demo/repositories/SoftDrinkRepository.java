package com.example.reactive_spring_demo.repositories;

import com.example.reactive_spring_demo.domain.SoftDrink;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftDrinkRepository extends ReactiveMongoRepository<SoftDrink, String> {
}
