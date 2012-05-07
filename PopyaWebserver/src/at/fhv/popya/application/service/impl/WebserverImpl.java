package at.fhv.popya.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import at.fhv.popya.application.service.IWebserver;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserPreferencesTO;
import at.fhv.popya.application.transfer.UserTO;

@WebService(endpointInterface = "at.fhv.popya.application.service.IWebserver")
public class WebserverImpl implements IWebserver {

	@Override
	public List<UserTO> connect(UserPreferencesTO preferences, UserTO user) {
		List<UserTO> out = new ArrayList<UserTO>();
		out.add(user);
		// TODO implement
		return out;
	}

	@Override
	public List<MessageTO<?>> getMessages(UserTO user) {
		List<MessageTO<?>> out = new ArrayList<MessageTO<?>>();
		out.add(new MessageTO<String>(MessageTO.LANG_EN, "My test message"));
		// TODO implement
		return out;
	}

	@Override
	public void sendMessage(UserTO user, MessageTO<?> message) {
		// TODO Auto-generated method stub

	}

}
