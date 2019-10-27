package me.caravanweb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import me.caravanweb.DAO.LocaisTuristicosDAO;
import me.caravanweb.profiles.LocaisTuristicos;

@Configuration
public class Config implements CommandLineRunner {

	private LocaisTuristicosDAO locaisRepository;
	
	@Override
	public void run(String... args) throws Exception {

	}
}