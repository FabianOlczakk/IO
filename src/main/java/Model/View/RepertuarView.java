package Model.View;

import Model.*;
import Model.Model.Seans;

import java.time.LocalDate;

public class RepertuarView implements IRepertuarView {
	private Repertuar _repertuar;
	private LocalDate _wybranaData;
	private Film _film;
	private Seans _seans;
	private SalaKinowa _salaKinowa;

	public RepertuarView() {
		throw new UnsupportedOperationException();
	}

	public void wyswietlRepertuar() {
		throw new UnsupportedOperationException();
	}

	public void ustawDate(Object aLocalDate_data) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlSeans(Object aInt_id) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlBlad(Object aString_komunikat) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlMiejsca(SalaKinowa aSala) {
		throw new UnsupportedOperationException();
	}

	public void wybierzMiejsce(Object aInt_r, Object aInt_p, Object aBoolean_res) {
		throw new UnsupportedOperationException();
	}

	public void wyswietlFilm(Object aFilm_Film) {
		throw new UnsupportedOperationException();
	}
}