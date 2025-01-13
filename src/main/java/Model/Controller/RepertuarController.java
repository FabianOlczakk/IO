package Model.Controller;

import Model.Film;
import Model.IRepertuarView;
import Model.IZarzadzanieOferta;
import Model.Model.Seans;
import Model.Service.RepertuarService;

public class RepertuarController {
	private final RepertuarService repertuarService;
	private final IRepertuarView repertuarView;
	private final IZarzadzanieOferta zarzadzanieOferta;

	/**
	 * Konstruktor przyjmuje serwis do zarządzania repertuarem, widok (UI) i ewentualnie interfejs do dalszych operacji.
	 */
	public RepertuarController(RepertuarService repertuarService,
							   IRepertuarView repertuarView,
							   IZarzadzanieOferta zarzadzanieOferta) {
		this.repertuarService = repertuarService;
		this.repertuarView = repertuarView;
		this.zarzadzanieOferta = zarzadzanieOferta;
	}

	public void aktualizujRepertuar() {
		// Przykład wywołania z serwisu
		// np. sprawdzamy czy repertuar istnieje itp.
		repertuarService.czyRepertuarIstnieje();
		// Albo z interfejsu
		// zarzadzanieOferta.czyRepertuarIstnieje();
	}

	public void wybierzRepertuarDoEdycji() {
		// Obsługa logiki wybierania repertuaru
	}

	public void dodajFilm(Film film) {
		repertuarService.dodajFilm(film);
	}

	public void edytujFilm(int filmId, Film noweDane) {
		repertuarService.edytujFilm(filmId, noweDane);
	}

	public void usunFilm(int filmId) {
		repertuarService.usunFilm(filmId);
	}

	public void zapiszZmiany() {
		repertuarService.zapiszRepertuar();
	}

	public void wylogujAdministratora() {
		// W tym miejscu można np. wyrzucić do ekranu logowania
		System.out.println("Wylogowanie administratora – do zaimplementowania");
	}
}
