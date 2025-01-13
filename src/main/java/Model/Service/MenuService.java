package Model.Service;

import Model.Produkt;
import Model.Repository.MenuRepository;

import java.util.List;

public class MenuService {
	// Ewentualnie wstrzyknięcie repozytorium
	// jeśli chcesz pójść w czyste "serwis + repozytorium"
	//private final MenuRepository menuRepository;  // jeśli klasa MenuRepository byłaby nie-statyczna

	public MenuService() {
		// U nas klasa MenuRepository ma wszystko statyczne, więc nic nie wstrzykujemy
	}

	/**
	 * Dodaje nowy produkt do listy i zapisuje w pliku JSON.
	 */
	public void dodajProdukt(Produkt produkt) {
		List<Produkt> produkty = MenuRepository.findAll();
		produkty.add(produkt);
		MenuRepository.save();  // Zapisujemy w pliku JSON
	}

	/**
	 * Pobiera listę wszystkich produktów z repozytorium.
	 */
	public List<Produkt> getMenu() {
		return MenuRepository.findAll();
	}
}
