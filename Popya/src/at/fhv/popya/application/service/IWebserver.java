package at.fhv.popya.application.service;

import java.util.List;

import android.location.Location;
import at.fhv.popya.application.entity.IUser;
import at.fhv.popya.application.entity.impl.Message;
import at.fhv.popya.application.entity.impl.UserPreferences;

/**
 * Interface for webserver interaction.
 * 
 * @author Michael
 * @version 1.0
 */
public interface IWebserver {

	/**
	 * Connect to the server.
	 * 
	 * @param preferences The user preferences
	 * @param user The user
	 * @return A list of all available chat partners
	 */
	public List<IUser> connect(UserPreferences preferences, IUser user);

	/**
	 * Get all available messages based on the location.
	 * 
	 * @param location The location
	 * @return A list of all available messages for the specified location
	 */
	public List<Message<?>> getMessages(Location location);

	/**
	 * Send a message.
	 * 
	 * @param user The user which is sending the message
	 * @param message The message which sould be sent
	 */
	public void sendMessage(IUser user, Message<?> message);

}