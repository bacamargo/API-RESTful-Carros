package com.example.cars.domain.dto;

import org.modelmapper.ModelMapper;
import com.example.cars.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

    private Long id;
    private String name;
    private String tipo;

    public static CarroDTO create(Carro c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarroDTO.class);
    }
}
