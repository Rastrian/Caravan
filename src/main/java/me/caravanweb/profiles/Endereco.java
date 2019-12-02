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
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Column(nullable=false) 
	private String endereco;
	@Column(nullable=false) 
	private Long telefone;
	@Column(nullable=false)
	private String cidade;
	@Column(nullable=false)
	private String estado;
	@Column(nullable=false)
	private String pais;
	@Column(nullable=false) 
	private Long cep;

	public Endereco() {
	}
	
	@JsonIgnore
	public Endereco(String endereco, Long telefone, Long cep, String cidade, String estado, String pais) {
		super();
		setEndereco(endereco);
		setTelefone(telefone);
		setCEP(cep);
		setCidade(cidade);
		setEstado(estado);
		setPais(pais);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}
	
	public Long getTelefone() {
		return telefone;
	}
	
	public Long getCEP() {
		return cep;
	}
	
	public String getPais() {
		return pais;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setEndereco(String newEndereco) {
		endereco = newEndereco;
	}
	
	public void setCidade(String newCidade) {
		cidade = newCidade;
	}
	
	public void setTelefone(Long newTelefone) {
		telefone = newTelefone;
	}
	
	public void setCEP(Long newCEP) {
		cep = newCEP;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Endereco) obj).getId());
	}
}
