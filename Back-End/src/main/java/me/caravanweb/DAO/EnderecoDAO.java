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

import me.caravanweb.profiles.Endereco;

@Repository
public class EnderecoDAO implements DAO<Endereco, Integer> {
	private String filename = "enderecos.bin";
	private File file = new File(filename);
	private static List<Endereco> enderecos;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public EnderecoDAO() throws IOException {
		if (!file.exists()) {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);
		}
		enderecos = new ArrayList<Endereco>();
		readFromFile();
	}

	@Override
	public Endereco get(Integer id) {
		readFromFile();
		for (Endereco end : enderecos) {
			if (end.getId() == id) {
				return end;
			}
		}
		return null;
	}

	@Override
	public boolean add(Endereco endereco) {
		try {
			if(!enderecos.contains(endereco))
				enderecos.add(endereco);
			saveToFile();
			return true;
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o endereco '" + endereco.getId() + "' no disco!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(Endereco endereco) {
		int index = enderecos.indexOf(endereco);
		if (index != -1) {
			enderecos.set(index, endereco);
		}
		saveToFile();

	}

	@Override
	public void remove(Endereco endereco) {
		int index = enderecos.indexOf(endereco);
		if (index != -1) {
			enderecos.remove(index);
		}
		saveToFile();

	}

	private void saveToFile() {
		try {
			FileOutputStream fos2 = new FileOutputStream(file, false); 
			ObjectOutputStream outputFile2 = new ObjectOutputStream(fos2);

			for (Endereco end : enderecos) {
				outputFile2.writeObject(end);
			}
			outputFile2.flush();
			readFromFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar endereco no disco!");
			e.printStackTrace();
		}
	}
	
	private List<Endereco> readFromFile() {
		enderecos = new ArrayList<Endereco>();
		Endereco endereco = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream inputFile = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				endereco = (Endereco) inputFile.readObject();
				enderecos.add(endereco);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
		return enderecos;
	}

	@Override
	public List<Endereco> getAll() {
		return enderecos;
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
