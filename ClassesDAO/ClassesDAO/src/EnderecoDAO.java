import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO implements DAO<Endereco,Long> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	public EnderecoDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	@Override
	public Endereco get(Long cep) {
		Endereco endereco = null;
		 
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				endereco = (Endereco) inputFile.readObject();

				if (cep.equals(endereco.getCEP())) {
					return endereco;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o endereco '" + cep + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Endereco endereco) {
		try {
			outputFile.writeObject(endereco);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o endereco '" + endereco.getCEP() + "' no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Endereco endereco) {
		List<Endereco> enderecos = getAll();
		int index = enderecos.indexOf(endereco);
		if (index != -1) {
			enderecos.set(index, endereco);
		}
		saveToFile(enderecos);
		
	}

	@Override
	public void remove(Endereco endereco) {
		List<Endereco> enderecos = getAll();
		int index = enderecos.indexOf(endereco);
		if (index != -1) {
			enderecos.remove(index);
		}
		saveToFile(enderecos);
		
	}
	
	private void saveToFile(List<Endereco> enderecos) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Endereco endereco : enderecos) {
				outputFile.writeObject(endereco);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar endereco no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Endereco> getAll() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		Endereco endereco = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				endereco = (Endereco) inputFile.readObject();
				enderecos.add(endereco);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar endereco no disco!");
			e.printStackTrace();
		}
		return enderecos;
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
