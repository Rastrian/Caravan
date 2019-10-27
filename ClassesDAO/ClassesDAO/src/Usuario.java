package me.caravanweb.profiles;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Endereco endereco;
	private String email;
	private String senha;
	private boolean Turista;
	private ArrayList<Caravanas> caravanas;
	private boolean admin;
	
	@JsonIgnore
	public Usuario() {
	}
	
	public Usuario(int id,String nome,Endereco endereco,String email,String senha,boolean turista) {
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setSenha(senha);
		setTurista(turista);
		this.id = id;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isTurista() {
		return Turista;
	}
	public void setTurista(boolean turista) {
		Turista = turista;
	}
	
	public boolean addCaravana(Caravanas cara) {
		caravanas.add(cara);
		return hasCaravana(cara);
	}
	public boolean hasCaravana(Caravanas cara) {
		if (caravanas.contains(cara))
			return true;
		return false;
	}
	public boolean removeCaravana(Caravanas cara) {
		caravanas.remove(cara);
		return (!hasCaravana(cara));
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isAdmin() {
		return this.admin;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Usuario) obj).getId());
	}
}