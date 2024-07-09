package com.example.reactive_spring_demo.repositories;

import com.example.reactive_spring_demo.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person p1 = Person.builder().id(1).firstName("A").lastName("aa").build();
    Person p2 = Person.builder().id(2).firstName("B").lastName("bb").build();
    Person p3= Person.builder().id(3).firstName("C").lastName("cc").build();
    Person p4 = Person.builder().id(4).firstName("D").lastName("dd").build();

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(p1);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(p1, p2, p3, p4);
    }
}
