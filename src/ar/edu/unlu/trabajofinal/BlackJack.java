package ar.edu.unlu.trabajofinal;

import java.util.ArrayList;
import java.util.Queue;
import ar.edu.unlu.mov.Controlador;
import java.util.LinkedList;

public class BlackJack {
	final private int MAXPLAYERS = 4;
	final private int MONTOINICIAL = 1000;
	private int cantPlayers = 0;
	private Crupier crupier;
	private ArrayList<JugadorBJ> players;
	private Queue<JugadorBJ> listaDeEspera;
	private Controlador cc;

	public BlackJack() {
		this.crupier = new Crupier();
		this.setPlayers();
		this.setListaDeEspera();
	}
	
	// Si no esta lleno, carga un nuevo jugador a la lista de espera.
	public int newPlayer(String name) {
		JugadorBJ newPlayer = null;
		int idAux = -1;
		
		if (!this.full()) {
			newPlayer = new JugadorBJ(name, this.MONTOINICIAL);
			this.listaDeEspera.add(newPlayer);
			idAux = newPlayer.getID();
		}
		
		return idAux;
	}
	
	// Registra la apuesta del jugador con el id dado.s
	public void registrarApuesta(float monto, int idPlayer) {
		JugadorBJ playerAux = this.pickAPlayer(idPlayer);
		playerAux.apostar(monto);
		this.apuestas();
	}
	
	// Rutina para repartir a los jugadores.
	public void repartir() {
		JugadorBJ playerAux = null;
		boolean flagFound = false;
		
		for (JugadorBJ player : this.players) {
			if (!player.yaJugo() && !flagFound) {
				playerAux = player;
				flagFound = true;
			}
		}
		
		if (flagFound) {
			this.turnoDe(playerAux);
		}
		else {
			this.finalDeMano();
		}
	}

	// Rutina para buscar jugadores que todavía no apostaron.
	public void apuestas() {
		JugadorBJ playerAux = null;
		boolean flagFound = false;
		
		for (JugadorBJ player : this.players) {
			if (!player.yaAposto() && !flagFound) {
				playerAux = player;
				flagFound = true;
			}
		}

		if (flagFound) {
			this.notificar(new Data<IJugador>(Evento.SOLICITARAPUESTAS, playerAux, playerAux.getID()));
		}
		else {
			this.repartir();
		}	
	}
	
	// Rutina para terminar el turno del jugador con id 'idPlayer'.
	public void terminarTurnoA(int idPlayer) {
		JugadorBJ player = this.pickAPlayer(idPlayer);
		player.terminoTurno();
	}
	
	// Notifica a los observadores pasandole el objeto data.
	public void notificar(Data<IJugador> data) {
		// this.notificarObservadores(data);
		this.cc.update(data);
	}
	
	// Retorna true si esta lleno, false en caso contrario.

	// Método que se utiliza para iniciar el juego en caso de que sea el primer jugador en ingresar.
	public void tryToStart() {
		if (this.players.size() == 0) {
			this.start();
		}
	}
	
	// Dar a carta a player.
	public void darCarta(int idPlayer) {
		JugadorBJ playerAux = this.pickAPlayer(idPlayer);
		this.crupier.darCarta(playerAux);
	}
	
	// Provisorio.
	public void setCC(Controlador cc) {
		this.cc = cc;
	}
	
	// Rutina para iniciar la mano. Este start es peligroso, debería ser privado.
	private void start() {
		this.ingresarListaDeEspera();
		this.primeraMano();
		this.apuestas();
	}

	// Retorna true si se llenó la mesa.
	private boolean full() {
		int totalPlayers = this.players.size() + this.listaDeEspera.size();
		return (totalPlayers >= this.MAXPLAYERS);
	}
		
	// Se reparte la primera mano.

	// Reparte el inicio de la mano. NOMBRE A CAMBIAR.
	private void primeraMano() {
		for (Jugador player : this.players) {
			this.crupier.primeraMano(player);
		}
	}
	
	// Carga los jugadores de la lista de espera.
	
	// Carga los jugadores en lista de espera.
	private void ingresarListaDeEspera() {
		JugadorBJ playerAux = null;
		
		for(int i = 0; i < this.listaDeEspera.size(); i++) {
			playerAux = this.listaDeEspera.poll();
			playerAux.setID(this.cantPlayers);
			this.players.add(playerAux);
			this.cantPlayers++;
		}
	}
	
	// Setter del array de players.
	
	// Setter del arraylist 'players'.
	private void setPlayers() {
		this.players = new ArrayList<JugadorBJ>(this.MAXPLAYERS);
	}
	
	// Setter de la queue de lista de espera.
	
	// Setter de la queue 'listaDeEspera'.
	public void setListaDeEspera() {
		this.listaDeEspera = new LinkedList<JugadorBJ>();
	}

	// Rutina para que player juegue su mano.
	
	// Se fija la condición de la mano de 'player'.
	private void turnoDe(JugadorBJ player) {
		EstadoDeMano estado = this.crupier.getEstado(player);
		boolean primeraMano = (player.cantidadDeCartas() == 2);
		boolean flagTerminoTurno = false;
		
		if (primeraMano) {
			this.crupier.mostrar(player);
			
			if (estado == EstadoDeMano.BLACKJACK) {
				flagTerminoTurno = true;
			}
			else {
				this.notificar(new Data<IJugador>(Evento.PREGUNTARPORCARTA, player, player.getID()));
			}
		}
		else {
			switch (estado) {
			case MAYORA21:
				flagTerminoTurno = true;
				break;
			case MENORA21:
				this.notificar(new Data<IJugador>(Evento.PREGUNTARPORCARTA, player, player.getID()));
				break;
			case IGUALA21:
				flagTerminoTurno = true;
				break;
			default:
				break;
			}	
		}
		
		if (flagTerminoTurno) {
			player.terminoTurno();
			this.repartir();
		}
	} 

	// Determina las ganancias y reinicia la mano.
	
	// Rutina para finalizar la mano. NO ESTA TERMINADO.
	private void finalDeMano() {
		for (JugadorBJ player : this.players) {
			this.crupier.determinarGanancia(player);
			player.reset();
		}
		this.start();
	}

	// Retorna el jugador con la id 'idPlayer'.
	
	// Selecciona al jugador que se corresponda con el 'idPlayer'.
	private JugadorBJ pickAPlayer(int idPlayer) {
		JugadorBJ playerAux = null;
		
		for (JugadorBJ player : this.players) {
			if (player.getID() == idPlayer) {
				playerAux = player;
			}
		}
		
		return playerAux;
	}
}
