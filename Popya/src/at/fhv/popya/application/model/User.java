package at.fhv.popya.application.model;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import at.fhv.popya.application.model.transfer.ITransferable;
import at.fhv.popya.application.transfer.LocationTO;
import at.fhv.popya.application.transfer.UserTO;

/**
 * The user entity which represents the current chat user.
 * 
 * @author Michael
 * @version 1.0
 */
public class User implements ITransferable<UserTO> {

	private final String _chatName;
	private final String _description;
	private final Bitmap _picture;
	private final UserPreferences _preferences;
	private LocationTO _currLocation;

	/**
	 * Create a new user.
	 * 
	 * @param chatName
	 *            The users chat name
	 * @param description
	 *            The users self description
	 * @param picture
	 *            The profile picture
	 * @param locationManager
	 *            The location manager which is used to get the current user
	 *            location
	 */
	public User(String chatName, String description, Bitmap picture,
			UserPreferences preferences) {
		_chatName = chatName;
		_description = description;
		_picture = picture;
		_preferences = preferences;
	}

	public String getChatName() {
		return _chatName;
	}

	public String getDescription() {
		return _description;
	}

	public Bitmap getPicture() {
		return _picture;
	}

	public UserPreferences get_preferences() {
		return _preferences;
	}

	public LocationTO getCurrentLocation() {
		return _currLocation;
	}

	public void setCurrentLocation(LocationTO currentLocation) {
		_currLocation = currentLocation;
	}

	@Override
	public UserTO getTransferObject() {
		// create byte array from bitmap
		byte[] img = null;
		if (getPicture() != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getPicture().compress(Bitmap.CompressFormat.PNG, 100, out);
			img = out.toByteArray();
		}

		return new UserTO(this.getChatName(), this.getDescription(), img,
				getCurrentLocation(), this.get_preferences()
						.getTransferObject());
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
		User other = (User) obj;
		if (_chatName == null) {
			if (other._chatName != null)
				return false;
		} else if (!_chatName.equals(other._chatName))
			return false;
		return true;
	}

}
