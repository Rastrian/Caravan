package me.caravanweb.resources.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.caravanweb.profiles.Caravanas;
import me.caravanweb.services.CaravanasService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/caravanas")
public class CaravanasResource {
	@Autowired
	private CaravanasService service;
	
	@GetMapping
	public ResponseEntity<List<Caravanas>> findAll() { 
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Caravanas> findById(@PathVariable Integer id) {
		Caravanas obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/local/{id}")
	public ResponseEntity<ArrayList<Caravanas>> findByLocalId(@PathVariable Integer id) {
		ArrayList<Caravanas> obj = service.findByLocalId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Caravanas> create(@RequestBody Caravanas c){
		service.add(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
	}

    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	Caravanas caravana = service.findById(id);
    	String body = "";
		boolean status = service.delete(caravana);
		if (status) {
			body = "Deletado.";
		}
		return ResponseEntity.ok().body(body);    
	}
	
}
