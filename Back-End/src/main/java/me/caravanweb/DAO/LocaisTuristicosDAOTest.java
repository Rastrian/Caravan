package me.caravanweb.DAO;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.caravanweb.profiles.Caravanas;

import java.util.ArrayList;
import java.util.List;


class LocaisTuristicosDAOTest {
	static List<Caravanas> caravanas;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		caravanas = new ArrayList<Caravanas>();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
