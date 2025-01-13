package Model;

import Model.Transakcja;

import java.util.List;

public interface ISprzedaz {

	public Transakcja rozpocznijTransakcje();

	public void zakonczTransakcje(Object aTransakcja_transakcja);

	public List<Transakcja> pobierzTransakcje();
}