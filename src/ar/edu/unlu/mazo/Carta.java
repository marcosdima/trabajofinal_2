package ar.edu.unlu.mazo;

public class Carta {

	private Palo paloDeCarta;
	private ContenidoDeCarta contenido;
	private boolean visible;

	public Carta(Palo palo, ContenidoDeCarta cartita) {
		this.setContenido(cartita);
		this.setPaloDeCarta(palo);
		this.setVisibilidad(false);
	}

	public Palo getPaloDeCarta() {
		return paloDeCarta;
	}

	private void setPaloDeCarta(Palo paloDeCarta) {
		this.paloDeCarta = paloDeCarta;
	}

	public int getValor() {
		return this.contenido.getValor();
	}

	public void setVisibilidad(boolean esVisible) {

		this.visible = esVisible;

	}

	public boolean esVisible() {
		return this.visible;
	}

	public String getLabel() {
		return this.contenido.getLabel();
	}

	public String getDesc() {
		/* Seteo la variable desc para que devuevla el formato:
		 * 'Label' de 'Palo'
		 * */
		String desc = this.getLabel() + " de " + this.paloDeCarta;
		return desc;
	}

	public ContenidoDeCarta getContenido() {
		return this.contenido;
	}

	public void setContenido(ContenidoDeCarta contenido) {
		this.contenido = contenido;
	}

	public String getIdentificador() {

		return (this.getContenido() + "_" + this.getPaloDeCarta());

	}

}
