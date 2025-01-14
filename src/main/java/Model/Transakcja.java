package Model;

import java.util.ArrayList;
import java.util.List;

public class Transakcja {
	private int id;
	private List<Bilet> bilety = new ArrayList<>();
	private List<Artykul> artykuly = new ArrayList<>();
	private float kwotaTransakcji = 0;

	public Transakcja() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Bilet> getBilety() {
		return bilety;
	}

	public void addBilet(Bilet bilet) {
		if (bilet != null) {
			bilety.add(bilet);
			kwotaTransakcji += bilet.getCenaBiletu();
		}
	}

	public List<Artykul> getArtykuly() {
		return artykuly;
	}

	public void addArtykul(Artykul artykul) {
		if (artykul != null) {
			artykuly.add(artykul);
			kwotaTransakcji += artykul.getCena();
		}
	}

	public float getKwotaTransakcji() {
		return kwotaTransakcji;
	}

	public void setKwotaTransakcji(float kwotaTransakcji) {
		this.kwotaTransakcji = kwotaTransakcji;
	}

	public void clear() {
		bilety.clear();
		artykuly.clear();
		kwotaTransakcji = 0;
	}
}
