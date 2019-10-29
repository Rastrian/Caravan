package me.caravanweb.resources.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.caravanweb.services.CaravanasService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/Caravanas")

public class CaravanasResource {
	@Autowired
	private CaravanasService service;
	
	@GetMapping
	public ResponseEntity<Object> findAll() { 
		return ResponseEntity.ok().body(service.findAll());
	}
}
