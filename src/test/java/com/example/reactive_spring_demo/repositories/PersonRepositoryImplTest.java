package com.example.reactive_spring_demo.repositories;

import com.example.reactive_spring_demo.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoWithSubscribe() {

        Mono<Person> person = personRepository.getById(1);
        person.subscribe(person1 -> {
            System.out.println(person1.toString());
        });
    }

    @Test
    void testMapOperation() {
        Mono<Person> person = personRepository.getById(1);
        person.map(Person::getFirstName).subscribe(System.out::println);
    }
}