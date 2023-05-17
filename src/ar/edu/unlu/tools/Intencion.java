package ar.edu.unlu.tools;

import java.io.Serializable;

public class Intencion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String[] out = {"salir", "exit", "slir", "salida", "ecsit", "renuncio", "me retiro", "no juego más", "no juego mas", "quit", "me rindo", "rindo", "rendir"};
	private String[] positive = {"si", "s", "yes", "y", "sisi", "sis", "yyes"};
	private String[] negative = {"no", "n", "nop", "nope", "nono", "nain", "nel"};
	// Truco para el ingreso de dinero.
	private String[] esoyam = {"esoyam", "dame plata", "platita", "biyuya", "plata", "una monedita por favor"};
	private String[] help = {"help", "aiuda", "ayuda", "comandos", "cmd", "-help", "help!"};

	// Recorre el array dado, comparando sus elementos con 'input'. Retorna true si alguno coincide.
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

	// Intenta salir.
	public boolean out(String input) {
		return this.recorrer(input, this.out);
	}

	// Respuesta positiva.
	public boolean positive(String input) {
		return this.recorrer(input, this.positive);
	}

	// Respuesta negativa.
	public boolean negative(String input) {
		return this.recorrer(input, this.negative);
	}

	// Pide dinero.
	public boolean esoyam(String input) {
		return this.recorrer(input, this.esoyam);
	}

	// Pide ayuda.
	public boolean help(String input) {
		return this.recorrer(input, this.help);
	}
}
