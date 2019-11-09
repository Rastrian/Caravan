import java.util.List;
import java.util.stream.Collectors;

public class CollectionsEndereco {
	private EnderecoDAO arqEndereco;
	private List<Endereco> lista;
	
	public CollectionsEndereco(){
		
	}
	
	public List<Endereco> getPessoasDeUmaMesmaCidade(String cidade){
		lista = arqEndereco.readFromFile();
		return lista.stream().filter(end -> end.getCidade().equals(cidade)).collect(Collectors.toList());
	}

	public List<Endereco> getLista() {
		return lista;
	}

}
