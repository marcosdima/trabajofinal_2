package ar.edu.unlu.mazo;

public enum Palo {

	CORAZON, PICA, TREBOL, DIAMANTE;

	static public Palo fromString(String palo) {
		Palo paloAux = null;
		palo = palo.toUpperCase();
		
		switch(palo) {
		case "CORAZON":
			paloAux = Palo.CORAZON;
			break;
		
		case "PICA":
			paloAux = Palo.PICA;
			break;
		
		case "TREBOL":
			paloAux = Palo.TREBOL;
			break;
		
		case "DIAMANTE":
			paloAux = Palo.DIAMANTE;
			break;
			
		default:
			break;
		}	
		
		return paloAux;
	}
}
