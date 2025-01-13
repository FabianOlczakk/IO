package Model;

/**
 * Klasa przechowująca szczegółowe dane o filmie, takie jak tytuł, obsada, czas trwania, opis fabuły.
 */
public class Film {
	private int id;
	private String tytul;
	private String obsada;
	private int czasTrwania;
	private String opis;

	// Domyślny konstruktor wymagany przez Gson
	public Film() {}

	// Konstruktor z parametrami (opcjonalny)
	public Film(int id, String tytul, String obsada, int czasTrwania, String opis) {
		this.id = id;
		this.tytul = tytul;
		this.obsada = obsada;
		this.czasTrwania = czasTrwania;
		this.opis = opis;
	}

	// Gettery i settery
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getObsada() {
		return obsada;
	}

	public void setObsada(String obsada) {
		this.obsada = obsada;
	}

	public int getCzasTrwania() {
		return czasTrwania;
	}

	public void setCzasTrwania(int czasTrwania) {
		this.czasTrwania = czasTrwania;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "Film{" +
				"id=" + id +
				", tytul='" + tytul + '\'' +
				", obsada='" + obsada + '\'' +
				", czasTrwania=" + czasTrwania +
				", opis='" + opis + '\'' +
				'}';
	}
}
