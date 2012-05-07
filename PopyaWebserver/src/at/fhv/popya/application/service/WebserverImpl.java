package at.fhv.popya.application.service;

import java.util.List;

import javax.jws.WebService;

import at.fhv.popya.application.model.MessageTO;
import at.fhv.popya.application.model.UserPreferencesTO;
import at.fhv.popya.application.model.UserTO;

@WebService(endpointInterface = "at.fhv.popya.application.service.IWebserver")
public class WebserverImpl implements IWebserver {

	@Override
	public List<UserTO> connect(UserPreferencesTO preferences, UserTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageTO<?>> getMessages(UserTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(UserTO user, MessageTO<?> message) {
		// TODO Auto-generated method stub

	}

}
