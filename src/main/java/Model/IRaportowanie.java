package Model;

import Model.Raport;

public interface IRaportowanie {

	public Raport generujRaportDzienny(Object aDate_data);

	public Raport generujRaportMiesieczny(Object aInt_month, Object aInt_year);
}