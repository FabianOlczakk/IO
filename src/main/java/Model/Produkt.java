package Model;

public class Produkt {
	private int id; // Nazwa zmieniona na pasującą do JSON
	private String nazwa;
	private String rodzaj;
	private float cenaNormalna;

	public Produkt() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getRodzaj() {
		return rodzaj;
	}

	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}

	public float getCenaNormalna() {
		return cenaNormalna;
	}

	public void setCenaNormalna(float cenaNormalna) {
		this.cenaNormalna = cenaNormalna;
	}

	@Override
	public String toString() {
		return nazwa + " - " + cenaNormalna + " PLN";
	}
}
