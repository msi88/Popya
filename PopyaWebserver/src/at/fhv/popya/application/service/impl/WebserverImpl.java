package at.fhv.popya.application.service.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.fhv.popya.application.service.IWebserver;
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

	@POST
	@Path("/connect")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public UsersTO connect(ConnectionTO connection) {
		UsersTO out = new UsersTO();
		out.getUsers().add(connection.getUser());
		// TODO implement
		return out;
	}

	@POST
	@Path("/getMessages")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public MessagesTO<Object> getMessages(UserTO user) {
		MessagesTO<Object> messages = new MessagesTO<Object>();
		MessageTO<Object> message = new MessageTO<Object>(MessageTO.LANG_EN,
				"My test message");
		messages.getMessages().add(message);
		// TODO implement
		return messages;
	}

	@POST
	@Path("/sendMessage")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public void sendMessage(MessageSenderTO message) {
		// TODO Auto-generated method stub

	}

}
