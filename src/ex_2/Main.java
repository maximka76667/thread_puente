package ex_2;

public class Main {

	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			new Thread(new Coche(i)).start();
		}
	}

}
