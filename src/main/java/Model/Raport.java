package Model;

/**
 * Klasa odpowiedzialna za generowanie i przechowywanie danych raportowych (np. raport sprzedaży).
 */
public class Raport {
	private int _id;
	private String _typRaportu;
	private String _dane;
	private java.util.Vector<Transakcja> _transakcje;

	public Raport() {
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return this._id;
	}

	public String getTypRaportu() {
		return this._typRaportu;
	}

	public String getDane() {
		return this._dane;
	}

	public void setId(int aId) {
		this._id = aId;
	}

	public void setTypRaportu(String aTypRaportu) {
		this._typRaportu = aTypRaportu;
	}

	public void setDane(String aDane) {
		this._dane = aDane;
	}
}