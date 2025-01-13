package Model;

public class Uzytkownik {
    private String login;
    private String haslo;
    private int stopien; // 1 - dostęp do sprzedaży, 2 - pełny dostęp

    public Uzytkownik(String login, String haslo, int stopien) {
        this.login = login;
        this.haslo = haslo;
        this.stopien = stopien;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public int getStopien() {
        return stopien;
    }
}
