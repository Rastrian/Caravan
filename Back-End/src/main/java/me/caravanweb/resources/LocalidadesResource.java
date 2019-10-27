package me.caravanweb.resources;

import me.caravanweb.profiles.LocaisTuristicos;
import me.caravanweb.services.LocalidadesService;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/localidades")
public class LocalidadesResource {
	@Autowired
	private LocalidadesService service;
	
	@GetMapping
	public ResponseEntity<List<LocaisTuristicos>> findAll() {
		List<LocaisTuristicos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LocaisTuristicos> findById(@PathVariable Integer id) {
		LocaisTuristicos obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LocaisTuristicos> create(@RequestBody LocaisTuristicos local){
		boolean status = service.add(local);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(local.getId()).toUri();
		return ResponseEntity.created(uri).body(local);
    }
    
    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	LocaisTuristicos local = service.findById(id);
    	String body = "";
		boolean status = service.delete(local);
		if (status) {
			body = "Deletado.";
		}
		return ResponseEntity.ok().body(body);    
	}
    
}
