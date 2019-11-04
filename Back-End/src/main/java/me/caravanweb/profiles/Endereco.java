package me.caravanweb.profiles;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String endereco = "undefined";
	private Long telefone = null;
	private Long cep = null;
	private String cidade = "undefined";

	public Endereco() {
	}
	
	@JsonIgnore
	public Endereco(String endereco, Long telefone, Long cep, String cidade) {
		super();
		this.endereco = endereco;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Integer getId() {
		return id;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public Endereco(String endereco, long telefone, long cep, String cidade) {
		this.endereco = endereco;
		setTelefone(telefone);
		setCEP(cep);
		this.cidade = cidade;
	}


	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}
	
	public long getTelefone() {
		return telefone;
	}
	
	public long getCEP() {
		return cep;
	}
	
	public void setEndereco(String newEndereco) {
		endereco = newEndereco;
	}
	
	public void setCidade(String newCidade) {
		cidade = newCidade;
	}
	
	public void setTelefone(Long newTelefone) {
		if (newTelefone >= 10000000 && newTelefone <= 100000000) {
			telefone = newTelefone;
		} else {
			telefone = null;
		}
	}
	
	public void setCEP(Long newCEP) {
		if (newCEP >= 10000000 && newCEP <= 100000000) {
			cep = newCEP;
		} else {
			cep = null;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Endereco) obj).getId());
	}
}
