package ex_1;

public class Main {

	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			new Thread(new Coche(i)).start();
		}
	}

}
