package Model.Repository;

import Model.SalaKinowa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SalaRepository {
	private static final String FILE_PATH = "sale.json";
	private static List<SalaKinowa> sale;

	static {
		// Blok statyczny – ładowanie sal z pliku JSON
		try (FileReader reader = new FileReader(FILE_PATH)) {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<SalaKinowa>>() {}.getType();
			sale = gson.fromJson(reader, listType);
			System.out.println("Załadowane sale: " + sale);
		} catch (IOException e) {
			e.printStackTrace();
			sale = new ArrayList<>();
		}
	}

	/**
	 * Zapis sal do pliku JSON.
	 */
	public static void save() {
		try (FileWriter writer = new FileWriter(FILE_PATH)) {
			Gson gson = new Gson();
			gson.toJson(sale, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pobiera wszystkie sale.
	 */
	public static List<SalaKinowa> findAll() {
		return sale;
	}

	/**
	 * Znajduje salę po numerze.
	 */
	public static SalaKinowa findByNumer(int numerSali) {
		return sale.stream()
				.filter(s -> s.getNumer() == numerSali)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Dodaje nową salę (np. w trakcie konfiguracji systemu).
	 */
	public static void addSala(SalaKinowa sala) {
		sale.add(sala);
		save();
	}

	/**
	 * Aktualizuje istniejącą salę (np. zmiana miejsc).
	 * W tym przykładowym kodzie wystarczy, że wywołamy save(),
	 * bo obiekt w "sale" jest już zaktualizowany w pamięci.
	 */
	public static void updateSala(SalaKinowa sala) {
		// Nic nie robimy (obiekt jest w liście),
		// wystarczy zapisać całą listę do pliku:
		save();
	}
}
