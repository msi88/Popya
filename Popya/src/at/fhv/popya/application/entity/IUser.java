package at.fhv.popya.application.entity;

import android.graphics.Bitmap;
import android.location.Location;
import at.fhv.popya.application.profile.IMovingProfile;

/**
 * Interface for the user class.
 * 
 * @author Michael
 * @version 1.0
 */
public interface IUser {

	/**
	 * Get the chat name of the user.
	 * 
	 * @return The chat name
	 */
	public String getChatName();

	/**
	 * Get the current user location.
	 * 
	 * @return The current user location
	 */
	public Location getCurrentLocation();

	/**
	 * Get the user description.
	 * 
	 * @return The self description of the user
	 */
	public String getDescription();

	/**
	 * The the profile picture of the user.
	 * 
	 * @return The users profile picture
	 */
	public Bitmap getImage();

	/**
	 * Get the current moving profile of the user.
	 * 
	 * @return The users current moving profile
	 */
	public IMovingProfile getProfile();

}