package at.fhv.popya.application.transfer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageSenderTO {
	private UserTO _user;
	private MessageTO<Object> _message;

	/**
	 * Default constructor.
	 */
	public MessageSenderTO() {

	}

	public UserTO getUser() {
		return _user;
	}

	public void setUser(UserTO user) {
		_user = user;
	}

	public MessageTO<Object> getMessage() {
		return _message;
	}

	public void setMessage(MessageTO<Object> message) {
		_message = message;
	}

}
