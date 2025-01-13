package Model.Service;

import Model.Film;
import Model.Model.Seans;
import Model.Repository.RepertuarRepository;
import Model.Repository.FilmRepository;
import Model.SalaKinowa;

import java.util.List;

public class RepertuarService {
	// W UML-u możesz mieć wstrzyknięte 3 repozytoria
	private final RepertuarRepository repertuarRepository;
	private final FilmRepository filmRepository;
	// Zakładam, że SalaRepository też może się przydać:
	// private final SalaRepository salaRepository;

	public RepertuarService(RepertuarRepository repertuarRepository,
							FilmRepository filmRepository) {
		this.repertuarRepository = repertuarRepository;
		this.filmRepository = filmRepository;
	}

	/**
	 * Pobiera wszystkie seanse.
	 */
	public List<Seans> getSeanse() {
		// W Twoim kodzie RepertuarRepository ma metodę findByDate(String date).
		// Brakuje findAll() – można dopisać podobnie jak w FilmRepository.
		// Na szybko:
		throw new UnsupportedOperationException("Na razie brak metody findAll w RepertuarRepository.");
	}

	public void dodajSeans(Seans seans, Film film, SalaKinowa sala) {
		// W Twoim UML-u tak to mogło wyglądać.
		// Możesz tu np. dodać do listy w RepertuarRepository i zapisać w JSON.
		// RepertuarRepository.addSeans(seans) -> ewentualnie utworzyć taką metodę
		System.out.println("Dodawanie seansu – logika do zaimplementowania");
	}

	public void usunSeans(int seansId) {
		System.out.println("Usuwanie seansu o ID " + seansId + " – do zaimplementowania");
	}

	public void czyRepertuarIstnieje() {
		// Przykładowo: sprawdzamy, czy jest cokolwiek w pliku JSON
		System.out.println("Sprawdzanie, czy repertuar istnieje – do zaimplementowania");
	}

	public void dodajFilm(Film film) {
		filmRepository.save(film);
	}

	public void edytujFilm(int filmId, Film noweDane) {
		// Znajdź stary film w repozytorium, zaktualizuj wartości i update
		Film staryFilm = filmRepository.findById(filmId);
		if (staryFilm != null) {
			staryFilm.setTytul(noweDane.getTytul());
			staryFilm.setObsada(noweDane.getObsada());
			staryFilm.setCzasTrwania(noweDane.getCzasTrwania());
			staryFilm.setOpis(noweDane.getOpis());
			filmRepository.update(staryFilm);
		}
	}

	public void usunFilm(int filmId) {
		filmRepository.delete(filmId);
	}

	public void zapiszRepertuar() {
		// Jeżeli chcesz zapisać seanse – np. wywołanie RepertuarRepository.save()
		System.out.println("Zapis repertuaru – do zaimplementowania (np. do JSON).");
	}
}
