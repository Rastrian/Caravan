import java.io.Serializable;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private Endereco endereco;
	private String email;
	private String senha;
	private boolean Turista;
	private int TuristaID;
	
	public Usuario(String nome,Endereco endereco,String email,String senha,boolean turista,int IDturista) {
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setSenha(senha);
		setTurista(turista);
		setTuristaID(IDturista);
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

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
	public void setTurista(boolean turista) {
		Turista = turista;
	}
	public int getTuristaID() {
		return TuristaID;
	}
	public void setTuristaID(int turistaID) {
		TuristaID = turistaID;
	}
}
