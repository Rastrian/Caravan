package me.caravanweb.services;

import java.io.IOException;
import org.springframework.stereotype.Service;

import me.caravanweb.DAO.CaravanasDAO;

public class CaravanasService {

	private CaravanasDAO repository;
	
	public CaravanasService() {
		try {
			repository = new CaravanasDAO();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
