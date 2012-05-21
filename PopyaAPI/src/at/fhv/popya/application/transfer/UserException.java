package at.fhv.popya.application.transfer;

/**
 * Exception for all types of errors concerning the user.
 * 
 * @author Michael
 * 
 */
public class UserException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}
}
