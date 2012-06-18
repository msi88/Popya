package at.fhv.popya.settings;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import at.fhv.popya.PopyaSettingsActivity;
import at.fhv.popya.application.model.User;
import at.fhv.popya.application.model.UserPreferences;
import at.fhv.popya.application.view.SendMessageListener;

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
		return user;
	}

	public static void setUser(User user) {
		Settings.user = user;
	}
}
