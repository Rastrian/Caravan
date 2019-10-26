import java.io.IOException;
import java.util.List;

public class Aplicacao {

	public static void main(String[] args) throws IOException {
		CaravanasDAO caravanas = new CaravanasDAO("Caravanas");
		LocaisTuristicos local = new LocaisTuristicos("Japao","Local bonito");
		Caravanas a = new Caravanas("Livre","passeio pela liberadade",local,1,12,2019);
		Caravanas b = new Caravanas("Liv","passeio pela liberdaade",local,1,12,2019);
		Caravanas c = new Caravanas("Livreee","passeio pela ldiberdade",local,1,12,2019);
		Caravanas d = new Caravanas("Livreaa","passeio pelaa liberdade",local,1,12,2019);
		
		caravanas.add(a);
		caravanas.add(b);
		caravanas.add(c);
		caravanas.add(d);
		List<Caravanas> lista = caravanas.getAll();
		
		for(Caravanas car:lista) {
			System.out.println(car.getId());
			System.out.println(car.getNome());
			System.out.println(car.getDescricao());
		}
		
		System.out.println(lista.indexOf(b));
		
	}

}