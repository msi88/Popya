package at.fhv.popya.application.entity.impl;

import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import at.fhv.popya.application.entity.IUser;
import at.fhv.popya.application.profile.IMovingProfile;

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
	public Bitmap getImage() {
		return null;
	}

	@Override
	public Location getCurrentLocation() {
		return null;
	}

	@Override
	public IMovingProfile getProfile() {
		return null;
	}

}