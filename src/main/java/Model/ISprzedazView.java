package Model;

import Model.Transakcja;

import java.util.List;

public interface ISprzedazView {

	public void wyswietlTransakcje(List<Transakcja> aTransakcje);

	public void pokazSzczegolyTansakcji(Object aInt_TransakcjaId);

	public void wyswietlBlad(Object aString_komunikat);
}