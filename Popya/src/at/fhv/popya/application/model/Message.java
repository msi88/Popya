package at.fhv.popya.application.model;

import at.fhv.popya.application.model.transfer.ITransferable;
import at.fhv.popya.application.transfer.MessageTO;

/**
 * Represents a single message.
 * 
 * @author Michael
 * @version 1.0
 * @param <T>
 *            The type of the message
 */
public class Message<T> implements ITransferable<MessageTO<T>> {
	public static final String LANG_DE = "de";
	public static final String LANG_EN = "en";

	private final User _user;
	private final String _language;
	private final T _message;

	/**
	 * Create a new message.
	 * 
	 * @param language
	 *            The language of the message. May be null
	 * @param message
	 *            The message which should be transported
	 */
	public Message(String language, T message, User user) {
		_language = language;
		_message = message;
		_user = user;
	}

	/**
	 * Get the message.
	 * 
	 * @return The message
	 */
	public T getMessage() {
		return _message;
	}

	/**
	 * Get the language.
	 * 
	 * @return The message language. May be null
	 */
	public String getLanguage() {
		return _language;
	}

	@Override
	public MessageTO<T> getTransferObject() {
		// TODO Auto-generated method stub
		return new MessageTO<T>(this.getLanguage(), this.getMessage(),this.get_user().getTransferObject());
	}

	public User get_user() {
		return _user;
	}
}
