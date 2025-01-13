package Model;

import Model.SalaKinowa;

public interface IRepertuarView {

	public void wyswietlRepertuar();

	public void ustawDate(Object aLocalDate_data);

	public void wyswietlSeans(Object aInt_id);

	public void wyswietlBlad(Object aString_komunikat);

	public void wyswietlMiejsca(SalaKinowa aSala);

	public void wybierzMiejsce(Object aInt_r, Object aInt_p, Object aBoolean_res);

	public void wyswietlFilm(Object aFilm_Film);
}