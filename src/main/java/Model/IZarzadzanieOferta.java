package Model;

import Model.Model.Seans;
import Model.Produkt;
import Model.Znizka;

import java.util.List;

public interface IZarzadzanieOferta {

	public List<Seans> pobierzSeanse();

	public Seans pobierzSeans(Object aInt_id);

	public void dodajSeans(Object aSeans_seans);

	public List<Produkt> pobierzMenu();

	public List<Znizka> pobierzZnizki();

	public void dodajFilm(Object aFilm_film);

	public void usunFilm(Object aInt_filmId);

	public void edytujFilm(Object aInt_filmId, Object aFilm_noweDane);

	public void czyRepertuarIstnieje();
}