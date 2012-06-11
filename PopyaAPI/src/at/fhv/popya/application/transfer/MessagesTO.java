package at.fhv.popya.application.transfer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessagesTO<T> {
	private List<MessageTO<T>> _messages;

	/**
	 * Default constructor.
	 */
	public MessagesTO() {
		_messages = new ArrayList<MessageTO<T>>();
	}

	public List<MessageTO<T>> getMessages() {
		return _messages;
	}

	@XmlElement(name = "_messages")
	public void setMessages(List<MessageTO<T>> messages) {
		_messages = new ArrayList<MessageTO<T>>(messages);
	}

}
