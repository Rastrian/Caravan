package me.caravanweb.resources.api;

import java.util.ArrayList;
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

import me.caravanweb.profiles.Endereco;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/endereco")
public class EnderecoResource {
	
	@Autowired private UsuarioService serviceu;
	
	@GetMapping
	public ResponseEntity<ArrayList<Endereco>> findAll() {
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		serviceu.findAll().forEach(u -> enderecos.add(u.getEndereco()));
		return ResponseEntity.ok().body(enderecos);
	}

    @PostMapping(value = "/register/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> add(Endereco e, @PathVariable Integer userId){
    	String msg = "InternalError";
    	Usuario user = serviceu.findById(userId);
    	if (user != null) {
    		e.setId(user.getId());
    		serviceu.setEndereco(userId, e);
    		msg = "success";
    	}else{
    		msg = "UserNotFound";
    	}
		return ResponseEntity.ok().body(msg);
	}
}
