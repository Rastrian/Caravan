import java.io.Serializable;

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Endereco endereco;
	private String email;
	private String senha;
	private boolean Turista;
	private int TuristaID;
	
	public Usuario(int id,String nome,Endereco endereco,String email,String senha,boolean turista) {
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setSenha(senha);
		setTurista(turista);
		this.id = id;
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
	
	@Override
	public boolean equals(Object obj) {
		return this.id.equals(((Usuario) obj).getId());
	}
}
