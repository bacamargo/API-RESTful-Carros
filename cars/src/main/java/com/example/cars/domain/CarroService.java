package com.example.cars.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.cars.domain.dto.CarroDTO;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCars() {

        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

    }

    public CarroDTO getCarsById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public Optional<Carro> getCarsById() {
        return null;
    }

    public List<CarroDTO> getCarsByType(String tipo) {
        return rep.findByType(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Carro save(Carro carro) {
        return carro;
    }

    public CarroDTO insert(Carro carro) {
        Assert.notNull(carro.getId(), "Não foi possível inserir o registro");

        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();

            db.setName(carro.getName());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id: " + db.getId());

            rep.save(db);

            return CarroDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }

}
