package Model.View;

import Model.IRepertuarView;
import Model.Film;
import Model.Repertuar;
import Model.Model.Seans;
import Model.SalaKinowa;

import java.time.LocalDate;
import java.util.List;

public class RepertuarView implements IRepertuarView {
	private Repertuar repertuar;
	private LocalDate _wybranaData;
	private Film _film;
	private Seans _seans;
	private SalaKinowa _salaKinowa;

	public RepertuarView(Repertuar repertuar) {
		this.repertuar = repertuar;
	}

	public void wyswietlRepertuar() {
		if (repertuar == null) {
			System.out.println("Brak repertuaru.");
			return;
		}

		// Przypisanie wyników metody getSeanse() do zmiennej lokalnej
		List<Seans> seanse = repertuar.getSeanse();
		if (seanse == null || seanse.isEmpty()) {
			System.out.println("Brak dostepnych seansow w repertuarze.");
		} else {
			System.out.println("Repertuar:");
			for (Seans seans : seanse) {
				// Obsługa nieprawidłowych danych dla seansu
				if (seans == null || seans.toString() == null || seans.toString().isEmpty()) {
					System.out.println("Brak poprawnych danych dla seansu.");
				} else {
					System.out.println(seans);
				}
			}
		}
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