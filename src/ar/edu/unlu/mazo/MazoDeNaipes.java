package ar.edu.unlu.mazo;

public class MazoDeNaipes extends Mazo {

	private static final long serialVersionUID = 1L;

	public MazoDeNaipes() {

		super(52);
		this.setCartas();

	}

	public void setCartas() {

		Palo[] palos = Palo.values();
		ContenidoDeCarta[] valores = ContenidoDeCarta.values();
		Carta card;

		for (Palo palo : palos) {

			for (ContenidoDeCarta i : valores) {

				card = new Carta(palo, i);
				this.addCarta(card);
			}

		}

	}

}
