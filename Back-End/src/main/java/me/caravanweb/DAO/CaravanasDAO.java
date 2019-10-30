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
		for (Caravanas caravana : caravanas) {
			if (caravana.getId() == id) {
				return caravana;
			}
		}
		return null;
	}

	@Override
	public boolean add(Caravanas caravana) {
		try {
			if (!caravanas.contains(caravana))
				caravanas.add(caravana);
			saveToFile();
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o produto '" + caravana.getId() + "' no disco!");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void update(Caravanas caravana) {
		int index = caravanas.indexOf(caravana);
		if (index != -1) {
			caravanas.set(index, caravana);
		}
		saveToFile();

	}

	@Override
	public void remove(Caravanas caravana) {
		int index = caravanas.indexOf(caravana);
		if (index != -1) {
			caravanas.remove(index);
		}
		saveToFile();

	}

	private void saveToFile() {
		try {
			FileOutputStream fos2 = new FileOutputStream(file, false);
			ObjectOutputStream outputFile2 = new ObjectOutputStream(fos2);

			for (Caravanas car : caravanas) {
				outputFile2.writeObject(car);
			}
			outputFile2.flush();
			readFromFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar caravana no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Caravanas> getAll() {
		return caravanas;
	}

	private List<Caravanas> readFromFile() {
		caravanas = new ArrayList<Caravanas>();
		Caravanas caravana = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream inputFile = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				caravana = (Caravanas) inputFile.readObject();
				caravanas.add(caravana);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar caravana no disco!");
			e.printStackTrace();
		}
		return caravanas;
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
