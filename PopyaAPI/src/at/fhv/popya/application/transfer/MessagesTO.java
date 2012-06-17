package at.fhv.popya.application.transfer;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessagesTO<T> {
	private ArrayList<MessageTO<T>> _messages;

	/**
	 * Default constructor.
	 */
	public MessagesTO() {
		_messages = new ArrayList<MessageTO<T>>();
	}

	public ArrayList<MessageTO<T>> getMessages() {
		return _messages;
	}

	@XmlElement(name = "_messages")
	public void setMessages(ArrayList<MessageTO<T>> messages) {
		_messages = new ArrayList<MessageTO<T>>(messages);
	}

}
