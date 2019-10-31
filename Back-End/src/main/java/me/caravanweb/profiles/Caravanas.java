package me.caravanweb.profiles;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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
	private LocalDate data;
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
	private ArrayList<Usuario> usuarios = null;
	
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
	}
	
	public int getDia() {
		return this.dia;
	}
	public int getMes() {
		return this.mes;
	}
	public int getAno() {
		return this.ano;
	}
	public int getLocalId() {
		return this.localId;
	}
	public int getOwnerId() {
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
	public boolean addUser(Usuario u) {
		usuarios.add(u);
		return hadUser(u);
	}
	public boolean hadUser(Usuario u) {
		if (usuarios.contains(u)) {
			return true;
		}else {
			return false;
		}
	}
	public boolean removeUser(Usuario u) {
		usuarios.remove(u);
		return (!hadUser(u));
	}
	public ArrayList<Usuario> getUsers() {
		return usuarios;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Caravanas) obj).getId());
	}
	
}