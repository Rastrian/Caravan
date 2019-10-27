

import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;



public class Caravanas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String nome;
	private String descricao;
	private LocaisTuristicos local;
	private LocalDate data;
	private Usuario owner;
	private ArrayList<Usuario> usuarios;
	

	public Caravanas() {
	}
	
	public Caravanas(int id, String nome, String descricao, LocaisTuristicos local,int dia,int mes,int ano, Usuario owner) {
		setNome(nome);
		setDescricao(descricao);
		setLocal(local);
		setOwner(owner);
		data = LocalDate.of(ano, mes, dia);
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