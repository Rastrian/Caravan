package me.caravanweb.services;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.LocaisTuristicosDAO;
import me.caravanweb.profiles.LocaisTuristicos;

@Service
public class LocalidadesService {
	
	private LocaisTuristicosDAO repository;
	
	public LocalidadesService() {
		try {
			repository = new LocaisTuristicosDAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<LocaisTuristicos> findAll() {
		return repository.getAll();
	}

	public LocaisTuristicos findById(Integer id) {
		return repository.get(id);
	}
	
	public int count() {
		return repository.count();
	}
	
	public boolean delete(LocaisTuristicos locaisTuristicos) {
		repository.remove(locaisTuristicos);
		if (repository.get(locaisTuristicos.getId()) == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean add(LocaisTuristicos local) {
		return repository.add(local);
	}
}
