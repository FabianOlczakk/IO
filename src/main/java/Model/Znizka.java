package Model;

/**
 * Klasa reprezentująca zniżki dostępne w systemie (np. dla uczniów, seniorów).
 */
public class Znizka {
	private int _id;
	private String _nazwa;
	private int _procent;
	private String _warunki;

	public Znizka() {
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return this._id;
	}

	public String getNazwa() {
		return this._nazwa;
	}

	public int getProcent() {
		return this._procent;
	}

	public String getWarunki() {
		return this._warunki;
	}

	public void setId(int aId) {
		this._id = aId;
	}

	public void setNazwa(String aNazwa) {
		this._nazwa = aNazwa;
	}

	public void setProcent(int aProcent) {
		this._procent = aProcent;
	}

	public void setWarunki(String aWarunki) {
		this._warunki = aWarunki;
	}
}