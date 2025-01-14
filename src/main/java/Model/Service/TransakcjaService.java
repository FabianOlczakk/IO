package Model.Service;

import Model.Transakcja;

public class TransakcjaService {
	private Transakcja aktualnaTransakcja = new Transakcja();

	public Transakcja getAktualnaTransakcja() {
		return aktualnaTransakcja;
	}

	public void dodajBiletDoTransakcji(Model.Bilet bilet) {
		aktualnaTransakcja.addBilet(bilet);
	}

	public void dodajArtykulDoTransakcji(Model.Artykul artykul) {
		aktualnaTransakcja.addArtykul(artykul);
	}

	public void wyczyscTransakcje() {
		aktualnaTransakcja.clear();
	}

	public void finalizujTransakcje() {
		// Tutaj można zaimplementować logikę zapisu transakcji do repozytorium
		aktualnaTransakcja.clear();
	}

	public void dodajZnizke(float wartosc) {
		aktualnaTransakcja.setKwotaTransakcji(aktualnaTransakcja.getKwotaTransakcji() - wartosc);
	}
}
