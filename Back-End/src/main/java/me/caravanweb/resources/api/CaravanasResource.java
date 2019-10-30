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
import me.caravanweb.profiles.LocaisTuristicos;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.profiles.others.Caravana;
import me.caravanweb.services.CaravanasService;
import me.caravanweb.services.LocalidadesService;
import me.caravanweb.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/caravanas")
public class CaravanasResource {
	
	@Autowired
	private CaravanasService service;
	private UsuarioService serviceu;
	private LocalidadesService servicel;

	@GetMapping
	public ResponseEntity<ArrayList<Caravana>> findAll() { 
		ArrayList<Caravana> caravanas = null;
		for (Caravanas c : service.findAll()) {
			Caravana parsed = service.parseCaravana(c);
			caravanas.add(parsed);
		}
		return ResponseEntity.ok().body(caravanas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Caravana> findById(@PathVariable Integer id) {
		Caravanas carav = service.findById(id);
		Caravana obj = service.parseCaravana(carav);
		return ResponseEntity.ok().body(obj);
	}
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Caravanas> create(@RequestBody Caravana local){
    	Caravanas c = new Caravanas();
    	c.setNome(local.getNome()); c.setDescricao(local.getDesc());
		c.setData(local.getData(local.getDia(), local.getMes(), local.getAno()));
		boolean status = service.add(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
    }
    
    @PostMapping(value = "/{idcaravana}/set/local/{idlocal}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Caravanas> setLocalTuristico(@PathVariable Integer idcaravana, Integer idlocal){
    	Caravanas c = service.findById(idcaravana);
    	LocaisTuristicos l = servicel.findById(idlocal);
    	c.setLocal(l);
    	service.update(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
	}
    
    @PostMapping(value = "/{idcaravana}/add/user/{iduser}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Caravanas> addUsuarioCaravana(@PathVariable Integer iduser, Integer idcaravana){
    	Caravanas c = service.findById(idcaravana);
    	Usuario l = serviceu.findById(iduser);
    	if (!service.hasUser(l, c)) {
    		c.addUser(l);
    	}
    	if(serviceu.hasCaravana(l, c)) {
    		l.addCaravana(c);
    	}
    	service.update(c);
    	serviceu.update(l);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
	}
    
    @PostMapping(value = "/{idcaravana}/remove/user/{iduser}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Caravanas> removeUsuarioCaravana(@PathVariable Integer iduser, Integer idcaravana){
    	Caravanas c = service.findById(idcaravana);
    	Usuario l = serviceu.findById(iduser);
    	if (!service.hasUser(l, c)) {
    		c.removeUser(l);
    	}
    	if(serviceu.hasCaravana(l, c)) {
    		l.removeCaravana(c);
    	}
    	service.update(c);
    	serviceu.update(l);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
	}
    
	@GetMapping(value = "/local/{id}")
	public ResponseEntity<List<Caravanas>> caravanasByLocalTuristicoID(@PathVariable Integer id) {
		List<Caravanas> obj = service.findAll();
		for (Caravanas c : obj) {
			if (c.getLocal().getId() != id) {
				obj.remove(c);
			}
		}
		return ResponseEntity.ok().body(obj);
	}
    
    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	Caravanas local = service.findById(id);
    	String body = "";
		boolean status = service.delete(local);
		if (status) {
			body = "deletado";
		}
		return ResponseEntity.ok().body(body);    
	}
	
}
