import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;



public class LocaisTuristicosDAO implements DAO<LocaisTuristicos,Integer>{
	
	private String filename = "locais.bin";
	
	private File file = new File(filename);
	private static List<LocaisTuristicos> locais;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	
	public LocaisTuristicosDAO() throws IOException {
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
		for (LocaisTuristicos local : locais) {
			if (local.getId() == id) {
				return local;
			}
		}
		return null;
	}

	@Override
	public boolean add(LocaisTuristicos local) {
		try {
			if(!locais.contains(local))
				locais.add(local);
			saveToFile();
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o produto '" + local.getId() + "' no disco!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(LocaisTuristicos local) {
		int index = locais.indexOf(local);
		if (index != -1) {
			locais.set(index, local);
		}
		saveToFile();
	}

	@Override
	public void remove(LocaisTuristicos local) {
		int index = locais.indexOf(local);
		if (index != -1) {
			locais.remove(index);
		}
		saveToFile();
	}
	
	public int count() {
		return readFromFile().size();
	}
	
	private void saveToFile() {
		try {
			FileOutputStream fos2 = new FileOutputStream(file, false); 
			ObjectOutputStream outputFile2 = new ObjectOutputStream(fos2);

			for (LocaisTuristicos local : locais) {
				outputFile2.writeObject(local);
			}
			outputFile2.flush();
			readFromFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<LocaisTuristicos> getAll() {
		return locais;
	}
	
	private List<LocaisTuristicos> readFromFile() {
		locais = new ArrayList<LocaisTuristicos>();
		LocaisTuristicos local = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				local = (LocaisTuristicos) inputFile.readObject();
				locais.add(local);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
		return locais;
	}
	
	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}
	
	protected void finalize() throws Throwable {
		this.close();
	}
}