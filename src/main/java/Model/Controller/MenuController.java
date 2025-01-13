package Model.Controller;

import Model.IZarzadzanieOferta;
import Model.Produkt;
import Model.Service.MenuService;

import java.util.List;

public class MenuController {
	private final MenuService menuService;
	private final IZarzadzanieOferta zarzadzanieOferta;

	// Konstruktor z wstrzyknięciem zależności
	public MenuController(MenuService menuService, IZarzadzanieOferta zarzadzanieOferta) {
		this.menuService = menuService;
		this.zarzadzanieOferta = zarzadzanieOferta;
	}

	// Dodaje nowy produkt do menu
	public void dodajProdukt(Produkt produkt) {
		// Wywołanie metody w warstwie serwisu
		menuService.dodajProdukt(produkt);
	}

	// Usuwa produkt o danym ID
	public void usunProdukt(int produktId) {
		// Możesz dorobić w menuService metodę usunProdukt(produktId)
		System.out.println("Usunięto produkt o ID = " + produktId + " (do zaimplementowania)");
		// Przykład:
		// menuService.usunProdukt(produktId);
	}

	// Pobiera listę wszystkich produktów
	public List<Produkt> getMenu() {
		return menuService.getMenu();
	}
}
