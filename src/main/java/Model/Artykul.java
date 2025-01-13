package Model;

/**
 * Klasa reprezentująca pojedynczy produkt zakupiony w ramach transakcji (np. cola, popcorn).
 */
public class Artykul {
	private Produkt produkt; // Produkt przypisany do artykułu
	private int ilosc;       // Ilość produktów
	private float cena;      // Cena całkowita (produkt * ilość)

	// Konstruktor domyślny
	public Artykul() {
		this.produkt = null;
		this.ilosc = 0;
		this.cena = 0;
	}

	// Konstruktor z pełnymi parametrami
	public Artykul(Produkt produkt, int ilosc) {
		this.produkt = produkt;
		this.ilosc = ilosc;
		this.cena = produkt.getCenaNormalna() * ilosc; // Obliczenie całkowitej ceny
	}

	// Getter dla produktu
	public Produkt getProdukt() {
		return this.produkt;
	}

	// Setter dla produktu (przelicza cenę po zmianie produktu)
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
		this.cena = produkt.getCenaNormalna() * this.ilosc; // Aktualizacja ceny
	}

	// Getter dla ilości
	public int getIlosc() {
		return this.ilosc;
	}

	// Setter dla ilości (przelicza cenę po zmianie ilości)
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
		if (this.produkt != null) {
			this.cena = this.produkt.getCenaNormalna() * ilosc; // Aktualizacja ceny
		}
	}

	// Getter dla ceny
	public float getCena() {
		return this.cena;
	}

	// Setter dla ceny (nie zalecane, ale dodane dla zgodności)
	public void setCena(float cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return String.format("Produkt: %s, Ilość: %d, Cena: %.2f PLN",
				produkt != null ? produkt.getNazwa() : "Brak produktu",
				ilosc,
				cena);
	}
}
