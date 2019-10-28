package me.caravanweb.resources.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.caravanweb.profiles.Usuario;
import me.caravanweb.profiles.others.Login;
import me.caravanweb.services.UsuarioService;

@RestController
@RequestMapping(value = "/api/users")
public class UsuarioResource {
	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/count")
	public ResponseEntity<String> count() {
		int obj = service.count();
		return ResponseEntity.ok().body(String.valueOf(obj));
	}
	
    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody Usuario u){
    	String body = service.add(u);
		return ResponseEntity.ok().body(body);
	}
    
    @PostMapping("/login")
    String login(
      @RequestParam("email") final String username,
      @RequestParam("senha") final String password) {
    	return service.auth(username,password);
    }

}
