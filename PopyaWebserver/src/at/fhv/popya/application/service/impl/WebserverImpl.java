package at.fhv.popya.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.fhv.popya.application.service.timer.GarbageTimer;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.MessagesTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.application.transfer.UserTO;
import at.fhv.popya.application.ws.IWebserver;

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
		new Timer().schedule(new GarbageTimer(_messages),
				CLEAN_INTERVAL_MILLISEC, CLEAN_INTERVAL_MILLISEC);
	}

	@POST
	@Path("/connect")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public void connect(@FormParam("user") UserTO user) throws UserException {
		// register user
		if (!_messages.containsKey(user)) {
			List<MessageTO<Object>> messageList = new ArrayList<MessageTO<Object>>();
			_messages.put(user, messageList);
		} else {
			throw new UserException("User name already in use.");
		}
	}

	@POST
	@Path("/getMessages")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public MessagesTO<Object> getMessages(@FormParam("user") UserTO user)
			throws UserException {
		if (!_messages.containsKey(user)) {
			throw new UserException("User is not connected.");
		}

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
	public void sendMessage(@FormParam("message") MessageTO<Object> message) {
		for (UserTO receiver : _messages.keySet()) {
			if (canCommunicate(receiver, message.getUser())) {
				_messages.get(receiver).add(message);
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

	/**
	 * Get all messages.
	 * 
	 * @return The messages
	 */
	public static Map<UserTO, List<MessageTO<Object>>> getMessages() {
		return _messages;
	}

}
