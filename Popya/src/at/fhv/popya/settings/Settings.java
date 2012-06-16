package at.fhv.popya.settings;

import at.fhv.popya.application.model.User;
import at.fhv.popya.application.model.UserPreferences;

/* TODO:
 * Load settings from xml file or something.
 * Make settings updateable.
 * 
 * */

public class Settings {

	private static UserPreferences userPreferences;
	private static User user;

	public static UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public static void setUserPreferences(UserPreferences userPreferences) {
		Settings.userPreferences = userPreferences;
	}

	public static User getUser() {
		if (Settings.user == null)
			Settings.loadSettings();

		return user;
	}

	public static void setUser(User user) {

		Settings.user = user;
	}

	public static void loadSettings() {
		Settings.setUserPreferences(new UserPreferences(100, 100,
				"http://vps.luukwullink.nl:8080/PopyaWebserver/rest/popya/",
				1000));
		Settings.setUser(new User("Luuk88", "Random dutch guy", null, null,
				userPreferences));
	}

}
