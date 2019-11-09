import java.util.List;

public class CollectionsLocaisTuristicos {
	
	private LocalTuristicosDAO arqLocaisTuristicos;
	private List<LocaisTuristicos> lista;
	
	public CollectionsLocaisTuristicos() {
		
	}
	
	public LocaisTuristicos getByName(String nome) {
		lista = arqLocaisTuristicos.readFromFile();
		return ((LocaisTuristicos) lista.stream().filter(locais -> locais.getNome().equals(nome)));
	}

	public List<LocaisTuristicos> getLista() {
		return lista;
	}
}
