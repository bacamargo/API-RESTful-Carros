package com.example.cars.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cars.domain.dto.CarroDTO;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByType(String tipo);

    void save(CarroDTO db);

}
