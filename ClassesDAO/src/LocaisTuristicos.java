import java.io.Serializable;

public class LocaisTuristicos implements Serializable {
	private Integer id;
	private String nome;
	private String descricao;
	private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public LocaisTuristicos(int id, String nome, String descricao, boolean status) {
		setId(id);
		setNome(nome);
		setDescricao(descricao);
		setStatus(status);
	}
	
	
}
