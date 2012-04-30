package at.fhv.popya.application.entity.impl;

import java.io.BufferedInputStream;

import android.graphics.Bitmap;
import android.location.LocationManager;
import at.fhv.popya.application.entity.IUser;

/**
 * The user entity which represents the current chat user.
 * 
 * @author Michael
 * @version 1.0
 */
public class User implements IUser {

	private final String _chatName;
	private final String _description;
	private final Bitmap _picture;
	private final LocationManager _locationManager;

	/**
	 * Create a new user.
	 * 
	 * @param chatName The users chat name
	 * @param description The users self description
	 * @param picture The profile picture
	 * @param locationManager The location manager which is used to get the
	 *            current user location
	 */
	public User(String chatName, String description, Bitmap picture,
			LocationManager locationManager) {
		_chatName = chatName;
		_description = description;
		_picture = picture;
		_locationManager = locationManager;
	}

	@Override
	public String getChatName() {
		return "";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public BufferedInputStream getImage() {
		return null;
	}

	@Override
	public String getProfile() {
		return null;
	}

	@Override
	public String getCurrentLongitude() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentAltitude() {
		// TODO Auto-generated method stub
		return null;
	}

}