package at.fhv.popya.application.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserPreferencesTO;
import at.fhv.popya.application.transfer.UserTO;

/**
 * Interface for webserver interaction.
 * 
 * @author Michael
 * @version 1.0
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IWebserver {

	/**
	 * Connect to the server.
	 * 
	 * @param preferences
	 *            The user preferences
	 * @param user
	 *            The user
	 * @return A list of all available chat partners
	 */
	@WebMethod
	public List<UserTO> connect(UserPreferencesTO preferences, UserTO user);

	/**
	 * Get all available messages based on the location of the user.
	 * 
	 * @param user
	 *            The user which is looking for new messages
	 * @return A list of all available messages for the specified user
	 */
	@WebMethod
	public List<MessageTO<?>> getMessages(UserTO user);

	/**
	 * Send a message.
	 * 
	 * @param user
	 *            The user which is sending the message
	 * @param message
	 *            The message which should be sent
	 */
	@WebMethod
	public void sendMessage(UserTO user, MessageTO<?> message);

}