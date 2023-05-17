package ar.edu.unlu.trabajofinal.mov;

import java.rmi.RemoteException;
//import java.util.ArrayList;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.trabajofinal.Data;
import ar.edu.unlu.trabajofinal.IJugador;
import ar.edu.unlu.trabajofinal.IModelo;

public class Controlador implements IControladorRemoto {
	private int id = -1; // -1 representa el nulo, ya que los id's siempre son positivos.
	private IModelo modelo;
	//private ArrayList<IVista> vistas;
	private IVista vistaPrincipal;
	
	public void menuPrincipal() {
		this.vistaPrincipal.menuPrincipal();
	}
	
	// Ingresa al jugador, seteando su id.
	public void startGame() {
		String name = "";
	
		name = this.vistaPrincipal.formularioDeIngreso();
		
		try {
			this.id = this.modelo.newPlayer(name);
			System.out.println(this.id);
			this.modelo.tryToStart();
		} catch (RemoteException e) {
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

	public void quieroOtraCarta() {
		try {
			this.modelo.darCarta(this.id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void noQuieroMas() {
		try {
			this.modelo.terminarTurnoA(this.id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void update(Data<IJugador> data) throws RemoteException {
		this.vistaPrincipal.mostrarMesa(this.modelo.infoDeMesa());
		System.out.println(data.evento());
		System.out.println(data.remitente());
		System.out.println(this.id);
		if (this.id == data.remitente()) {
			switch(data.evento()) {
			
			case SOLICITARAPUESTAS:
				
				String monto = this.vistaPrincipal.ingresoDeApuesta();
				this.modelo.registrarApuesta(monto, data.remitente());
				break;

			case PREGUNTARPORCARTA:
				boolean flag = this.vistaPrincipal.preguntaQuieroOtra();

				if (flag) {
					this.quieroOtraCarta();
				}
				else {
					this.noQuieroMas();
				}

				break;
				
			case FINDEJUEGO:
				this.menuPrincipal();
				break;
			default:
				break;
			}
		}
	}

	public void addVista(IVista vista) {
		this.vistaPrincipal = vista;
	}
	
	@Override
	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		@SuppressWarnings({"unchecked"})
		Data<IJugador> data = (Data<IJugador>) arg1;
		this.update(data);
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T arg0) {
		this.modelo = (IModelo) arg0;
	}
}