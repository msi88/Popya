package at.fhv.popya.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.fhv.popya.application.service.IWebserver;
import at.fhv.popya.application.service.timer.GarbageTimer;
import at.fhv.popya.application.transfer.ConnectionTO;
import at.fhv.popya.application.transfer.MessageSenderTO;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.MessagesTO;
import at.fhv.popya.application.transfer.UserTO;
import at.fhv.popya.application.transfer.UsersTO;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/popya")
public class WebserverImpl implements IWebserver {
	private static Map<UserTO, List<MessageTO<Object>>> _messages;
	private static final int CLEAN_INTERVAL_MILLISEC = 5 * 60 * 1000;

	static {
		// list with all users and their received messages
		_messages = new HashMap<UserTO, List<MessageTO<Object>>>();

		// initialize the garbage timer for cleaning the messages
		new Timer().schedule(new GarbageTimer(_messages), 1000,
				CLEAN_INTERVAL_MILLISEC);
	}

	@POST
	@Path("/connect")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public UsersTO connect(ConnectionTO connection) {
		// register user
		if (!_messages.containsKey(connection.getUser())) {
			List<MessageTO<Object>> messageList = new ArrayList<MessageTO<Object>>();
			_messages.put(connection.getUser(), messageList);
		}

		return getAllChatPartners(connection.getUser());
	}

	/**
	 * Get all availabel chat partners for a specific user
	 * 
	 * @param user
	 *            The user for whom all available chat partners should be loaded
	 */
	private UsersTO getAllChatPartners(UserTO user) {
		// FIXME test implementation
		UsersTO out = new UsersTO();
		out.getUsers().add(user);
		return out;
	}

	@POST
	@Path("/getMessages")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public MessagesTO<Object> getMessages(UserTO user) {

		// get the messages
		MessagesTO<Object> messages = new MessagesTO<Object>();
		messages.setMessages(_messages.get(user));

		// reset the message list
		_messages.get(user).clear();
		return messages;
	}

	@POST
	@Path("/sendMessage")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public void sendMessage(MessageSenderTO message) {
		for (UserTO receiver : _messages.keySet()) {
			if (canCommunicate(receiver, message.getUser())) {
				_messages.get(receiver).add(message.getMessage());
			}
		}

	}

	/**
	 * Determine if two users can communicate with each other
	 * 
	 * @param receiver
	 *            The potential receiver
	 * @param sender
	 *            The sender of the message
	 * @return True if receiver and sender can communicate
	 */
	private boolean canCommunicate(UserTO receiver, UserTO sender) {
		// TODO implement
		return true;
	}

}
