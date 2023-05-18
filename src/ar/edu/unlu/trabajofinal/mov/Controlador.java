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

		if (this.id == data.remitente()) {
			String txt = data.evento().getMensaje();
			
			switch(data.evento()) {
				case SOLICITARAPUESTAS:
					
					String monto = this.vistaPrincipal.ingresoDeApuesta(txt);
					this.modelo.registrarApuesta(monto, data.remitente());
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
					boolean flag = this.vistaPrincipal.preguntaQuieroOtra(txt);
	
					if (flag) {
						this.quieroOtraCarta();
					}
					else {
						this.noQuieroMas();
					}
	
					break;
					
				case FINDEMANO:
					this.vistaPrincipal.mostrarMensaje(txt);
					break;
					
				
					
				case FINDEJUEGO:
					this.vistaPrincipal.mostrarMensaje(txt);
					this.menuPrincipal();
					break;
					
				case ESOYAM:
					this.vistaPrincipal.mostrarMensaje(txt);
					
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