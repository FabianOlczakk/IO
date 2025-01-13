package Model;

import Model.Model.Seans;

/**
 * Klasa reprezentująca bilet zakupiony przez klienta.
 */
public class Bilet {
	private int _id;
	private Seans _seans;
	private String _typ;
	private float _cenaBiletu;
	private String _miejsca;

	public Bilet() {
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return this._id;
	}

	public Seans getSeans() {
		return this._seans;
	}

	public String getTyp() {
		return this._typ;
	}

	public float getCenaBiletu() {
		return this._cenaBiletu;
	}

	public String getMiejsca() {
		return this._miejsca;
	}

	public void setId(int aId) {
		this._id = aId;
	}

	public void setSeans(Seans aSeans) {
		this._seans = aSeans;
	}

	public void setTyp(String aTyp) {
		this._typ = aTyp;
	}

	public void setCenaBiletu(float aCenaBiletu) {
		this._cenaBiletu = aCenaBiletu;
	}

	public void setMiejsca(String aMiejsca) {
		this._miejsca = aMiejsca;
	}
}