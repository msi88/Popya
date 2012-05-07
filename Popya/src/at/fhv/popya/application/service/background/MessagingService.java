package at.fhv.popya.application.service.background;

import java.util.ArrayList;
import java.util.List;

import at.fhv.popya.application.model.IUser;
import at.fhv.popya.application.model.Location;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.model.impl.UserPreferences;
import at.fhv.popya.application.service.IWebserver;

/**
 * Background service for messaging.
 * 
 * @author Michael
 * @version 1.0
 */
public class MessagingService {

	private IWebserver _webserver;

	/**
	 * Default constructor.
	 */
	public MessagingService() {

	}

	/**
	 * Connect to the webserver.
	 * 
	 * @param preferences
	 *            The user preferences
	 * @param user
	 *            The user
	 * @return A list of all available chat partners or an empty list if no chat
	 *         partner can be found
	 */
	public List<IUser> connect(UserPreferences preferences, IUser user) {
		return new ArrayList<IUser>();
	}

	/**
	 * Get all available messages based on my location.
	 * 
	 * @param location
	 *            The location
	 * @return A list of all available messages based on my location or an empty
	 *         list if no messages are available
	 */
	public List<Message<?>> getMessages(Location location) {
		return new ArrayList<Message<?>>();
	}

	/**
	 * Send a message.
	 * 
	 * @param message
	 *            The message which should be sent
	 * @param user
	 *            The user which has sent the message
	 */
	public void sendMessage(Message<?> message, IUser user) {

	}

}