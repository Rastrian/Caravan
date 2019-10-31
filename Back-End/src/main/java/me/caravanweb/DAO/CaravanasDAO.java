package me.caravanweb.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import me.caravanweb.profiles.Caravanas;

@Repository
public class CaravanasDAO implements DAO<Caravanas, Integer> {
	private String filename = "caravanas.bin";
	private File file = new File(filename);
	private static List<Caravanas> caravanas;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public CaravanasDAO() throws IOException {
		if (!file.exists()) {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);
		}
		caravanas = new ArrayList<Caravanas>();
		readFromFile();
	}

	@Override
	public Caravanas get(Integer id) {
		readFromFile();
		for (Caravanas usu : caravanas) {
			if (usu.getId().equals(id)) {
				return usu;
			}
		}
		return null;
	}

	@Override
	public boolean add(Caravanas Caravanas) {
		try { 
			if(!caravanas.contains(Caravanas)) {
				caravanas.add(Caravanas);
			}else{
				return false;
			}
			saveToFile();
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Caravanas '" + Caravanas.getId() + "' no disco!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(Caravanas Caravanas) {
		int index = caravanas.indexOf(Caravanas);
		if (index != -1) {
			caravanas.set(index, Caravanas);
		}
		saveToFile();
	}

	@Override
	public void remove(Caravanas Caravanas) {
		int index = caravanas.indexOf(Caravanas);
		if (index != -1) {
			caravanas.remove(index);
		}
		saveToFile();

	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Caravanas Caravanas : caravanas) {
				outputFile.writeObject(Caravanas);
			}
			outputFile.flush();
			readFromFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Caravanas no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Caravanas> getAll() {
		return caravanas;
	}

	private List<Caravanas> readFromFile() {
		caravanas = new ArrayList<Caravanas>();
		Caravanas Caravanas = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				Caravanas = (Caravanas) inputFile.readObject();
				caravanas.add(Caravanas);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar Caravanas no disco!");
			e.printStackTrace();
		}
		return caravanas;
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
