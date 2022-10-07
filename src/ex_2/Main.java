package ex_2;

public class Main {

	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			Coche coche = new Coche(i);
			new Thread(coche).start();
		}
		System.out.println("\n");

		System.out.format("%30s = %50s = %-30s \n", "El lado izqierda", StringUtils.center(" La puente ", 50, '-'),
				"El lado derecho");
	}

}
