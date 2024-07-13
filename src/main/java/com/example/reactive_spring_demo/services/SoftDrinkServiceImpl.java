package com.example.reactive_spring_demo.services;

import com.example.reactive_spring_demo.mappers.SoftDrinkMapper;
import com.example.reactive_spring_demo.model.SoftDrinkDTO;
import com.example.reactive_spring_demo.repositories.SoftDrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SoftDrinkServiceImpl implements SoftDrinkService {

    private final SoftDrinkRepository softDrinkRepository;
    private final SoftDrinkMapper softDrinkMapper;

    @Override
    public Mono<SoftDrinkDTO> saveSoftDrink(Mono<SoftDrinkDTO> dto) {
        return dto.map(softDrinkMapper::softDrinkDtoToSoftDrink)
                .flatMap(softDrinkRepository::save)
                .map(softDrinkMapper::softDrinkToSoftDrinkDto);
    }

    @Override
    public Mono<SoftDrinkDTO> getSoftDrinkById(String id) {
        return null;
    }
}
