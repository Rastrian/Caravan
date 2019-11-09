import java.util.List;

public class CollectionsLinkedUC {
	private LinkedUC_DAO arqLinkedUC;
	private List<LinkedUC> lista;
	
	public CollectionsLinkedUC() {
		
	}
	
	public long getQtdeCaravanasPossuiUsuario(Integer id){
		lista = arqLinkedUC.readFromFile();
		return lista.stream().filter(link -> link.getCaravanaId().equals(id)).count();
	}

	public List<LinkedUC> getLista() {
		return lista;
	}
}
