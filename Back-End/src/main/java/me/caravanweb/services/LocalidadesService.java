package me.caravanweb.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
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
		LocaisTuristicos l = new LocaisTuristicos(local.getNome(), local.getDescricao());
		return repository.add(local);
	}
}
