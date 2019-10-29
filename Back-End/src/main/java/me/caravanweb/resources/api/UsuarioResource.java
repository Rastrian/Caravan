package me.caravanweb.resources.api;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.caravanweb.profiles.LocaisTuristicos;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.profiles.others.Login;
import me.caravanweb.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/users")
public class UsuarioResource {
	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/count")
	public ResponseEntity<String> count() {
		int obj = service.count();
		return ResponseEntity.ok().body(String.valueOf(obj));
	}
	
	@GetMapping(value = "/user/{id}")
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
    public ResponseEntity<String> register(Usuario u){
    	String body = service.add(u);
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> login(Login l){
    	String body = service.auth(l.getEmail(), l.getSenha());
		return ResponseEntity.ok().body(body);
	}

}
