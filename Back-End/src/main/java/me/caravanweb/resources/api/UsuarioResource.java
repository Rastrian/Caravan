package me.caravanweb.resources.api;

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

import me.caravanweb.profiles.Caravanas;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.services.CaravanasService;
import me.caravanweb.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/users")
public class UsuarioResource {
	@Autowired
	private UsuarioService service;
	@Autowired
	private CaravanasService servicec;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/count")
	public ResponseEntity<String> count() {
		int obj = service.count();
		return ResponseEntity.ok().body(String.valueOf(obj));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/emails")
	public ResponseEntity<List<String>> emails() {
		return ResponseEntity.ok().body(service.listEmails());
	}
	
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody Usuario u){
    	System.out.println(u.getEmail());
    	String body = service.add(u);
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> login(@RequestBody Usuario u){
    	String body = service.auth(u.getEmail(), u.getSenha());
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/{idUsuario}/caravana/add/{idCaravana}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addUser(@PathVariable Integer idCaravana, Integer idUsuario){
    	String body = "erro";
    	Caravanas c = servicec.findById(idCaravana);
    	Usuario u = service.findById(idUsuario);
    	if (u != null) {
    		if (c != null) {
    			
    		}
    	}
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/{idUsuario}/caravana/remove/{idCaravana}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> removeUser(@PathVariable Integer idCaravana, Integer idUsuario){
    	String body = "erro";
    	Caravanas c = servicec.findById(idCaravana);
    	Usuario u = service.findById(idUsuario);
    	u.removeCaravana(c);
    	c.removeUser(u);
    	service.update(u);
    	servicec.update(c);
    	if (c.getUsers().contains(c)) {
    		body = "removido";
    	}
		return ResponseEntity.ok().body(body);
	}

}
