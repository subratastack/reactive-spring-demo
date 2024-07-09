package com.example.reactive_spring_demo.repositories;

import com.example.reactive_spring_demo.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    Mono<Person> getById(Integer id);
    Flux<Person> findAll();
}
