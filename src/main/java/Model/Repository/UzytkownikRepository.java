package Model.Repository;

import Model.Uzytkownik;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UzytkownikRepository {
	private static final String FILE_PATH = "uzytkownicy.json";
	private static List<Uzytkownik> users;

	static {
		try (FileReader reader = new FileReader(FILE_PATH)) {
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Uzytkownik>>() {}.getType();
			users = gson.fromJson(reader, listType);
		} catch (IOException e) {
			users = List.of(
					new Uzytkownik("admin", "admin", 2)
			);
			saveUsers();
		}
	}

	public static Uzytkownik findByLogin(String login) {
		return users.stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
	}

	public static void saveUsers() {
		try (FileWriter writer = new FileWriter(FILE_PATH)) {
			Gson gson = new Gson();
			gson.toJson(users, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
