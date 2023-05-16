package ar.edu.unlu.mov;

import ar.edu.unlu.trabajofinal.BlackJack;
import ar.edu.unlu.trabajofinal.Data;
import ar.edu.unlu.trabajofinal.IJugador;

public class Controlador {
	private int id = -1; // -1 representa el nulo, ya que los id's siempre son positivos.
	private BlackJack modelo;
	// private ArrayList<IVista> vistas;
	private IVista vistaPrincipal;

	public Controlador(BlackJack modelo) {
		this.setVistas();
		this.modelo = modelo;
		modelo.setCC(this);
	}

	// Ingresa al jugador, seteando su id.
	public void startGame() {
		String name = "";
		if (id < 0) {
			name = this.vistaPrincipal.formularioDeIngreso();
			this.id = this.modelo.newPlayer(name);
			this.modelo.tryToStart();
		}
	}

	public void apostar(float monto) {
		this.modelo.registrarApuesta(monto, this.id);
	}

	public void quieroOtraCarta() {
		// Esto esta nefasto.
		this.modelo.darCarta(this.id);
		this.modelo.repartir();
	}

	public void noQuieroMas() {
		this.modelo.terminarTurnoA(this.id);
		this.modelo.repartir();
	}

	public void update(Data<IJugador> data) {
		this.vistaPrincipal.mostrarMesa(this.modelo.infoDeMesa());

		if (this.id == data.remitente()) {
			switch(data.evento()) {
			case SOLICITARAPUESTAS:
				int monto = this.vistaPrincipal.ingresoDeApuesta();
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
			default:
				break;
			}
		}
	}

	// Esta en modo de test!
	private void setVistas() {
		this.vistaPrincipal = new VistaConsola();
	}
}