package ar.edu.unlu.mazo;

public class TesterMazo {

	public static void main(String[] args) {

		MazoDeNaipes m = new MazoDeNaipes();
		m.barajar();
		System.out.println(m.getBaraja().getCant());
		System.out.println("3 doritos después...");
		m.remove(new Carta(Palo.CORAZON, ContenidoDeCarta.OCHO));
		System.out.println(m.getBaraja().getCant());


		
	}

}
