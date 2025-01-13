package Model.Repository;

import Model.Produkt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
	private static final String FILE_PATH = "menu.json";
	private static List<Produkt> produkty;

	static {
		try (FileReader reader = new FileReader(FILE_PATH)) {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Produkt>>() {}.getType();
			produkty = gson.fromJson(reader, listType);
			System.out.println("Załadowane produkty: " + produkty);
		} catch (IOException e) {
			e.printStackTrace();
			produkty = new ArrayList<>();
		}
	}

	public static List<Produkt> findAll() {
		return produkty;
	}

	public static void save() {
		try (FileWriter writer = new FileWriter(FILE_PATH)) {
			Gson gson = new Gson();
			gson.toJson(produkty, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
