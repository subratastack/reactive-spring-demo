package com.example.reactive_spring_demo.mappers;

import com.example.reactive_spring_demo.domain.SoftDrink;
import com.example.reactive_spring_demo.model.SoftDrinkDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SoftDrinkMapper {

    SoftDrink softDrinkDtoToSoftDrink(SoftDrinkDTO dto);
    SoftDrinkDTO softDrinkToSoftDrinkDto(SoftDrink entity);
}
