package ar.edu.unlu.tools;

import java.io.Serializable;

public class Intencion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String[] out = {"salir", "exit", "slir", "salida", "ecsit", "renuncio", "me retiro", "no juego más", "no juego mas", "quit", "me rindo", "rindo", "rendir"};
	private String[] positive = {"si", "s", "yes", "y", "sisi", "sis", "yyes"};
	private String[] negative = {"no", "n", "nop", "nope", "nono", "nain", "nel"};
	private String[] guardar = {"almacenar progreso", "conservar avance", "mantener estado de juego", "retener etapa", "preservar desarrollo", "guardar fase",
								"archivar nivel", "save", "guardar", "guard", "saver", "gurdar", "guadar", "guar", "guord", "gardar", "gard", "guerder",
								"guardar partida", "save game", "saving", "game save", "progress save"};
	private String[] allin = {
		    "apostar todo", "ir con todo", "todo adentro", "jugarlo todo", "jugarse todo", "arriesgar todo", "apuesta total", "apuesta máxima", "apuesta completa", "apuesta total", "apuesta máxima",
		    "go all-in", "bet it all", "all-in bet", "put it all on", "risk it all", "bet everything", "bet the farm", "bet the house", "go big or go home", "all in", 
		    "allin", "todo pa"};
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

	public boolean save(String input) {
		return this.recorrer(input, this.guardar);
	}
	
	public boolean allIn(String input) {
		return this.recorrer(input, this.allin);
	}
}
