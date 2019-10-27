package me.caravanweb.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.caravanweb.profiles.Caravanas;

public class CaravanasDAO implements DAO<Caravanas,Integer>{
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	
	public CaravanasDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	 
	@Override
	public Caravanas get(Integer id) {
		Caravanas caravana = null;
 
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream inputFile = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				caravana = (Caravanas) inputFile.readObject();

				if (id.equals(caravana.getId())) {
					return caravana;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler a caravana '" + id + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean add(Caravanas caravana) {
		try {
			outputFile.writeObject(caravana);
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a caravana '" + caravana.getDescricao() + "' no disco!");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void update(Caravanas caravana) {
		List<Caravanas> caravanas = getAll();
		int index = caravanas.indexOf(caravana);
		if (index != -1) {
			caravanas.set(index, caravana);
		}
		saveToFile(caravanas);
		
	}

	@Override
	public void remove(Caravanas caravana) {
		List<Caravanas> caravanas = getAll();
		int index = caravanas.indexOf(caravana);
		if (index != -1) {
			caravanas.remove(index);
		}
		saveToFile(caravanas);
		
	}
	
	private void saveToFile(List<Caravanas> caravanas) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Caravanas caravana : caravanas) {
				outputFile.writeObject(caravana);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar caravana no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Caravanas> getAll() {
		List<Caravanas> caravanas = new ArrayList<Caravanas>();
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
