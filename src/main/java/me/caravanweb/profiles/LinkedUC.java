package me.caravanweb.profiles;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LinkedUC implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,unique=true) 
	private Integer idCaravana;
	@Column(nullable=false,unique=true) 
	private Integer idUsuario;
	
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
}
