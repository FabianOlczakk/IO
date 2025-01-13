package Model.Controller;

import Model.IloginView;
import Model.Repository.UzytkownikRepository;
import Model.Uzytkownik;

public class UzytkownikController {
	private Object _uzytkownikService;
	protected Object _sesja;
	public IloginView _unnamed_IloginView_;

	public UzytkownikController() {
		throw new UnsupportedOperationException();
	}

	public static Uzytkownik zaloguj(String login, String haslo) {
		Uzytkownik user = UzytkownikRepository.findByLogin(login);
		if (user != null && user.getHaslo().equals(haslo)) {
			return user;
		}
		return null;
	}

	public void wyloguj() {
		throw new UnsupportedOperationException();
	}

	public Uzytkownik getZalogowanyUzytkownik() {
		throw new UnsupportedOperationException();
	}

	public boolean rejestruj(Object aStrin_login, Object aString_haslo) {
		throw new UnsupportedOperationException();
	}

	public boolean zresetujHaslo(Object aString_login) {
		throw new UnsupportedOperationException();
	}
}