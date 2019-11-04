package me.caravanweb.profiles;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.caravanweb.services.CaravanasService;
import me.caravanweb.services.UsuarioService;

public class LinkedUC {
	@Column(nullable=false,unique=true) 
	private Integer idCaravana;
	@Column(nullable=false,unique=true) 
	private Integer idUsuario;

	@Autowired
	private UsuarioService service;
	@Autowired
	private CaravanasService servicec;
	
	public LinkedUC() {
	}
	
	@JsonIgnore
	public LinkedUC(Integer idCaravana, Integer idUsuario) {
		super();
		this.idCaravana = idCaravana;
		this.idUsuario = idUsuario;
	}
	
	public Integer getCaravanaId() {
		return idCaravana;
	}
	
	public Integer getUserId() {
		return idUsuario;
	}
	
	public Caravanas getCaravana() {
		return servicec.findById(idCaravana);
	}
	
	public Usuario getUsuario() {
		return service.findById(idUsuario);
	}
}
