package Model;

import Model.Model.Seans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Vector;

/**
 * Klasa zarządzająca listą seansów dostępnych w kinie.
 */
public class Repertuar {
	private Vector<Seans> _seanse;

	public Repertuar() {
		_seanse = new Vector<>();
	}

	public Vector<Seans> getSeanse() {
		return this._seanse;
	}

	public void setSeanse(Vector<Seans> aSeanse) {
		this._seanse = aSeanse;
	}

	public void dodajFilm(Seans seans) {
		if (seans != null) {
			_seanse.add(seans);
		}
	}

	public void usunFilm(int filmId) {
		_seanse.removeIf(seans -> seans.getId() == filmId);
	}

	public void edytujFilm(int filmId, Seans noweDane) {
		for (int i = 0; i < _seanse.size(); i++) {
			if (_seanse.get(i).getId() == filmId) {
				_seanse.set(i, noweDane);
				return;
			}
		}
	}

	// Zapis danych do pliku JSON
	public void zapiszDoPliku(String sciezka) throws IOException {
		Gson gson = new Gson();
		try (Writer writer = new FileWriter(sciezka)) {
			gson.toJson(_seanse, writer);
		}
	}

	// Odczyt danych z pliku JSON
	public void odczytajZPliku(String sciezka) throws IOException {
		Gson gson = new Gson();
		try (Reader reader = new FileReader(sciezka)) {
			Type listType = new TypeToken<Vector<Seans>>() {}.getType();
			_seanse = gson.fromJson(reader, listType);
			if (_seanse == null) {
				_seanse = new Vector<>();
			}
		}
	}
}
