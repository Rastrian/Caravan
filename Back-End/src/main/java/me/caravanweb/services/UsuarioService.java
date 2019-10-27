package me.caravanweb.services;

import java.io.IOException;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.UsuarioDAO;

@Service
public class UsuarioService {
	
	private UsuarioDAO repository;
	
	public UsuarioService() {
		try {
			repository = new UsuarioDAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
