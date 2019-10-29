package me.caravanweb.profiles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LocaisTuristicos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false) 
	private String nome;
	@Column(nullable=false) 
	private String descricao;
	private boolean status;
	private String imgUrl = "./assets/img/default-local.png";
	
	public LocaisTuristicos() {
	}
	
	@JsonIgnore
	public LocaisTuristicos(String nome, String descricao) {
		super();
		setNome(nome);
		setDescricao(descricao);
		this.status = true;
		this.imgUrl = "./assets/img/default-local.png";
	}
	
	public void seturlImg(String url) {
		this.imgUrl = url;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((LocaisTuristicos) obj).getId());
	}
	
	
}
