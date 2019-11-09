package me.caravanweb.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.LocaisTuristicosDAO;
import me.caravanweb.profiles.LocaisTuristicos;

@Service
public class LocalidadesService {
	
	@Autowired 
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

	public LocaisTuristicos findById(int id) {
		return repository.get(id);
	}
	
	public ArrayList<String> listNames() {
		ArrayList<String> names = new ArrayList<String>();
		List<LocaisTuristicos> list = findAll();
		list.forEach(l -> names.add(l.getNome()));
		return names;
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
	
	public String findImgUrl(int id) {
		LocaisTuristicos local = findById(id);
		return local.getImgUrl();
	}
	
	public void add(LocaisTuristicos l) {
		int proxid = count() + 1;
		LocaisTuristicos checkcount = repository.get(proxid);
		while (checkcount != null) {
			proxid++;
			checkcount = repository.get(proxid);
		}
		l.setId(proxid);
		repository.add(l);
	}
}
