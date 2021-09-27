package sladoledzinica;

import java.awt.Color;

public class Ukus {
	
	private String naziv;
	private Color boja;

	public Ukus(String naziv, Color boja) {
		this.naziv = naziv;
		this.boja = boja;
	}
	
	public String dohvNaziv() {
		return naziv;
	}
	public Color dohvBoju() {
		return boja;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Ukus))
			return false;
		return this.naziv == ((Ukus)o).naziv;
	}
	
	@Override
	public String toString() {
		return "[" + naziv + "]";
	}
}
