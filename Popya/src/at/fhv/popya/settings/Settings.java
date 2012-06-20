package at.fhv.popya.settings;

import at.fhv.popya.application.model.User;
import at.fhv.popya.application.model.UserPreferences;

/**
 * 
 * The Settings application wide settings
 * 
 * 
 * @author Michael Sieber
 */
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
		return user;
	}

	public static void setUser(User user) {
		Settings.user = user;
	}
}
