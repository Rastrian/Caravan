import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario,Integer> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	
	public UsuarioDAO(String filename) throws IOException{
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Usuario get(Integer id) {
		Usuario usuario= null;
		
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				usuario = (Usuario) inputFile.readObject();

				if (id.equals(usuario.getId())) {
					return usuario;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o usuario '" + id + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Usuario usuario) {
		try {
			outputFile.writeObject(usuario);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o usuario '" + usuario.getNome() + "' no disco!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Usuario usuario) {
		List<Usuario> usuarios = getAll();
		int index = usuarios.indexOf(usuario);
		if (index != -1) {
			usuarios.set(index, usuario);
		}
		saveToFile(usuarios);
		
	}

	@Override
	public void remove(Usuario usuario) {
		List<Usuario> usuarios = getAll();
		int index = usuarios.indexOf(usuario);
		if (index != -1) {
			usuarios.remove(index);
		}
		saveToFile(usuarios);
		
	}
	
	private void saveToFile(List<Usuario> usuarios) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Usuario usuario : usuarios) {
				outputFile.writeObject(usuario);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar usuario no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				usuario = (Usuario) inputFile.readObject();
				usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar usuario no disco!");
			e.printStackTrace();
		}
		return usuarios;
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
