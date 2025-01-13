package Model;

public class SalaKinowa {
	private int _numer;
	private int[][] _miejsca;
	private int _liczbaMiejsc;

	public SalaKinowa(int numer, int[][] miejsca, int liczbaMiejsc) {
		this._numer = numer;
		this._miejsca = miejsca;
		this._liczbaMiejsc = liczbaMiejsc;
	}

	@Override
	public String toString() {
		return "SalaKinowa{" +
				"numer=" + _numer +
				", liczbaMiejsc=" + _liczbaMiejsc +
				'}';
	}

	public Object getNumer() {
		return this._numer;
	}
}
