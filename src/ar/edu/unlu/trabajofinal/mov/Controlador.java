package ar.edu.unlu.trabajofinal.mov;

import java.rmi.RemoteException;
//import java.util.ArrayList;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.trabajofinal.Data;
import ar.edu.unlu.trabajofinal.Evento;
import ar.edu.unlu.trabajofinal.IJugador;
import ar.edu.unlu.trabajofinal.IModelo;

public class Controlador implements IControladorRemoto {
	private int id = -1; // -1 representa el nulo, ya que los id's siempre son positivos.
	private IModelo modelo;
	//private ArrayList<IVista> vistas;
	private IVista vistaPrincipal;
	private boolean flagInGame = false;
	
	public void menuPrincipal() {
		this.vistaPrincipal.menuPrincipal();
	}
	
	// Ingresa al jugador, seteando su id.
	public void startGame() {
		String name = "";
	
		name = this.vistaPrincipal.formularioDeIngreso();
		
		try {
			this.id = this.modelo.newPlayer(name);
			this.inGame();
			this.updateMesa();
		} catch (RemoteException e) {
			System.out.println("Error al ingresar jugador!");
			e.printStackTrace();
		}
		
		try {
			this.modelo.tryToStart();
		} catch (RemoteException e) {
			System.out.println("Error al intentar iniciar!");
			e.printStackTrace();
		}
	}

	public void apostar(String monto) {
		try {
			this.modelo.registrarApuesta(monto, this.id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void otraCarta(String text) {
		boolean decision = false;

		if (text == "1") {
			decision = true;
		}
		
		if (decision) {
			this.quieroOtraCarta();
		}
		else {
			this.noQuieroMas();
		}
	}

	public void preguntaSiSigoJugando(String text) {
		boolean decision = false;

		if (text == "1") {
			decision = true;
		}
		
		if (decision) {
			try {
				this.modelo.sigoJugando(this.id);
			} catch (RemoteException e) {
				System.out.println("Error al intentar seguir jugando!");
				e.printStackTrace();
			}
		} else {
			try {
				this.modelo.abandono(id);
			} catch (RemoteException e) {
				System.out.println("Error al eliminar al jugador!");
				e.printStackTrace();
			}
		}
	}
	
	// Toma lo que recibe de la vista y lo redirije.
	public void askSomething(String response, Evento evento) {
		switch(evento) {
		case REINICIODEMANO:
			this.preguntaSiSigoJugando(response);
			break;
		case PREGUNTARPORCARTA:
			this.otraCarta(response);
			break;
		default:
			break;
		}
	}
	
	public void addVista(IVista vista) {
		this.vistaPrincipal = vista;
	}

	public void sendMensaje(String contenido) {
		try {
			this.modelo.mensaje(contenido, this.id);
		} catch (RemoteException e) {
			System.out.println("Error al enviar mensaje!");
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getRank() {
		ArrayList<String> res = new ArrayList<String>();
		
		try {
			res = this.modelo.getRank();
		} catch (RemoteException e) {			
			res.add("Error en la coneccion!");
		}
		
		return res;
	}
	
	@Override
	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		if (arg1 instanceof String) {
			this.vistaPrincipal.mostrarMensaje((String) arg1);
		} else {
			@SuppressWarnings({"unchecked"})
			Data<IJugador> data = (Data<IJugador>) arg1;
			if (this.flagInGame) {
				this.update(data);
			}
		}
	}
	
	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T arg0) {
		this.modelo = (IModelo) arg0;
	}

	public void cargarPartida() {
		try {
			this.id = this.modelo.loadGame();
			this.inGame();
			this.modelo.tryToStart();
		} catch (RemoteException e) {
			System.out.println("Error al intentar cargar partida!");
		}
	}
	
	public void exit() {
		try {
			this.modelo.removerObservador(this);
		} catch (RemoteException e) {
			System.out.println("No se pudo remover al observador!");
		}
	}

	private void updateMesa() {
		try {
			this.vistaPrincipal.mostrarMesa(this.modelo.infoDeMesa());
		} catch (RemoteException e) {
			System.out.println("Error update mesa");
			e.printStackTrace();
		}
	} 
	
	private void quieroOtraCarta() {
		try {
			this.modelo.darCarta(this.id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void noQuieroMas() {
		try {
			this.modelo.terminarTurnoA(this.id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void update(Data<IJugador> data) {
		this.updateMesa();
		
		if (this.id == data.remitente()) {
			String txt = data.evento().getMensaje();

			switch(data.evento()) {
				case INICIODEMANO:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
			
				case SOLICITARAPUESTAS:
					this.vistaPrincipal.ingresoDeApuesta(txt);
					break;
					
				case APUESTANOVALIDA:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
					
				case APUESTAMINIMA:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
					
				case APUESTASINFONDOS:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
	
				case PREGUNTARPORCARTA:
					this.vistaPrincipal.siONo(txt, data.evento());;	
					break;
					
				case FINDEMANO:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
					
				case REINICIODEMANO:
					this.vistaPrincipal.siONo(txt, data.evento());
					break;
				
				case FINDEJUEGO:
					this.vistaPrincipal.mostrarMensaje(txt);
					this.offGame();
					this.menuPrincipal();
					break;
					
				case ESOYAM:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
					
				default:
					break;
			}
		}
	}
	
	private void offGame() {
		this.flagInGame = false;
	}
	
	private void inGame() {
		this.flagInGame = true;
	}
}