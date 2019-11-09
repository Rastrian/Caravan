

import java.io.Serializable;


public class LocaisTuristicos implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String nome;
	private String descricao;
	private boolean status;
	

	public LocaisTuristicos() {
		
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
	public LocaisTuristicos(String nome, String descricao) {
		super();
		setNome(nome);
		setDescricao(descricao);
		this.status = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((LocaisTuristicos) obj).getId());
	}
	
	
}
