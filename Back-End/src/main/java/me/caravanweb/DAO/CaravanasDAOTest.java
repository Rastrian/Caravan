package me.caravanweb.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.caravanweb.profiles.Caravanas;
import me.caravanweb.profiles.Endereco;
import me.caravanweb.profiles.LocaisTuristicos;
import me.caravanweb.profiles.Usuario;

import java.io.IOException;
import java.util.List;


class CaravanasDAOTest {
	public static CaravanasDAO caravanas;
	static List<Caravanas> lista;
	LocaisTuristicos local;
	Caravanas teste;
	Usuario u;
	Usuario u2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
		caravanas = new CaravanasDAO();
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
		local = new LocaisTuristicos("Fernando de Noronha","Praia");
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testRemoveCaravana() {
		LocaisTuristicos localidade = new LocaisTuristicos("Belo Horizonte (MG)", "Bela Cidade");
		Endereco end = new Endereco();
		end.setCep(Long.parseLong("99999999"));
		Usuario u = new Usuario(1,"Cleber", end, "teste@email.com", "123123", true);
		Usuario u2 = new Usuario(1,"Angela", end, "angela@email.com", "123123", true);
		Caravanas a = new Caravanas(1,"Passeio do Cleber","Pescar em alto mar",localidade,20,12,2025, u);
		caravanas.add(a);
		caravanas.remove(a);
		lista = caravanas.getAll();
		assertTrue(lista.isEmpty());
	}
	
	@Test
	void testAdicionarCaravana() {
		Caravanas b = new Caravanas(2,"Passeio do paraiso","Passeio pelo lugar bonito",local,4,12,2019, u2);
		caravanas.add(b);
		lista = caravanas.getAll();
		assertFalse(lista.isEmpty());
		caravanas.remove(b);
	}
	
	@Test
	void testGetCaravana() {
		Caravanas c = new Caravanas(3,"Passeio do Sol","Passeio pelo lugar quente",local,5,10,2021, u2);
		caravanas.add(c);
		lista = caravanas.getAll();
		for(Caravanas car:lista)
			System.out.println(car.getDescricao());
	    assertEquals(c,caravanas.get(3));
		caravanas.remove(c);
	}
	
	@Test
	void testUpdateCaravana() {
		Caravanas  d = new Caravanas(4,"OI","Passeio pelo paraiso",local,2,5,2020, u);
		caravanas.add(d);
		Caravanas f = new Caravanas(4,"Tchau","Nao gosto de passear",local,2,12,2032, u2);
		caravanas.update(f);
		lista = caravanas.getAll();
		for(Caravanas car:lista) {
			if(car.getId() == 4)
				assertEquals("Tchau",car.getNome());
		}
		caravanas.remove(f);
	}
	
	
	
	
	
	
	
	
	

}
