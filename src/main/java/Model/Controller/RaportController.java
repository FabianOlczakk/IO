package Model.Controller;

import Model.IRaportView;
import Model.IRaportowanie;
import Model.ISprzedaz;
import Model.Raport;

public class RaportController {
	private Object _raportService;
	public ISprzedaz _unnamed_ISprzedaz_;
	public IRaportowanie _unnamed_IRaportowanie_;
	public IRaportView _unnamed_IRaportView_;

	public RaportController() {
		throw new UnsupportedOperationException();
	}

	public Raport generujRaportDzienny(Object aLocalDate_data) {
		throw new UnsupportedOperationException();
	}

	public Raport generujRaportMiesieczny(Object aInt_rok, Object aInt_miesiac) {
		throw new UnsupportedOperationException();
	}

	public Raport generujRaportSpecjalny() {
		throw new UnsupportedOperationException();
	}
}