package at.fhv.popya.application.transfer;

/**
 * Represents a single message.
 * 
 * @author Michael
 * @version 1.0
 * @param <T> The type of the message
 */
public class MessageTO<T> {
	public static final String LANG_DE = "de";
	public static final String LANG_EN = "en";

	private final String _language;
	private final T _message;

	/**
	 * Create a new message.
	 * 
	 * @param language The language of the message. May be null
	 * @param message The message which should be transported
	 */
	public MessageTO(String language, T message) {
		_language = language;
		_message = message;
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

}