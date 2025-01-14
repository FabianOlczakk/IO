package Model.View;

import Model.IRaportView;
import Model.Raport;

public class RaportView implements IRaportView {
	private Raport _raport;

	// Nowy konstruktor przyjmujący Raport
	public RaportView(Raport raport) {
		this._raport = raport;
	}

	// Metoda wyświetlająca dane raportu
	@Override
	public void wyswietlRaport(Object aRaport_raport) {
		// Dla bezpieczeństwa sprawdzamy, czy przekazany obiekt nie jest nullem
		// i czy faktycznie jest klasy Raport
		if (aRaport_raport == null) {
			System.err.println("Błąd: Raport jest nullem!");
			return;
		}
		if (!(aRaport_raport instanceof Raport)) {
			System.err.println("Błąd: Niewłaściwy typ obiektu, oczekiwano Raport!");
			return;
		}

		Raport raport = (Raport) aRaport_raport;
		System.out.println("==== Wyswietlanie raportu ====");
		System.out.println("Raport ID: " + raport.getId());
		System.out.println("Typ raportu: " + raport.getTypRaportu());
		System.out.println("Dane raportu: " + raport.getDane());
		System.out.println("==== Koniec raportu ====");
	}

	// Metoda wyświetlająca błąd
	@Override
	public void wyswietlBlad(Object aString_komunikat) {
		System.err.println("Błąd: " + aString_komunikat);
	}
}
