package ar.edu.unlu.tools;

import java.io.Serializable;

public class Intencion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String[] out = {"salir", "exit", "slir", "salida", "ecsit"};
	private String[] positive = {"si", "s", "yes", "y", "sisi", "sis", "yyes"};
	private String[] negative = {"no", "n", "nop", "nope", "nono", "nain", "nel"};
	private String[] quit = {"renuncio", "me retiro", "no juego más", "no juego mas", "quit", "me rindo", "rindo", "rendir"};
	// Truco para el ingreso de dinero.
	private String[] esoyam = {"esoyam", "dame plata", "platita", "biyuya", "plata", "una monedita por favor"};
	private String[] help = {"help", "aiuda", "ayuda", "comandos", "cmd", "-help", "help!"};

	private boolean recorrer(String input, String[] array) {
		boolean res = false;
		input = input.toLowerCase();

		for (String str : array) {
			if (str.equals(input)) {
				res = true;
			}
		}

		return res;
	}

	public boolean out(String input) {
		return this.recorrer(input, this.out);
	}

	public boolean positive(String input) {
		return this.recorrer(input, this.positive);
	}

	public boolean negative(String input) {
		return this.recorrer(input, this.negative);
	}

	public boolean quit(String input) {
		return this.recorrer(input, this.quit);
	}

	public boolean esoyam(String input) {
		return this.recorrer(input, this.esoyam);
	}

	public boolean help(String input) {
		return this.recorrer(input, this.help);
	}
}
