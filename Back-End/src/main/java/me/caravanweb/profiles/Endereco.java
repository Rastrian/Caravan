package me.caravanweb.profiles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false) 
	private String endereco;
	@Column(nullable=false) 
	private long telefone;
	private Long cep;
	@Column(nullable=false) 
	private String cidade;

	@JsonIgnore
	public Endereco() {
		super();
		telefone = 0;
		cep = (long) 0;
		endereco = "";
		cidade = "naoLocalizada";
	}

	public Long getId() {
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
	
	public void setTelefone(long newTelefone) {
		if (newTelefone >= 10000000 && newTelefone <= 100000000)
			telefone = newTelefone;
		else
			telefone = 0;
	}
	
	public void setCEP(long newCEP) {
		if (newCEP >= 10000000 && newCEP <= 100000000)
			cep = newCEP;
		else
			cep = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Endereco) obj).getId());
	}
}
