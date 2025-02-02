package me.caravanweb.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.CaravanasDAO;
import me.caravanweb.profiles.Caravanas;

@Service
public class CaravanasService {

	@Autowired
	private CaravanasDAO repository;
	@Autowired
	private LocalidadesService servicel;
	@Autowired
	private UsuarioService serviceu;
	
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
		Caravanas c = repository.get(id);
		if (c != null) {
			c.setLocal(servicel.findById(c.getLocalId()));
			c.setOwner(serviceu.findById(c.getOwnerId()));
			c.setData(LocalDate.of(c.getAno(), c.getMes(), c.getDia()));
			repository.update(c);
			return c;
		} else {
			return null;
		}
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

	public String add(Caravanas c) {
		String msg = "InternalError";
		c.setLocal(servicel.findById(c.getLocalId()));
		c.setOwner(serviceu.findById(c.getOwnerId()));
		c.setData(LocalDate.of(c.getAno(), c.getMes(), c.getDia()));
		int proxid = count() + 1;
		Caravanas checkcount = repository.get(proxid);
		while (checkcount != null) {
			proxid++;
			checkcount = repository.get(proxid);
		}
		c.setId(proxid);
		boolean status = repository.add(c);
		if (status == true) {
			msg = c.getId().toString();
		}
		return msg;
	}

	public ArrayList<Caravanas> findByLocalId(Integer id) {
		ArrayList<Caravanas> lista = new ArrayList<Caravanas>();
		for (Caravanas c : findAll()) {
			if (c.getLocalId() == id) {
				lista.add(c);
			}else{
				if (c.getLocal().getId() == id) {
					lista.add(c);
				}
			}
		}
		return lista;
	}
}
