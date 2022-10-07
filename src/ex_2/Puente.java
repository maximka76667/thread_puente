package ex_2;

import java.util.ArrayList;

public class Puente {
	private ArrayList<Coche> coches;

	public Puente() {
		coches = new ArrayList<Coche>();
	}

	public ArrayList<Coche> getCoches() {
		return coches;
	}

	public int getPeso() {
		int peso = 0;
		for (Coche coche : coches) {
			peso += coche.getPeso();
		}
		return peso;
	}

	public String showCochesWithId() {
		String s = "";
		for (int i = 0; i < coches.size(); i++) {
			s += coches.get(i).toStringWithId();
			if (i != coches.size() - 1)
				s += ", ";
		}
		return s;
	}
}
