package ar.edu.unlu.trabajofinal;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IModelo extends IObservableRemoto {

	// Si no esta lleno, carga un nuevo jugador a la lista de espera.
	int newPlayer(String name) throws RemoteException;

	// Registra la apuesta del jugador con el id dado.s
	void registrarApuesta(String monto, int idPlayer) throws RemoteException;

	// Rutina para terminar el turno del jugador con id 'idPlayer'.
	void terminarTurnoA(int idPlayer) throws RemoteException;

	// Método que se utiliza para iniciar el juego en caso de que sea el primer jugador en ingresar.
	void tryToStart() throws RemoteException;

	// Dar a carta a player.
	void darCarta(int idPlayer) throws RemoteException;

	// Retorna la info de los jugadores de la mesa.
	ArrayList<IJugador> infoDeMesa() throws RemoteException;
	
	public void mensaje(String contenido, int idPlayer) throws RemoteException;
	
	public void sigoJugando(int playerId) throws RemoteException;
	
	// Elimina al jugador. 
	public void eliminarPlayer(int idPlayer) throws RemoteException;
}