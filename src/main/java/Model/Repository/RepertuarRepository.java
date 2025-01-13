package Model.Repository;

import Model.Model.Seans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepertuarRepository {
	private static final String FILE_PATH = "repertuar.json";
	private static List<Seans> seanse;

	static {
		try (FileReader reader = new FileReader(FILE_PATH)) {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Seans>>() {}.getType();
			seanse = gson.fromJson(reader, listType);
			System.out.println("Załadowane seanse: " + seanse);
		} catch (IOException e) {
			e.printStackTrace();
			seanse = new ArrayList<>();
		}
	}

	public static List<Seans> findByDate(String date) {
		return seanse.stream()
				.filter(seans -> seans.getData() != null && seans.getData().equals(date))
				.collect(Collectors.toList());
	}

	public static void save() {
		try (FileWriter writer = new FileWriter(FILE_PATH)) {
			Gson gson = new Gson();
			gson.toJson(seanse, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
