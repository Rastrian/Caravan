package me.caravanweb.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.CaravanasDAO;
import me.caravanweb.profiles.Caravanas;

@Service
public class CaravanasService {

	@Autowired
	private CaravanasDAO repository;
	
	public CaravanasService() {
		try {
			repository = new CaravanasDAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public boolean delete(Caravanas c) {
		repository.remove(c);
		if ((repository.get(c.getId())) == null) {
			return true;
		}else {
			return false;
		}
	}

	public void add(Caravanas c) {
		int proxid = count() + 1;
		Caravanas checkcount = repository.get(proxid);
		while (checkcount != null) {
			proxid++;
			checkcount = repository.get(proxid);
		}
		c.setId(proxid);
		repository.add(c);
	}
}
