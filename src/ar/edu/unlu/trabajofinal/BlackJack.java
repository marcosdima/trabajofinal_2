package ar.edu.unlu.trabajofinal;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ar.edu.unlu.tools.Intencion;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class BlackJack extends ObservableRemoto implements IModelo {
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

	// Si no esta lleno, carga un nuevo jugador a la lista de espera.
	@Override
	public int newPlayer(String name) throws RemoteException {
		JugadorBJ newPlayer = null;
		int idAux = -1;

		if (!this.full()) {
			newPlayer = new JugadorBJ(name, this.MONTOINICIAL);
			this.listaDeEspera.add(newPlayer);
			newPlayer.setID(this.cantPlayers);
			this.cantPlayers++;
			idAux = newPlayer.getID();
		}

		return idAux;
	}

	// Registra la apuesta del jugador con el id dado.s
	@Override
	public void registrarApuesta(String monto, int idPlayer) throws RemoteException {
		try {
            float floatMonto = Float.parseFloat(monto);
            JugadorBJ playerAux = this.pickAPlayer(idPlayer);
    		playerAux.apostar(floatMonto);
    		
    		// Si el monto ingresado es 0, entonces saltea su turno.
    		if (floatMonto == 0) {
    			playerAux.terminoTurno();
    		}

        } catch (NumberFormatException e) {
        	this.command(monto, idPlayer);
        }

		this.apuestas();
	}

	// Rutina para terminar el turno del jugador con id 'idPlayer'.
	@Override
	public void terminarTurnoA(int idPlayer) throws RemoteException {
		JugadorBJ player = this.pickAPlayer(idPlayer);
		player.terminoTurno();
		this.repartir();
	}

	// Método que se utiliza para iniciar el juego en caso de que sea el primer jugador en ingresar.
	@Override
	public void tryToStart() throws RemoteException {
		if (this.players.size() == 0) {
			this.start();
		}
	}

	// Dar a carta a player.
	@Override
	public void darCarta(int idPlayer) throws RemoteException {
		boolean flagFound = false;
		JugadorBJ playerTurno = null;
		
		for (JugadorBJ player : this.players) {
			if (!player.yaJugo() && !flagFound) {
				playerTurno = player;
				flagFound = true;
			}
		}
		
		if (playerTurno.getID() == idPlayer) {
			this.crupier.darCarta(playerTurno, true);
			this.repartir();
		}

	}

	// Retorna la info de los jugadores de la mesa.
	@Override
	public ArrayList<IJugador> infoDeMesa() throws RemoteException {
		ArrayList<IJugador> datosDeJugadores = new ArrayList<>(this.players.size() + 1);

		for (JugadorBJ player : this.players) {
			datosDeJugadores.add(player);
		}

		// Por último, agrego al crupier para que quede en la última posición.
		datosDeJugadores.add(this.crupier);

		return datosDeJugadores;
	}

	// Notifica a los observadores pasandole el objeto data.
	public void notificar(Data<IJugador> data) {
		try {
			this.notificarObservadores(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Rutina para iniciar la mano. Este start es peligroso, debería ser privado.
	private void start() {
		this.ingresarListaDeEspera();
		this.crupier.reset();
		this.primeraMano();
		this.apuestas();
	}

	// Rutina para buscar jugadores que todavía no apostaron.
	private void apuestas() {
		JugadorBJ playerAux = this.pickAGambler();

		if (playerAux != null) {
			this.notificar(new Data<IJugador>(Evento.SOLICITARAPUESTAS, playerAux, playerAux.getID()));
		}
		else {
			this.repartir();
		}
	}
	
	// Rutina para repartir a los jugadores.
	private void repartir() {
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

	// Retorna true si se llenó la mesa.
	private boolean full() {
		int totalPlayers = this.players.size() + this.listaDeEspera.size();
		return (totalPlayers >= this.MAXPLAYERS);
	}

	// Reparte el inicio de la mano. NOMBRE A CAMBIAR.
	private void primeraMano() {
		for (Jugador player : this.players) {
			this.crupier.primeraMano(player);
		}
		this.crupier.primeraMano(this.crupier);
	}

	// Carga los jugadores de la lista de espera.
	private void ingresarListaDeEspera() {
		JugadorBJ playerAux = null;

		for(int i = 0; i < this.listaDeEspera.size(); i++) {
			playerAux = this.listaDeEspera.poll();
			this.players.add(playerAux);
		}
	}

	// Setter del array de players.
	private void setPlayers() {
		this.players = new ArrayList<>(this.MAXPLAYERS);
	}

	// Setter de la queue de lista de espera.
	private void setListaDeEspera() {
		this.listaDeEspera = new LinkedList<>();
	}

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
	private void finalDeMano() {

		this.crupier.repartirASiMismo();

		for (JugadorBJ player : this.players) {
			this.notificar(new Data<IJugador>(Evento.FINDEMANO, player, player.getID()));
			this.crupier.determinarGanancia(player);
			player.cobrar();
			player.reset();
		}

		this.crupier.reset();
		
		if ((this.players.size() > 0) || (this.listaDeEspera.size() > 0)) {
			this.start();
		}
		else {
			this.setListaDeEspera();
			this.setPlayers();
		}
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

	// Retorna el jugador que tiene que apostar.
	private JugadorBJ pickAGambler() {
		JugadorBJ playerAux = null;
		boolean flagFound = false;

		for (JugadorBJ player : this.players) {
			if (!player.yaAposto() && !flagFound) {
				playerAux = player;
				flagFound = true;
			}
		}
		
		return playerAux;
	}
	
	// Elimina al jugador. 
	private void eliminarPlayer(int idPlayer) {
		int contador = 0;
		int index = 0;

		for (JugadorBJ player : this.players) {
			if (player.getID() == idPlayer) {
				index = contador;
			}
			contador++;
		}

		this.players.remove(index);
		this.notificar(new Data<IJugador>(Evento.FINDEJUEGO, null, idPlayer));
	}
	
	// Ejecuta el comando ingresado.
	private void command(String input, int playerId) {
		JugadorBJ playerAux = this.pickAPlayer(playerId);
		Intencion mean = new Intencion();
		
		if (mean.out(input)) {
			playerAux.terminoTurno();
			this.eliminarPlayer(playerId);
		}
		else if (mean.esoyam(input)) {
			playerAux.giveDinero(1000);
		}

		this.apuestas();
	}	
}
