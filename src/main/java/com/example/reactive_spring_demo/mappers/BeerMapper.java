package com.example.reactive_spring_demo.mappers;

import com.example.reactive_spring_demo.domain.Beer;
import com.example.reactive_spring_demo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);
    BeerDTO beerToBeerDto(Beer entity);
}
