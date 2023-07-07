package ar.edu.unlu.trabajofinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ar.edu.unlu.mazo.ContenidoDeCarta;
import ar.edu.unlu.mazo.Palo;
import ar.edu.unlu.mazo.Carta;
import ar.edu.unlu.mazo.ConjuntoDeCartas;


public class PartidaGuardada {
	
	private BlackJack modelo;
	private FileManager files;
	private ArrayList<JugadorBJ> jugadores;
	private String cartasDelCrupier;
	private ConjuntoDeCartas descarte = new ConjuntoDeCartas();
	private int contPlayers = 0;
	
	public PartidaGuardada(BlackJack modelo, FileManager fileManager) {
		this.modelo = modelo;
		this.files = fileManager;
		this.setPlayers();
	}

	public void setPlayer(int id) {
		if (this.contPlayers < this.jugadores.size()) {
			// Seteo el id correspondiente.
			this.jugadores.get(this.contPlayers).setID(id);
			
			// Lo agrego a la lista de espera.
			this.modelo.addToListaDeEspera(this.jugadores.get(this.contPlayers));
			
			this.contPlayers++;
		}
	}

	public ConjuntoDeCartas getCartasDelCrupier() {
		return this.parseCards(this.cartasDelCrupier);
	}
	
	public boolean full() {
		return ((this.jugadores.size()) == this.contPlayers);
	}
	
	public ConjuntoDeCartas getDescarte() {
		return this.descarte;
	}
	
	private void setPlayers() {
		JugadorBJ playerAux = null;
		String[] lineaParsed = null;
		this.jugadores = new ArrayList<JugadorBJ>();
		
		// Aca intente utilizar un mapa para los valores que le corresponden a cada dato guardado.
		Map<String, Integer> values = new HashMap<String, Integer>();
		values.put("nombre", 0);
		values.put("dinero", 1);
		values.put("aposto?", 2);
		values.put("apuesta", 3);
		values.put("yaJugo?", 4);
		values.put("mano", 5);
		
		try {
			ArrayList<String> carga = this.files.carga();
			
			// Recorre hasta el anteúltimo elemento, el último es el crupier.
			for (int i = 0; i < carga.size() -1; i++) {
				// linea -> jugador1,dinero,aposto?,monto_apostado,[mano]
				// linea.split -> 1. Nombre de jugador; 2. Dinero del jugador; 3. Flag de aposto; 4. Monto apostado.; 5. Flag de ya jugó; 6. Mano.
				
				lineaParsed = carga.get(i).split(",");

				// Inicializa el jugador.
				playerAux = new JugadorBJ(	lineaParsed[values.get("nombre")],
											Float.valueOf(lineaParsed[values.get("dinero")]) + Float.valueOf(lineaParsed[values.get("apuesta")]));
				
				// Si aposto, setea la apuesta.
				if (Boolean.valueOf(lineaParsed[values.get("aposto?")])) {
					playerAux.apostar(Integer.valueOf(lineaParsed[values.get("apuesta")]));
				}
				
				// Si ya jugó, setea el flag.
				if (Boolean.valueOf(lineaParsed[values.get("yaJugo?")])) {
					playerAux.terminoTurno();
				}
				
				// Setea la mano.
				String mano = lineaParsed[values.get("mano")];
				playerAux.setMano(this.parseCards(mano));

				this.jugadores.add(playerAux);
			}
			
			// Set de crupier
			// 0. cartas (Dejo esto por si despues se me ocurre agarrar algo)
			lineaParsed = carga.get(carga.size() - 1).split(",");
			this.cartasDelCrupier = lineaParsed[0];
			
		} catch (IOException e) {
			System.out.println("Error al intentar cargar la partidas!");
			e.printStackTrace();
		}
	}
	
	private ConjuntoDeCartas parseCards(String cards) {
		// Formato de 'cards'
		// [AS_PICA|DOS_CORAZON]
		
		ConjuntoDeCartas res = new ConjuntoDeCartas();
		String[] lineaParsed = null;
		String[] cartas = null;
		Carta cartaAux;
		
		// Saco los corchetes.
		cards = cards.substring(1, cards.length() - 1);
		
		cartas = cards.split("@");
		
		for (String carta : cartas) {
			// 0. Palo; 1. Valor. 
			lineaParsed = carta.split("_");
			Palo palo = Palo.fromString(lineaParsed[1]);
			ContenidoDeCarta contenido = ContenidoDeCarta.fromString(lineaParsed[0]);
			boolean seVe = Boolean.valueOf(lineaParsed[2]);
			
			cartaAux = new Carta(palo, contenido);
			cartaAux.setVisibilidad(seVe);

			res.addCarta(cartaAux);
			this.addToDescarte(cartaAux);
		}
		
		return res;
	}

	private void addToDescarte(Carta carta) {
		this.descarte.addCarta(carta);
	}
}
