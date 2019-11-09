import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LocaisTuristicosDAO implements DAO<LocaisTuristicos, Integer> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public LocaisTuristicosDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false);
		outputFile = new ObjectOutputStream(fos);
	}

	@Override
	public LocaisTuristicos get(Integer id) {
		LocaisTuristicos local = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				local = (LocaisTuristicos) inputFile.readObject();

				if (id.equals(local.getId())) {
					return local;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o local '" + id + "' do disco!");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void add(LocaisTuristicos local) {
		List<LocaisTuristicos> locais = this.getAll();
		boolean check = true;
		for (LocaisTuristicos loc : locais) {
			if (loc.getId() == local.getId())
				check = false;
		}
		if (check) {
			try {
				outputFile.writeObject(local);
			} catch (Exception e) {
				System.out.println("ERRO ao gravar o local '" + local.getDescricao() + "' no disco!");
				e.printStackTrace();
			}
		} else
			System.out.println("Local turistico com mesmo id ja cadastrado");

	}

	@Override
	public void update(LocaisTuristicos local) {
		List<LocaisTuristicos> locais = getAll();
		int index = locais.indexOf(local);
		if (index != -1) {
			locais.set(index, local);
		}
		saveToFile(locais);

	}

	@Override
	public void remove(LocaisTuristicos local) {
		List<LocaisTuristicos> locais = getAll();
		int index = locais.indexOf(local);
		if (index != -1) {
			locais.remove(index);
		}
		saveToFile(locais);

	}

	private void saveToFile(List<LocaisTuristicos> locais) {
		try {
			close();
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (LocaisTuristicos local : locais) {
				outputFile.writeObject(local);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar local no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<LocaisTuristicos> getAll() {
		List<LocaisTuristicos> locais = new ArrayList<LocaisTuristicos>();
		LocaisTuristicos local = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				local = (LocaisTuristicos) inputFile.readObject();
				locais.add(local);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar local no disco!");
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
