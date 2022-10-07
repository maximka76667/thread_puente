package main;

public class Main {

	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			new Thread(new Coche(i)).start();
		}
	}

}
