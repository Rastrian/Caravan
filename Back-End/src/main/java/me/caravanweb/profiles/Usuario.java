package me.caravanweb.profiles;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Column(nullable=false) 
	private String nome;
	@Column(nullable=false,unique=true) 
	private String email;
	@Column(nullable=false) 
	private String senha;
	
	private Endereco address;
	private boolean Turista = true;
	private boolean admin = false;
	private String imgUrl = "./assets/img/default-user.png";
	
	public Usuario() {
	}
	
	@JsonIgnore
	public Usuario(String nome,String email,String senha) {
		super();
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		this.Turista=true;
		this.admin = false;
		this.imgUrl = "./assets/img/default-user.png";
	}
	
	public void seturlImg(String url) {
		this.imgUrl = url;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
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
	public boolean setTurista(boolean turista) {
		Turista = turista;
		return this.Turista;
	}
	public boolean setAdmin(boolean admin) {
		this.admin = admin;
		return this.admin;
	}
	public boolean isAdmin() {
		return this.admin;
	}
	
	public Endereco getEndereco() {
		return address;
	}
	public void setEndereco(Endereco address) {
		this.address = address;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Usuario) obj).getId());
	}
}