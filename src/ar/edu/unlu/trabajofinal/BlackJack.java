package ar.edu.unlu.trabajofinal;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BlackJack {
	final private int MAXPLAYERS = 4;
	final private int MONTOINICIAL = 1000;
	private int cantPlayers = 0;
	private Crupier crupier;
	private ArrayList<JugadorBJ> players;
	private Queue<JugadorBJ> listaDeEspera;

	public BlackJack() {
		this.crupier = new Crupier();
		this.setPlayers();
		this.setListaDeEspera();
	}

	// Rutina para iniciar la mano.
	public void start() {
		this.ingresarListaDeEspera();
		this.primeraMano();
		this.apuestas();
	}
	
	// Si no esta lleno, carga un nuevo jugador a la lista de espera.
	public int newPlayer(String name) {
		JugadorBJ newPlayer = null;
		if (!this.full()) {
			newPlayer = new JugadorBJ(name, this.MONTOINICIAL);
			this.listaDeEspera.add(newPlayer);
		}
		return newPlayer.getID();
	}
	
	// Registra la apuesta del jugador con el id dado.s
	public void registrarApuesta(float monto, int idPlayer) {
		JugadorBJ playerAux = this.pickAPlayer(idPlayer);
		playerAux.apostar(monto);
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
		System.out.println(data.info().getID());
	}
	
	// Retorna true si esta lleno, false en caso contrario.
	private boolean full() {
		int totalPlayers = this.players.size() + this.listaDeEspera.size();
		return (totalPlayers >= this.MAXPLAYERS);
	}
	
	// Se reparte la primera mano.
	private void primeraMano() {
		for (Jugador player : this.players) {
			this.crupier.primeraMano(player);
		}
	}
	
	// Carga los jugadores de la lista de espera.
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
	private void setPlayers() {
		this.players = new ArrayList<JugadorBJ>(this.MAXPLAYERS);
	}
	
	// Setter de la queue de lista de espera.
	public void setListaDeEspera() {
		this.listaDeEspera = new LinkedList<JugadorBJ>();
	}

	// Rutina para que player juegue su mano.
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
			this.repartir();
		}
	} 

	// Determina las ganancias y reinicia la mano.
	private void finalDeMano() {
		for (JugadorBJ player : this.players) {
			this.crupier.determinarGanancia(player);
			player.reset();
		}
		this.start();
	}

	// Retorna el jugador con la id 'idPlayer'.
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
