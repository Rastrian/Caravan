package me.caravanweb.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import me.caravanweb.DAO.CaravanasDAO;
import me.caravanweb.profiles.Caravanas;
import me.caravanweb.profiles.LocaisTuristicos;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.profiles.others.Caravana;

@Service
public class CaravanasService {

	private CaravanasDAO repository;
	
	public CaravanasService() {
		try {
			repository = new CaravanasDAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Caravana parseCaravana(Caravanas c) {
		Caravana carav = new Caravana();
		carav.setNome(c.getNome());
		carav.setDesc(c.getDescricao());
		carav.setDia(c.getData().getDayOfMonth());
		carav.setMes(c.getData().getMonthValue());
		carav.setAno(c.getData().getYear());
		return carav;
	}

	public List<Caravanas> findAll() {
		return repository.getAll();
	}
	
	public Caravanas findById(int id) {
		return repository.get(id);
	}
	
	public int count() {
		return repository.count();
	}
	
	public void update(Caravanas c) {
		repository.update(c);
	}
	
	public boolean add(Caravanas l) {
		int proxid = count() + 1;
		Caravanas checkcount = repository.get(proxid);
		while (checkcount != null) {
			proxid++;
			checkcount = repository.get(proxid);
		}
		l.setId(proxid);
		return repository.add(l);
	}
	
	public boolean delete(Caravanas caravanas) {
		repository.remove(caravanas);
		if (repository.get((int) caravanas.getId()) == null) {
			return true;
		}else {
			return false;
		}
	}

	public boolean hasUser(Usuario l, Caravanas c) {
		for (Usuario u : c.getUsers()) {
			if (u.getId() == l.getId()) {
				return true;
			}
		}
		return false;
	}
	
}
