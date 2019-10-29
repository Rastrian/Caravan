package me.caravanweb.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import me.caravanweb.DAO.UsuarioDAO;
import me.caravanweb.profiles.LocaisTuristicos;
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
	
	public List<String> listEmails() {
		ArrayList<String> emails = new ArrayList<String>();
		List<Usuario> usuarios = repository.getAll();
		usuarios.forEach(l -> emails.add(l.getEmail()));
		return emails;
	}
	
	public Usuario findById(int id) {
		return repository.get(id);
	}
	
	public boolean emailExists(String email) {
		List<String> emails = listEmails();
		if (emails.contains(email)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String add(Usuario u) {
		String msg;
		if (emailExists(u.getEmail())) {
			msg = "MailError";
		}else {
			int proxid = count() + 1;
			Usuario checkcount = repository.get(proxid);
			while (checkcount != null) {
				proxid++;
				checkcount = repository.get(proxid);
			}
			u.setId(proxid);
			boolean cadastro = repository.add(u);
			if (cadastro == true) {
				msg = "success";
			}else {
				msg = "InternalError";
			}
		}
		return msg;
	}
	
	public boolean checkPassword(String uEmail, String uSenha, String email, String senha) {
		boolean msg = false;
		if (uEmail.equals(email)) {
			if (uSenha.equals(senha)) {
				msg = true;
			}	
		}
		return msg;
	}
	
	public String auth(String email, String senha) {
		String msg = "error";
		if (emailExists(email)) {
			boolean check = false;
			List<Usuario> usuarios = repository.getAll();
			for (Usuario u : usuarios) {
				if (check == false) {
					check = checkPassword(u.getEmail(), u.getSenha(), email, senha);
				}
			}
			if (check==true) {
				msg="success";
			}
		}else {
			msg = "MailError";
		}
		return msg;
	}
	
	public int count () {
		return repository.getAll().size();
	}

}
