package at.fhv.popya.application.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectionTO {
	private UserTO _user;
	private UserPreferencesTO _preferences;

	/**
	 * Default constructor.
	 */
	public ConnectionTO() {

	}

	public UserTO getUser() {
		return _user;
	}

	@XmlElement(name = "user")
	public void setUser(UserTO user) {
		_user = user;
	}

	public UserPreferencesTO getPreferences() {
		return _preferences;
	}

	@XmlElement(name = "preferences")
	public void setPreferences(UserPreferencesTO preferences) {
		_preferences = preferences;
	}

}
