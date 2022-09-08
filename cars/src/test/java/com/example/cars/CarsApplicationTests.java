package com.example.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cars.domain.Carro;
import com.example.cars.domain.CarroService;
import com.example.cars.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	public void testSave() {
		Carro carro = new Carro();
		carro.setName("Ferrari");
		carro.setTipo("esportivos");

		CarroDTO c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		c = service.getCarsById(id);
		assertNotNull(c);

		assertEquals("Ferrari", c.getName());
		assertEquals("esportivos", c.getTipo());

		service.delete(id);

		try {
			assertNull(service.getCarsById(id));
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {

		}
	}

	@Test
	public void testList() {
		List<CarroDTO> carros = service.getCars();

		assertEquals(30, carros.size());
	}

	@Test
	public void testGet() {
		CarroDTO c = service.getCarsById(11L);

		assertNotNull(c);

		assertEquals("Ferrari FF", c.getName());
	}
}
