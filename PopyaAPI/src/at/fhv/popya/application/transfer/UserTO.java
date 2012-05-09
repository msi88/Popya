package at.fhv.popya.application.transfer;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The user transfer object which represents the current chat user.
 * 
 * @author Michael
 * @version 1.0
 */
@XmlRootElement
public class UserTO {

	private String _chatName;
	private String _description;
	private byte[] _picture;
	private LocationTO _currentLocation;
	private UserPreferencesTO _preferences;

	/**
	 * Default constructor.
	 */
	public UserTO() {
		this(null, null, null, null, null);
	}

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

	public void setChatName(String chatName) {
		_chatName = chatName;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public byte[] getPicture() {
		return _picture;
	}

	public void setPicture(byte[] picture) {
		_picture = picture;
	}

	public LocationTO getCurrentLocation() {
		return _currentLocation;
	}

	public void setCurrentLocation(LocationTO currentLocation) {
		_currentLocation = currentLocation;
	}

	public UserPreferencesTO getPreferences() {
		return _preferences;
	}

	public void setPreferences(UserPreferencesTO preferences) {
		_preferences = preferences;
	}

}