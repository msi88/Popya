package at.fhv.popya.application.entity;

import java.io.BufferedInputStream;

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
	 * Get the users current longitude value.
	 * 
	 * @return The longitude value
	 */
	public String getCurrentLongitude();

	/**
	 * Get the users current altitude value.
	 * 
	 * @return The altitude value
	 */
	public String getCurrentAltitude();

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
	public BufferedInputStream getImage();

	/**
	 * Get the current moving profile of the user.
	 * 
	 * @return The users current moving profile
	 */
	public String getProfile();

}