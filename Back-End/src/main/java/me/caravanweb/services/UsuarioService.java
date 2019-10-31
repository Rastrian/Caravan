package me.caravanweb.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.UsuarioDAO;
import me.caravanweb.profiles.Caravanas;
import me.caravanweb.profiles.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
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
	
	public List<Usuario> findAll(){
		return repository.getAll();
	}
	
	public List<Caravanas> listCaravanas(Usuario u){
        u = repository.get(u.getId());
		return u.getCaravanas();
	}
	
	public Usuario findById(Integer id) {
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
	
	public Usuario userByEmail(String email) {
		List<Usuario> usuarios = repository.getAll();
		for (Usuario u : usuarios) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
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
				Usuario get = userByEmail(email);
				msg=String.valueOf(get.getId());
			}else {
				msg="invalidpassword";
			}
		}else {
			msg = "MailError";
		}
		return msg;
	}
	
	public int count () {
		return repository.getAll().size();
	}

	public Usuario update(Usuario u) {
		repository.update(u);
		return repository.get(u.getId());
	}

}
