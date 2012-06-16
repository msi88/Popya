package at.fhv.popya.application.service.background;

import java.util.List;

import at.fhv.popya.application.model.Message;

/**
 * 
 * Interface for all message change listeners
 * 
 * @author Michael Sieber
 */
public interface IMessageListener {

	/**
	 * This method gets called, if new messages are available
	 * 
	 * @param messages
	 *            The new messages
	 */
	public void notify(List<Message<Object>> messages);
}
