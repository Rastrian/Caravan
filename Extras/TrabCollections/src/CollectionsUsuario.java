import java.util.List;
import java.util.stream.Collectors;

public class CollectionsUsuario {
	private UsuarioDAO arqUsuario;
	private List<Usuario> lista;

	public CollectionsUsuario() {

	}

	public List<Usuario> getUsuariosTuristas() {
		lista = arqUsuario.readFromFile();
		return lista.stream().filter(usu -> usu.isTurista() == true).collect(Collectors.toList());
	}

	public List<Usuario> getUsuariosNaoTuristas() {
		lista = arqUsuario.readFromFile();
		return lista.stream().filter(usu -> usu.isTurista() == false).collect(Collectors.toList());
	}

	public List<Usuario> getLista() {
		return lista;
	}
	
	
}
