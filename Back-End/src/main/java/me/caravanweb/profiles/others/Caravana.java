package me.caravanweb.profiles.others;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.caravanweb.profiles.Caravanas;
import me.caravanweb.profiles.LocaisTuristicos;
import me.caravanweb.profiles.Usuario;
import me.caravanweb.services.CaravanasService;
import me.caravanweb.services.LocalidadesService;
import me.caravanweb.services.UsuarioService;

public class Caravana implements Serializable {
	
	@Autowired
	private CaravanasService service;
	private UsuarioService serviceu;
	private LocalidadesService servicel;
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	private Integer localId;
	private Integer ownerId;
	private Integer dia;
	private Integer mes;
	private Integer ano;
	
	public Caravana() {
	}
	
	public Caravana(String nome, String desc, Integer lid, Integer oid, Integer d, Integer m, Integer a) {
		super();
		setNome(nome);
		setDesc(desc);
		setLocalId(lid);
		setOwnerId(oid);
		setDia(d);
		setMes(m);
		setAno(a);
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDesc() {
		return descricao;
	}
	
	public Integer getLocalId() {
		return localId;
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public Integer getMes() {
		return mes;
	}
	
	public Integer getDia() {
		return dia;
	}
	
	public LocaisTuristicos getLocalTuristico(int id) {
		return servicel.findById(id);
	}
	
	public Usuario getOwner(int id) {
		return serviceu.findById(id);
	}
	
	public LocalDate getData(int dia, int mes, int ano) {
		return LocalDate.of(ano, mes, dia);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDesc(String desc) {
		this.descricao = desc;
	}
	
	public void setLocalId(Integer id) {
		this.localId = id;
	}
	
	public void setOwnerId(Integer id) {
		this.ownerId = id;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public void setDia(Integer dia) {
		this.dia = dia;
	}
}
