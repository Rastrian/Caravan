package me.caravanweb.services;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import me.caravanweb.DAO.UsuarioDAO;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.profiles.others.Login;

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
	
	public boolean emailExists(String email) {
		if (repository.count() > 0) {
			List<Usuario> usuarios = repository.getAll();
			for (Usuario u : usuarios) {
				if (u.getEmail() == email) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	public String add(Usuario u) {
		String msg;
		if (emailExists(u.getEmail())) {
			msg = "MailError";
		}else {
			u.setId((long) (count() + 1));
			boolean cadastro = repository.add(u);
			if (cadastro == true) {
				msg = "success";
			}else {
				msg = "InternalError";
			}
		}
		return msg;
	}
	
	public String auth(String email, String senha) {
		String msg = "error";
		List<Usuario> usuarios = repository.getAll();
		for (Usuario u : usuarios) {
			if (u.getEmail() == email) {
				if (u.getSenha() == senha) {
					msg = "success";
				}
			}
		}
		return msg;
	}
	
	public int count () {
		return repository.getAll().size();
	}

}
