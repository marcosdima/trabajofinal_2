package ar.edu.unlu.mazo;

public class TesterMazo {

	public static void main(String[] args) {

		MazoDeNaipes m = new MazoDeNaipes();
		System.out.println(m.agarrarCarta().getDesc());
		System.out.println(m.agarrarCarta().getDesc());
		System.out.println(m.getDescarte().getTope().getDesc());

	}

}
