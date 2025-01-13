package Model.View;

import Model.ISprzedazView;
import Model.Transakcja;
import Model.Znizka;

import java.util.List;

public class SprzedazView implements ISprzedazView {
	private java.util.Vector<Transakcja> _transakcje;
	private Znizka _znizka;

	public SprzedazView() {
		throw new UnsupportedOperationException();
	}

	public void wyswietlZnizke(Object aInt_id) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlTransakcje(Object aInt_id) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlMenu() {
		throw new UnsupportedOperationException();
	}

	public void wyswietlProdukt(Object aInt_id) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlBlad(Object aString_komunikat) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlTransakcje(List<Transakcja> aTransakcje) {
		throw new UnsupportedOperationException();
	}

	public void pokazSzczegolyTansakcji(Object aInt_TransakcjaId) {
		throw new UnsupportedOperationException();
	}
}