package me.caravanweb.services;

import java.io.IOException;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.EnderecoDAO;
import me.caravanweb.profiles.Endereco;

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
	
	public void add(Endereco e) {
		repository.add(e);
	}
	
	public Endereco get(Long id) {
		return repository.get(id);
	}
	
}
