package at.fhv.popya.application.transfer;

/**
 * The user transfer object which represents the current chat user.
 * 
 * @author Michael
 * @version 1.0
 */
public class UserTO {

	private final String _chatName;
	private final String _description;
	private final byte[] _picture;
	private final LocationTO _currentLocation;
	private final UserPreferencesTO _preferences;

	/**
	 * Create a new user.
	 * 
	 * @param chatName
	 *            The users chat name
	 * @param description
	 *            The users self description
	 * @param picture
	 *            The profile picture
	 * @param currentLocation
	 *            The current location of the user
	 */
	public UserTO(String chatName, String description, byte[] picture,
			LocationTO currentLocation, UserPreferencesTO preferences) {
		_chatName = chatName;
		_description = description;
		_picture = picture;
		_currentLocation = currentLocation;
		_preferences = preferences;
	}

	public String getChatName() {
		return _chatName;
	}

	public String getDescription() {
		return _description;
	}

	public byte[] getImage() {
		return _picture;
	}

	public String getProfile() {
		return null;
	}

	public LocationTO getCurrentLocation() {
		return _currentLocation;
	}

	public UserPreferencesTO getPreferences() {
		return _preferences;
	}
}