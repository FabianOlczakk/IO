package Model;

import java.util.Vector;

/**
 * Klasa przechowująca listę produktów barowych dostępnych w kinie.
 */
public class Menu {
	private java.util.Vector<Produkt> _produkty;

	public Menu() {
		throw new UnsupportedOperationException();
	}

	public Vector<Produkt> getProdukty() {
		return this._produkty;
	}

	public void setProdukty(Vector<Produkt> aProdukty) {
		this._produkty = aProdukty;
	}
}