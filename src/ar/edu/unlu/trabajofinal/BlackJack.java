package ar.edu.unlu.trabajofinal;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ar.edu.unlu.tools.Intencion;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class BlackJack extends ObservableRemoto implements IModelo {
	final private int MAXPLAYERS = 4;
	final private int MONTOINICIAL = 1000;
	final private int APUESTAMINIMA = 100;
	private int cantPlayers = 0;
	private Crupier crupier;
	private ArrayList<JugadorBJ> players;
	private Queue<JugadorBJ> listaDeEspera;
	private FileManager fileManager; 

	public BlackJack() {
		this.crupier = new Crupier();
		this.setPlayers();
		this.setListaDeEspera();
		this.fileManager = new FileManager();
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
            
    		// Si el monto ingresado es 0, entonces saltea su turno.
    		if (floatMonto == 0) {
    			playerAux.terminoTurno();
    		}
    		// Si el monto es menor a la apuesta mínima, se le notifica.
    		else if (floatMonto < this.APUESTAMINIMA) {
    			this.notificar(new Data<IJugador>(Evento.APUESTAMINIMA, playerAux, playerAux.getID()));
    		}
    		// Si el jugador no tiene fondos.
    		else if (floatMonto > playerAux.getDinero()) {
    			this.notificar(new Data<IJugador>(Evento.APUESTASINFONDOS, playerAux, playerAux.getID()));
    		}
    		
    		else {
    			playerAux.apostar(floatMonto);
    		}
    		
    		this.apuestas();
        } catch (NumberFormatException e) {
        	this.command(monto, idPlayer);
        }
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

	// Notifica a los observadores pasandole el objeto data (IJugador).
	public void notificar(Data<IJugador> data) throws RemoteException {
		this.notificarObservadores(data);
	}
		
	// Envía un msj a todos los jugadores.
	public void mensaje(String contenido, int playerId) throws RemoteException {
		String mensajeFormateado = "";
		
		JugadorBJ sender = this.pickAPlayer(playerId);
		mensajeFormateado = sender.getNombre() + ": " + contenido;

		this.notificarObservadores(mensajeFormateado);
	}
	
	// El jugador sigue jugando.
	public void sigoJugando(int playerId) throws RemoteException {
		this.pickAPlayer(playerId).sigue();
		this.preguntarSiSeJuega();
	}
	
	// Jugador abandona.
	public void abandono(int idPlayer) throws RemoteException {
		this.eliminarPlayer(idPlayer);
		this.preguntarSiSeJuega();
	}
	
	// Elimina al jugador. 
	private void eliminarPlayer(int idPlayer) throws RemoteException {
		int contador = 0;
		int index = 0;

		for (JugadorBJ player : this.players) {
			if (player.getID() == idPlayer) {
				index = contador;
				this.saveRank(player);
			}
			contador++;
		}

		this.players.remove(index);
		this.notificar(new Data<IJugador>(Evento.FINDEJUEGO, null, idPlayer));
	}
	
	// Devuelve el ranking.
	public ArrayList<String> getRank() throws RemoteException {
		return this.loadRank();
	}
	
	// Rutina para iniciar la mano. Este start es peligroso, debería ser privado.
	private void start() throws RemoteException {
		this.ingresarListaDeEspera();
		this.crupier.reset();
		this.primeraMano();
		
		for (JugadorBJ player : this.players) {
			this.notificar(new Data<IJugador>(Evento.INICIODEMANO, player, player.getID()));
		}
		
		this.apuestas();
	}

	// Rutina para buscar jugadores que todavía no apostaron.
	private void apuestas() throws RemoteException {
		JugadorBJ playerAux = this.pickAGambler();

		if (playerAux != null) {
			this.notificar(new Data<IJugador>(Evento.SOLICITARAPUESTAS, playerAux, playerAux.getID()));
		}
		else {
			this.repartir();
		}
	}
	
	// Rutina para repartir a los jugadores.
	private void repartir() throws RemoteException {
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
	private void turnoDe(JugadorBJ player) throws RemoteException {
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
		else if (player.getCartas().size() == 5) {
			flagTerminoTurno = true;
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
	private void finalDeMano() throws RemoteException {
		ArrayList<Integer> eliminated = new ArrayList<>();
		
		this.crupier.repartirASiMismo();

		// Reparte las ganancias y prepara a los jugadores para la siguiente mano.
		for (JugadorBJ player : this.players) {
			this.notificar(new Data<IJugador>(Evento.FINDEMANO, player, player.getID()));
			this.crupier.determinarGanancia(player);
			player.cobrar();
			
			if (player.getDinero() <= 0) {
				eliminated.add(player.getID());
			}
		}
		
		// Elimina a quien haya perdido.
		for (int index : eliminated) {
			this.eliminarPlayer(index);
		}
		
		if (this.players.size() > 0) {
			this.preguntarSiSeJuega();	
		}
	}
	
	// Busca al primer jugador que todavía no se le pregunto por si sigue jugando.
	private void preguntarSiSeJuega() throws RemoteException {
		boolean flagFound = false;
		JugadorBJ playerAux = null;
		
		for (JugadorBJ player : this.players) {
			if (!player.sigueJugando() && !flagFound) {
				playerAux = player;
				flagFound = true;
			}
		}
		
		if (flagFound) {
			this.notificar(new Data<IJugador>(Evento.REINICIODEMANO, playerAux, playerAux.getID()));
		} else {
			this.restartMano();
		}
		
	}

	// Rutina para reiniciar la mano.
	private void restartMano() throws RemoteException {
		// Si hay jugadores.
		if ((this.players.size() > 0) || (this.listaDeEspera.size() > 0)) {
			
			// Reseteo los estados de los jugadores.
			for (JugadorBJ player : this.players) {
				player.reset();
			}
			
			this.start();
		}
		// Caso contrario resetea todo.
		else {
			this.crupier.reset();
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
	
	// Ejecuta el comando ingresado.
	private void command(String input, int playerId) throws RemoteException {
		// Recibe los comandos del input de las apuestas.
		JugadorBJ playerAux = this.pickAPlayer(playerId);
		Intencion mean = new Intencion();
		
		if (mean.out(input)) {
			playerAux.terminoTurno();
			this.eliminarPlayer(playerId);
		}
		else if (mean.esoyam(input)) {
			this.notificar(new Data<IJugador>(Evento.ESOYAM, playerAux, playerAux.getID()));
			playerAux.giveDinero(1000);
		}
		else {
			this.notificar(new Data<IJugador>(Evento.APUESTANOVALIDA, playerAux, playerAux.getID()));
		}

		this.apuestas();
	}	

	// Guarda el puntaje de player en el ranking.
	private void saveRank(JugadorBJ player) {
		try {
			this.fileManager.addToRanking(player.getNombre(), player.getDinero(), this.MONTOINICIAL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Retorna el ranking.
	private  ArrayList<String> loadRank() {
		ArrayList<String> res = null;
		try {
			res = this.fileManager.loadRanking();
		} catch (IOException e) {
			res = new ArrayList<String>();
			res.add("Error al intentar cargar!");
		}
		return res;
	}
}
