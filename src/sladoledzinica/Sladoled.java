package sladoledzinica;

import java.util.ArrayList;
import java.util.HashMap;

public class Sladoled {
	
	private ArrayList<Ukus> ukusi = new ArrayList<>();
	private HashMap<String, Integer> kolicineUkusa = new HashMap<>();
	private int velicina, ispunjenost;
	
	public Sladoled(int velicina) {
		this.velicina = velicina;
	}
	
	public Sladoled dodajUkus(Ukus u, int kolicina) {
		if (ispunjenost == velicina)
			return this;
		int kol = kolicina;
		if (ispunjenost + kol > velicina)
			kol = velicina - ispunjenost;
		for (Ukus ukus : ukusi) {
			if (ukus.equals(u)) {
				kolicineUkusa.replace(u.dohvNaziv(), kolicineUkusa.get(u.dohvNaziv()) + kol);
				ispunjenost += kol;
				return this;
			}
		}
		ukusi.add(u);
		kolicineUkusa.put(u.dohvNaziv(), kol);
		ispunjenost += kol;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Ukus u : ukusi)
			sb.append(kolicineUkusa.get(u.dohvNaziv()) + "ml" + u + " ");
		return sb.toString();
	}
}
