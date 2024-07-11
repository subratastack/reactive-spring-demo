package com.example.reactive_spring_demo.services;

import com.example.reactive_spring_demo.mappers.BeerMapper;
import com.example.reactive_spring_demo.model.BeerDTO;
import com.example.reactive_spring_demo.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll().map(beerMapper::beerToBeerDto);
    }
}
