package com.example.reactive_spring_demo.controllers;

import com.example.reactive_spring_demo.model.BeerDTO;
import com.example.reactive_spring_demo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;
    private final Environment environment;

    @GetMapping("${app.beer.path}")
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping("${app.beer-id.path}")
    Mono<BeerDTO> getBeerById(@PathVariable("beerId") Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @PostMapping("${app.beer.path}")
    Mono<ResponseEntity<Void>> createBeer(@Validated @RequestBody BeerDTO dto) {
        return beerService.saveBeer(dto)
                .map(savedDto -> ResponseEntity.created(
                        UriComponentsBuilder
                                .fromHttpUrl("http://localhost:8080/" + environment.getProperty("app.beer.path") + "/" + savedDto.getId())
                                .build().toUri())
                        .build()
                );
    }

    @PutMapping("${app.beer-id.path}")
    Mono<ResponseEntity<Void>> updateExistingBeer(@PathVariable("beerId") Integer beerId,
                                                  @Validated @RequestBody BeerDTO beerDTO) {
        return beerService.updateExistingBeer(beerId, beerDTO)
                .map(savedDto -> ResponseEntity.ok().build());
    }

    @DeleteMapping("${app.beer-id.path}")
    Mono<ResponseEntity<Void>> deleteBeer(@PathVariable("beerId") Integer beerId) {
        return beerService.deleteBeerById(beerId)
                .map(beer -> ResponseEntity.noContent().build());
    }
}
