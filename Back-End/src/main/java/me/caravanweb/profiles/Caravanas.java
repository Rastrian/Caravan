package me.caravanweb.profiles;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Caravanas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	@Column(nullable=false) 
	private String nome;
	@Column(nullable=false) 
	private String descricao;
	@Column(nullable=false) 
	private Integer ownerId;
	@Column(nullable=false) 
	private Integer localId;
	@Column(nullable=false) 
	private Integer dia;
	@Column(nullable=false) 
	private Integer mes;
	@Column(nullable=false) 
	private Integer ano;

	private Usuario owner = null;
	private LocaisTuristicos local = null;
	private LocalDate data;
	private String imgUrl = "./assets/img/default-caravana.png";
	
	public Caravanas() {
	}
	
	@JsonIgnore
	public Caravanas(String nome, String descricao, int localId, int ownerId,int dia,int mes,int ano) {
		super();
		setNome(nome);
		setDescricao(descricao);
		setOwnerId(ownerId);
		setLocalId(localId);
		data = LocalDate.of(ano, mes, dia);
		this.ownerId = ownerId;
		this.localId = localId;
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
		this.imgUrl = "./assets/img/default-caravana.png";
	}
	
	public void seturlImg(String url) {
		this.imgUrl = url;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public Integer getDia() {
		return this.dia;
	}
	public Integer getMes() {
		return this.mes;
	}
	public Integer getAno() {
		return this.ano;
	}
	public Integer getLocalId() {
		return this.localId;
	}
	public Integer getOwnerId() {
		return this.ownerId;
	}
	public void setLocalId(int localId) {
		this.localId = localId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public void setId(int id) {
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocaisTuristicos getLocal() {
		return local;
	}
	public void setLocal(LocaisTuristicos local) {
		this.local = local;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public void setOwner(Usuario user) {
		this.owner = user;
	}
	public Usuario getOwner() {
		return this.owner;
	}
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Caravanas) obj).getId());
	}
	
}