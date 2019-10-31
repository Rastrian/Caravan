package me.caravanweb.resources.api;

import java.net.URI;
import java.time.LocalDate;
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
import me.caravanweb.services.LocalidadesService;
import me.caravanweb.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/caravanas")
public class CaravanasResource {
	@Autowired
	private CaravanasService service;
	@Autowired
	private LocalidadesService servicel;
	@Autowired
	private UsuarioService serviceu;
	
	@GetMapping
	public ResponseEntity<List<Caravanas>> findAll() { 
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Caravanas> findById(@PathVariable Integer id) {
		Caravanas obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Caravanas> create(@RequestBody Caravanas c){
		c.setLocal(servicel.findById(c.getLocalId()));
		c.setOwner(serviceu.findById(c.getOwnerId()));
		c.setData(LocalDate.of(c.getAno(), c.getMes(), c.getDia()));
		service.add(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
	}

    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	Caravanas local = service.findById(id);
    	String body = "";
		boolean status = service.delete(local);
		if (status) {
			body = "Deletado.";
		}
		return ResponseEntity.ok().body(body);    
	}
	
}
