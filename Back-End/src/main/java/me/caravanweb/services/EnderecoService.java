package me.caravanweb.services;

import java.io.IOException;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.EnderecoDAO;

@Service
public class EnderecoService {

	private EnderecoDAO repository;
	
	public EnderecoService() {
		try {
			repository = new EnderecoDAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
