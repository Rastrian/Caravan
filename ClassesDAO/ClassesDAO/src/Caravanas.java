import java.io.Serializable;
import java.time.LocalDate;

public class Caravanas implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String descricao;
	private LocaisTuristicos local;
	private LocalDate data;
	
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
	
	public Caravanas(int id, String nome, String descricao, LocaisTuristicos local,int dia,int mes,int ano) {
		setNome(nome);
		setDescricao(descricao);
		setLocal(local);
		data = LocalDate.of(ano, mes, dia);
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Caravanas) obj).getId());
	}
	
}
