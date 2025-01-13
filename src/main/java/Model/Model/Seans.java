package Model.Model;

import Model.Film;
import Model.SalaKinowa;

public class Seans {
	private int id; // Nazwa zmieniona na pasującą do JSON
	private Film film; // Obiekt film
	private String data;
	private String godzina;
	private String rodzaj;
	private float cenaNormalna;
	private String sciezkaDzw;
	private String napisy;
	private SalaKinowa salaKinowa;

	public Seans() {}

	// Gettery i settery
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getGodzina() {
		return godzina;
	}

	public void setGodzina(String godzina) {
		this.godzina = godzina;
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

	public String getSciezkaDzw() {
		return sciezkaDzw;
	}

	public void setSciezkaDzw(String sciezkaDzw) {
		this.sciezkaDzw = sciezkaDzw;
	}

	public String getNapisy() {
		return napisy;
	}

	public void setNapisy(String napisy) {
		this.napisy = napisy;
	}

	public SalaKinowa getSalaKinowa() {
		return salaKinowa;
	}

	public void setSalaKinowa(SalaKinowa salaKinowa) {
		this.salaKinowa = salaKinowa;
	}

	@Override
	public String toString() {
		return "Seans{" +
				"id=" + id +
				", data='" + data + '\'' +
				", godzina='" + godzina + '\'' +
				", film=" + (film != null ? film.getTytul() : "brak") +
				'}';
	}
}
