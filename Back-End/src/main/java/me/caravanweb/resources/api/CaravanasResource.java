package me.caravanweb.resources.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import me.caravanweb.profiles.Caravanas;
import me.caravanweb.services.CaravanasService;
import me.caravanweb.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/caravanas")
public class CaravanasResource {
	@Autowired
	private CaravanasService service;
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
	
	@GetMapping(value = "/local/{id}")
	public ResponseEntity<ArrayList<Caravanas>> findByLocalId(@PathVariable Integer id) {
		ArrayList<Caravanas> obj = service.findByLocalId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@PostMapping(value = "/register")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<String> create(@RequestBody Caravanas c){
		String body =  service.add(c);
		return ResponseEntity.ok().body(body);
	}

    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	String body = "";
    	Caravanas caravana = service.findById(id);
    	if (caravana != null) {
    		body = serviceu.cleanCaravanaUsers(id);
    		boolean status = service.delete(caravana);
    		if (status) {
    			body = "Deletado.";
    		}
    	}
		return ResponseEntity.ok().body(body);    
	}
	
}
