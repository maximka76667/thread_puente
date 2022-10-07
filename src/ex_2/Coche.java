package ex_2;

import java.util.ArrayList;

public class Coche implements Runnable {

	private static ArrayList<Coche> coches;
	private static ArrayList<Coche> cochesAntesPuente;
	private static ArrayList<Coche> cochesDespuesPuente;
	private static Puente puente;

	static {
		Puente puente = new Puente();
		Coche.cochesAntesPuente = new ArrayList<Coche>();
		Coche.puente = puente;
		Coche.coches = puente.getCoches();
		Coche.cochesDespuesPuente = new ArrayList<Coche>();
	}

	private int id, peso;
	private boolean isPassed;

	public Coche(int id) {
		this.id = id;
		this.isPassed = false;
		this.peso = (int) Math.floor(800 + Math.random() * 500);
		cochesAntesPuente.add(this);
	}

	public int getId() {
		return id;
	}

	public int getPeso() {
		return peso;
	}

	public void setIsPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}

	public void poner() throws InterruptedException {
		synchronized (coches) {
			while (coches.size() == 3 || puente.getPeso() + this.getPeso() >= 3000) {
				coches.wait();
			}
			cochesAntesPuente.remove(this);
			coches.add(0, this);
			Thread.sleep(950);
			show();
			coches.notifyAll();
		}

		pasar();
	}

	public void pasar() throws InterruptedException {
		Thread.sleep(1000);
		synchronized (coches) {
			while (coches.size() == 0) {
				coches.wait();
			}
			Coche coche = coches.remove(coches.size() - 1);
			cochesDespuesPuente.add(0, coche);
			coche.setIsPassed(true);
			Thread.sleep(950);
			System.out.println("\n" + StringUtils.center(" " + coche + " acaba de pasar ", 120, '-') + "\n");
			show();
			coches.notifyAll();
		}
	}

	public String toStringWithId() {
		return "#" + id + " (" + peso + " kg)";
	}

	@Override
	public String toString() {
		return "#" + id;
	}

	@Override
	public void run() {
		if (!isPassed) {
			try {
				poner();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void show() {
		System.out.format("%30s | %50s | %-30s", cochesAntesPuente, StringUtils.center(puente.showCochesWithId(), 50),
				cochesDespuesPuente);
//		System.out.println("El lado izqierda " + cochesAntesPuente);
//		System.out.println("La puente " + coches);
//		System.out.println("El lado derecho " + cochesDespuesPuente);
		System.out.println();
	}
}
