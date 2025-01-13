package Model;

public class SalaKinowa {
	private int _numer;
	private int[][] _miejsca;
	private int _liczbaMiejsc;

	// Domyślny konstruktor bezparametrowy dla GSON
	public SalaKinowa() {
	}

	public SalaKinowa(int numer, int[][] miejsca, int liczbaMiejsc) {
		this._numer = numer;
		this._miejsca = miejsca;
		this._liczbaMiejsc = liczbaMiejsc;
	}

	public int getNumer() {
		return _numer;
	}

	public void setNumer(int numer) {
		this._numer = numer;
	}

	public int[][] getMiejsca() {
		return _miejsca;
	}

	public void setMiejsca(int[][] miejsca) {
		this._miejsca = miejsca;
	}

	public int getLiczbaMiejsc() {
		return _liczbaMiejsc;
	}

	public void setLiczbaMiejsc(int liczbaMiejsc) {
		this._liczbaMiejsc = liczbaMiejsc;
	}

	@Override
	public String toString() {
		return "SalaKinowa{" +
				"numer=" + _numer +
				", liczbaMiejsc=" + _liczbaMiejsc +
				'}';
	}
}
