
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.stereotype.Repository;


//@Repository
public class LinkedUC_DAO implements DAO<LinkedUC, Integer> {
	private String filename = "LinkedUCs.bin";
	private File file = new File(filename);
	private static List<LinkedUC> LinkedUCs;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public LinkedUC_DAO() throws IOException {
		if (!file.exists()) {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);
		}
		LinkedUCs = new ArrayList<LinkedUC>();
		readFromFile();
	}
	
	@Override
	public LinkedUC get(Integer id) {
		readFromFile();
		for (LinkedUC usu : LinkedUCs) {
			if (usu.getCaravanaId().equals(id)) {
				return usu;
			}
		}
		return null;
	}
	
	public LinkedUC getUser(Integer id) {
		readFromFile();
		for (LinkedUC usu : LinkedUCs) {
			if (usu.getUserId().equals(id)) {
				return usu;
			}
		}
		return null;
	}

	@Override
	public boolean add(LinkedUC LinkedUC) {
		try { 
			if(!LinkedUCs.contains(LinkedUC)) {
				LinkedUCs.add(LinkedUC);
			}else{
				return false;
			}
			saveToFile();
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o LinkedUC '" + LinkedUC.getUserId() + "' no disco!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(LinkedUC LinkedUC) {
		int index = LinkedUCs.indexOf(LinkedUC);
		if (index != -1) {
			LinkedUCs.set(index, LinkedUC);
		}
		saveToFile();
	}

	@Override
	public void remove(LinkedUC LinkedUC) {
		int index = LinkedUCs.indexOf(LinkedUC);
		if (index != -1) {
			LinkedUCs.remove(index);
		}
		saveToFile();

	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (LinkedUC LinkedUC : LinkedUCs) {
				outputFile.writeObject(LinkedUC);
			}
			outputFile.flush();
			readFromFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar LinkedUC no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<LinkedUC> getAll() {
		return LinkedUCs;
	}

	private List<LinkedUC> readFromFile() {
		LinkedUCs = new ArrayList<LinkedUC>();
		LinkedUC LinkedUC = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				LinkedUC = (LinkedUC) inputFile.readObject();
				LinkedUCs.add(LinkedUC);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar LinkedUC no disco!");
			e.printStackTrace();
		}
		return LinkedUCs;
	}
	
	public long getQtdeCaravanasPossuiUsuario(Integer id){
		return LinkedUCs.stream().filter(link -> link.getCaravanaId().equals(id)).count();
	}

	public int count() {
		return readFromFile().size(); 
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
	}

}