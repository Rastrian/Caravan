

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
public class LocaisTuristicos implements Serializable {
	private static final long serialVersionUID = 1L;

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Id
	private Integer id;
	//@Column(nullable=false) 
	private String nome;
	//@Column(nullable=false) 
	private String descricao;
	private String imgUrl = "./assets/img/default-local.png";
	
	public LocaisTuristicos() {
	}
	
	//@JsonIgnore
	public LocaisTuristicos(String nome, String descricao) {
		super();
		setNome(nome);
		setDescricao(descricao);
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
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((LocaisTuristicos) obj).getId());
	}
	
	
}