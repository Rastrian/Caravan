import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class CaravanasDAOTest {
	public static CaravanasDAO caravanas;
	static List<Caravanas> lista;
	LocaisTuristicos local;
	Caravanas teste;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
		caravanas = new CaravanasDAO("CaravanasTeste");
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
	void testRemoveCravana() {
		Caravanas a = new Caravanas("Passeio do jose","Pescar em alto mar",local,20,12,2025);
		caravanas.add(a);
		caravanas.remove(a);
		lista = caravanas.getAll();
		assertTrue(lista.isEmpty());
	}
	
	@Test
	void testAdicionarCaravana() {
		Caravanas b = new Caravanas("Passeio do paraiso","Passeio pelo lugar bonito",local,4,12,2019);
		caravanas.add(b);
		lista = caravanas.getAll();
		assertFalse(lista.isEmpty());
	}
	
	@Test
	void testGetCaravana() {
		Caravanas c = new Caravanas("Passeio do Sol","Passeio pelo lugar quente",local,5,10,2021);
		caravanas.add(c);
		lista = caravanas.getAll();
		for(Caravanas car:lista)
			System.out.println(car.getDescricao());
	    assertEquals(c,caravanas.get(3));
	}
	
	@Test
	void testUpdateCaravana() {
		 
	}
	
	
	
	
	
	
	
	
	

}
