package me.caravanweb.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.CaravanasDAO;
import me.caravanweb.DAO.LinkedUC_DAO;
import me.caravanweb.DAO.UsuarioDAO;
import me.caravanweb.profiles.Caravanas;
import me.caravanweb.profiles.LinkedUC;
import me.caravanweb.profiles.Usuario;

@Service
public class UsuarioService {
	
	@Autowired private UsuarioDAO repository;
	@Autowired private LinkedUC_DAO repositoryUC;
	@Autowired private CaravanasDAO repositoryc;
	
	public UsuarioService() {
		try {
			repository = new UsuarioDAO();
			repositoryUC = new LinkedUC_DAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public boolean isAdmin(Integer id) {
		Usuario u = findById(id);
		return u.isAdmin();
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
	
	public Usuario findById(Integer id) {
		return repository.get(id);
	}
	
	public boolean emailExists(String email) {
		List<String> emails = listEmails();
		return emails.contains(email);
	}
	
	public String add(Usuario u) {
		String msg = "InternalError";
		if (emailExists(u.getEmail())) {
			msg = "MailError";
		}else{
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
			}
		}
		return msg;
	}
	
	public boolean checkPassword(String uEmail, String uSenha, String email, String senha) {
		if (uEmail.equals(email)) {
			return (uSenha.equals(senha));
		}
		return false;
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
				if (!check) {
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

	public String addUser(Integer c, Integer u) {
		String body = "error";
		LinkedUC newUC = new LinkedUC(c,u);
		boolean status = hasUser(c,u);
		if (!status) {
			repositoryUC.add(newUC);
			body = "success";
		}
		return body;
	}
	
	public String removeUser(Integer c, Integer u) {
		String body = "error";
		LinkedUC newUC = new LinkedUC(c,u);
		boolean status = hasUser(c,u);
		if (status) {
			repositoryUC.remove(newUC);
			body = "success";
		}
		return body;
	}
	
	public boolean hasUser(Integer c, Integer u) {
		boolean status = false;
		for (LinkedUC uc : repositoryUC.getAll()) {
			if (uc.getUserId().equals(u) && uc.getCaravanaId().equals(c)) {
				status = true;
			}
		}
		return status;
	}

	public ArrayList<Caravanas> getCaravanasOfUser(Integer userId) {
		ArrayList<Caravanas> caravanas = new ArrayList<Caravanas>();
		for (LinkedUC uc : repositoryUC.getAll()) {
			if (uc.getUserId().equals(userId)) {
				caravanas.add(repositoryc.get(uc.getCaravanaId()));
			}
		}
		return caravanas;
	}
	
	public Caravanas getCaravanaOfUser(Integer userId, Integer caravanId) {
		for (LinkedUC uc : repositoryUC.getAll()) {
			if (uc.getUserId().equals(userId) && uc.getCaravanaId().equals(caravanId)) {
				return repositoryc.get(uc.getCaravanaId());
			}
		}
		return null;
	}

	public boolean isTurista(Integer id) {
		Usuario u = findById(id);
		return u.isTurista();
	}

}
