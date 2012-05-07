package at.fhv.popya.application.model;

import java.io.BufferedInputStream;

import android.graphics.Bitmap;
import android.location.LocationManager;
import at.fhv.popya.application.transfer.LocationTO;

/**
 * The user entity which represents the current chat user.
 * 
 * @author Michael
 * @version 1.0
 */
public class User {

	private final String _chatName;
	private final String _description;
	private final Bitmap _picture;
	private final LocationManager _locationManager;

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
			LocationManager locationManager) {
		_chatName = chatName;
		_description = description;
		_picture = picture;
		_locationManager = locationManager;
	}

	public String getChatName() {
		return "";
	}

	public String getDescription() {
		return "";
	}

	public BufferedInputStream getImage() {
		return null;
	}

	public String getProfile() {
		return null;
	}

	public LocationTO getCurrentLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}