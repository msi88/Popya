package at.fhv.popya.server;

import java.util.List;

import at.fhv.popya.application.IWebserver;
import at.fhv.popya.application.entity.IUser;
import at.fhv.popya.application.entity.impl.Message;
import at.fhv.popya.application.entity.impl.UserPreferences;

/**
 * The popya webservice object.
 * 
 * @author Michael
 * 
 */
public class PopyaWebservice implements IWebserver {

	public List<IUser> connect(UserPreferences preferences, IUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Message<?>> getMessages(IUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendMessage(IUser user, Message<?> message) {
		// TODO Auto-generated method stub

	}

}
