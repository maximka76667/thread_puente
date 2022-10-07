package ex_2;

import java.util.ArrayList;

public class Coche implements Runnable {

	private static ArrayList<Coche> coches;
	private static ArrayList<Coche> cochesAntesPuente;
	private static ArrayList<Coche> cochesDespuesPuente;

	static {
		Coche.cochesAntesPuente = new ArrayList<Coche>();
		Coche.coches = new Puente().getCoches();
		Coche.cochesDespuesPuente = new ArrayList<Coche>();
	}

	private int id;
	private boolean isPassed;

	public Coche(int id) {
		this.id = id;
		this.isPassed = false;
		cochesAntesPuente.add(this);
	}

	public int getId() {
		return id;
	}

	public void setIsPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}

	public void poner() throws InterruptedException {
		synchronized (coches) {
			while (coches.size() == 3) {
				coches.wait();
			}
			cochesAntesPuente.remove(this);
			coches.add(0, this);
			Thread.sleep(950);
			System.out.println(cochesAntesPuente + " = " + coches + " = " + cochesDespuesPuente);
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
			if (cochesAntesPuente.size() == 0) {
				Thread.sleep(950);
				System.out.println(cochesAntesPuente + " = " + coches + " = " + cochesDespuesPuente);
			}
			coches.notifyAll();
		}
	}

	@Override
	public String toString() {
		return "" + id;
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
}
