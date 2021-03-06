package at.fhv.popya.application.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a single message.
 * 
 * @author Michael
 * @version 1.0
 * @param <T>
 *            The type of the message
 */
@XmlRootElement
public class MessageTO<T> {
	public static final String LANG_DE = "de";
	public static final String LANG_EN = "en";

	private String _language;
	private T _message;
	private UserTO _user;

	/**
	 * Default constructor.
	 */
	public MessageTO() {
		this(null, null, null);
	}

	/**
	 * Create a new message.
	 * 
	 * @param language
	 *            The language of the message. May be null
	 * @param message
	 *            The message which should be transported
	 */
	public MessageTO(String language, T message, UserTO user) {
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
	 * Get the user.
	 * 
	 * @return The user
	 */
	public UserTO getUser() {
		return _user;
	}

	@XmlElement(name = "_user")
	public void setUser(UserTO user) {
		_user = user;
	}

	/**
	 * Get the language.
	 * 
	 * @return The message language. May be null
	 */
	public String getLanguage() {
		return _language;
	}

	@XmlElement(name = "_language")
	public void setLanguage(String language) {
		_language = language;
	}

	@XmlElement(name = "_message")
	public void setMessage(T message) {
		_message = message;
	}

}