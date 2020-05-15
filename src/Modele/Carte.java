package Modele;

public class Carte {
	
	//Enumeration for the colors of the card
	public enum colors {black, red};
	
	//Enumeration for the colors of the card
	public enum types {square, heart, spade, club};
	/* spade => maccabo noir...
	 * square => biscuit, faux carré...
	 * heart => coeur
	 * club => arachide*/

	//Attributes of the class
	private String numero;
	private colors color;
	private types type;
	
	//Constructor
	public Carte(String numero, colors color, types type) {
		this.numero = numero;
		this.color = color;
		this.type = type;
	}
	
	//Getters and setters
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public colors getColor() {
		return color;
	}
	public void setColor(colors color) {
		this.color = color;
	}
	public types getType() {
		return type;
	}
	public void setType(types type) {
		this.type = type;
	}
	
}
