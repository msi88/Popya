package at.fhv.popya.application.transfer;

import javax.xml.bind.annotation.XmlElement;
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

	@XmlElement(name = "_chatName")
	public void setChatName(String chatName) {
		_chatName = chatName;
	}

	public String getDescription() {
		return _description;
	}

	@XmlElement(name = "_description")
	public void setDescription(String description) {
		_description = description;
	}

	public byte[] getPicture() {
		return _picture;
	}

	@XmlElement(name = "_picture")
	public void setPicture(byte[] picture) {
		_picture = picture;
	}

	public LocationTO getCurrentLocation() {
		return _currentLocation;
	}

	@XmlElement(name = "_currentLocation")
	public void setCurrentLocation(LocationTO currentLocation) {
		_currentLocation = currentLocation;
	}

	public UserPreferencesTO getPreferences() {
		return _preferences;
	}

	@XmlElement(name = "_preferences")
	public void setPreferences(UserPreferencesTO preferences) {
		_preferences = preferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_chatName == null) ? 0 : _chatName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTO other = (UserTO) obj;
		if (_chatName == null) {
			if (other._chatName != null)
				return false;
		} else if (!_chatName.equals(other._chatName))
			return false;
		return true;
	}

}