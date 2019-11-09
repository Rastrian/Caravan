
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
public class LocalTuristicosDAO implements DAO<LocaisTuristicos, Integer> {
	private String filename = "locais.bin";
	private File file = new File(filename);
	private static List<LocaisTuristicos> locais;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public LocalTuristicosDAO() throws IOException {
		if (!file.exists()) {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);
		}
		locais = new ArrayList<LocaisTuristicos>();
		readFromFile();
	}

	@Override
	public LocaisTuristicos get(Integer id) {
		readFromFile();
		for (LocaisTuristicos usu : locais) {
			if (usu.getId() == id) {
				return usu;
			}
		}
		return null;
	}

	@Override
	public boolean add(LocaisTuristicos LocaisTuristicos) {
		try { 
			if(!locais.contains(LocaisTuristicos)) {
				locais.add(LocaisTuristicos);
			}else{
				return false;
			}
			saveToFile();
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o LocaisTuristicos '" + LocaisTuristicos.getId() + "' no disco!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(LocaisTuristicos LocaisTuristicos) {
		int index = locais.indexOf(LocaisTuristicos);
		if (index != -1) {
			locais.set(index, LocaisTuristicos);
		}
		saveToFile();
	}

	@Override
	public void remove(LocaisTuristicos LocaisTuristicos) {
		int index = locais.indexOf(LocaisTuristicos);
		if (index != -1) {
			locais.remove(index);
		}
		saveToFile();

	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (LocaisTuristicos LocaisTuristicos : locais) {
				outputFile.writeObject(LocaisTuristicos);
			}
			outputFile.flush();
			readFromFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar LocaisTuristicos no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<LocaisTuristicos> getAll() {
		return locais;
	}

	public List<LocaisTuristicos> readFromFile() {
		locais = new ArrayList<LocaisTuristicos>();
		LocaisTuristicos LocaisTuristicos = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				LocaisTuristicos = (LocaisTuristicos) inputFile.readObject();
				locais.add(LocaisTuristicos);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar LocaisTuristicos no disco!");
			e.printStackTrace();
		}
		return locais;
	}
	
	public LocaisTuristicos getByName(String nome) {
		return ((LocaisTuristicos) locais.stream().filter(locais -> locais.getNome().equals(nome)));
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