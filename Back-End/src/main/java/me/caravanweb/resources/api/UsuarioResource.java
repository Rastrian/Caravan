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
	
	@GetMapping(value = "/{id}/turista")
	public ResponseEntity<Boolean> checkTurista(@PathVariable Integer id) {
		boolean obj = service.isTurista(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}/admin")
	public ResponseEntity<Boolean> checkAdmin(@PathVariable Integer id) {
		boolean obj = service.isAdmin(id);
		return ResponseEntity.ok().body(obj);
	}
	
    @PostMapping(value = "/{id}/turista")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> setTurista(@PathVariable Integer id){
    	Usuario u = service.findById(id);
    	boolean turista = u.isTurista();
    	if (turista) {
    		turista = u.setTurista(false);
    	}else{
    		turista = u.setTurista(true);
    	}
    	service.update(u);
		return ResponseEntity.ok().body(turista);
	}
	
    @PostMapping(value = "/{id}/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> setAdmin(@PathVariable Integer id){
    	Usuario u = service.findById(id);
    	boolean admin = u.isAdmin();
    	if (admin) {
    		admin = u.setAdmin(false);
    	}else{
    		admin = u.setAdmin(true);
    	}
    	service.update(u);
		return ResponseEntity.ok().body(admin);
	}
    
    @PostMapping(value = "/{id}/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> updateUser(@PathVariable Integer id, Usuario user){
    	String msg = "InternalError";
    	Usuario u = service.findById(id);
    	if (u != null && (user.getId() == id) && (u.getId() == user.getId())) {
    		if (u.getEndereco() != null) {
    			msg = "Success";
    		}else {
    			msg = "EnderecoError";
    		}
    	}
		return ResponseEntity.ok().body(msg);
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
    public ResponseEntity<String> register(Usuario u){
    	String body = service.add(u);
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> login(Usuario u){
    	String body = service.auth(u.getEmail(), u.getSenha());
		return ResponseEntity.ok().body(body);
	}

	@GetMapping(value = "/{idUsuario}/caravana")
	public ResponseEntity<ArrayList<Caravanas>> getAllCaravanasOfUser
		(@PathVariable Integer idUsuario) {
		ArrayList<Caravanas> obj = service.getCaravanasOfUser(idUsuario);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{idUsuario}/caravana/{idCaravana}")
	public ResponseEntity<Caravanas> getCaravanaOfUser
		(@PathVariable Integer idUsuario, @PathVariable Integer idCaravana) {
		Caravanas obj = service.getCaravanaOfUser(idUsuario, idCaravana);
		return ResponseEntity.ok().body(obj);
	}
    
    @PostMapping(value = "/{idUsuario}/caravana/add/{idCaravana}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addUser(@PathVariable Integer idCaravana, 
    		@PathVariable Integer idUsuario){
    	String body = "erro";
    	Caravanas c = servicec.findById(idCaravana);
    	Usuario u = service.findById(idUsuario);
    	if (u != null) {
    		if (c != null) {
    			body = service.addUser(idCaravana,idUsuario);
    		}
    	}
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/{idUsuario}/caravana/remove/{idCaravana}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> removeUser(@PathVariable Integer idCaravana, 
    		@PathVariable Integer idUsuario){
    	String body = "erro";
    	Caravanas c = servicec.findById(idCaravana);
    	Usuario u = service.findById(idUsuario);
    	if (u != null) {
    		if (c != null) {
    			body = service.removeUser(idCaravana,idUsuario);
    		}
    	}
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping(value = "/caravana/clean/{idCaravana}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> cleanCaravana(@PathVariable Integer idCaravana){
    	String body = "erro";
    	Caravanas c = servicec.findById(idCaravana);
    	if (c != null) {
    		body = service.cleanCaravanaUsers(idCaravana);
    	}
		return ResponseEntity.ok().body(body);
	}
}
