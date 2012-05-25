package at.fhv.popya.application.ws;

import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.MessagesTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.application.transfer.UserTO;

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
	 * @param user
	 *            The user which wants to connect
	 * @throws UserException
	 *             Thrown if the user name is already in use
	 */
	public void connect(UserTO user) throws UserException;

	/**
	 * Get all available messages based on the location of the user.
	 * 
	 * @param user
	 *            The user which is looking for new messages
	 * @return A list of all available messages for the specified user
	 */
	public MessagesTO<Object> getMessages(UserTO user) throws UserException;

	/**
	 * Send a message.
	 * 
	 * @param message
	 *            The message object which contains the sending user and the
	 *            message
	 */
	public void sendMessage(MessageTO<Object> message);

}