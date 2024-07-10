package com.example.reactive_spring_demo.repositories;

import com.example.reactive_spring_demo.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void testGetByIdFoundStepVerifier() {
        Mono<Person> personMono = personRepository.getById(3);
        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();
        personMono.subscribe(System.out::println);
    }

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

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println(Objects.requireNonNull(person));
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(System.out::println);
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxOnFilter() {
        personRepository.findAll()
                .filter(person -> person.getFirstName().equals("C"))
                .map(Person::getFirstName)
                .subscribe(System.out::println);
    }

    @Test
    void testGetById() {
        Mono<String> dMono = personRepository.findAll()
                .filter(person -> person.getFirstName().equals("D"))
                .map(Person::getFirstName)
                .next();
        dMono.subscribe(System.out::println);
    }

    @Test
    void testFindPersonByIdNotFound() {

        Flux<Person> personFlux = personRepository.findAll();
        final Integer id = 8;
        Mono<Person> personMono = personFlux.filter(person -> Objects.equals(person.getId(), id)).single();
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        }, throwable -> {
            System.out.println("Error: " + throwable.toString());
        });
    }
}