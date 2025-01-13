package Model.Service;

import Model.*;
import Model.Model.Seans;

import java.util.List;

public class ServiceFacade implements ISprzedaz {
	public MenuService _unnamed_MenuService_;
	public TransakcjaService _unnamed_TransakcjaService_;
	public RepertuarService _unnamed_RepertuarService_;
	public UzytkownikService _unnamed_UzytkownikService_;
	public ZnizkaService _unnamed_ZnizkaService_;
	public SprzedazService _unnamed_SprzedazService_;
	public RaportService _unnamed_RaportService_;

	public ServiceFacade() {
		throw new UnsupportedOperationException();
	}

	public List<Seans> pobierzSeanse() {
		throw new UnsupportedOperationException();
	}

	public Seans pobierzSeans(Object aInt_id) {
		throw new UnsupportedOperationException();
	}

	public void dodajSeans(Object aSeans_seans) {
		throw new UnsupportedOperationException();
	}

	public List<Produkt> pobierzMenu() {
		throw new UnsupportedOperationException();
	}

	public List<Znizka> pobierzZnizki() {
		throw new UnsupportedOperationException();
	}

	public void dodajFilm(Object aFilm_film) {
		throw new UnsupportedOperationException();
	}

	public void usunFilm(Object aInt_filmId) {
		throw new UnsupportedOperationException();
	}

	public void edytujFilm(Object aInt_filmId, Object aFilm_noweDane) {
		throw new UnsupportedOperationException();
	}

	public Transakcja rozpocznijTransakcje() {
		throw new UnsupportedOperationException();
	}

	public void zakonczTransakcje(Object aTransakcja_transakcja) {
		throw new UnsupportedOperationException();
	}

	public List<Transakcja> pobierzTransakcje() {
		throw new UnsupportedOperationException();
	}

	public Raport generujRaportDzienny(Object aDate_data) {
		throw new UnsupportedOperationException();
	}

	public Raport generujRaportMiesieczny(Object aInt_month, Object aInt_year) {
		throw new UnsupportedOperationException();
	}

	public void sprawdzDostepnosc() {
		throw new UnsupportedOperationException();
	}

	public void czyRepertuarIstnieje() {
		throw new UnsupportedOperationException();
	}
}