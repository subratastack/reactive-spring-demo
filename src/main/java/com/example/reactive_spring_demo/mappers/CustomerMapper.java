package com.example.reactive_spring_demo.mappers;

import com.example.reactive_spring_demo.domain.Customer;
import com.example.reactive_spring_demo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);
    CustomerDTO customerToCustomerDto(Customer entity);
}
