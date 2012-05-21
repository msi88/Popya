package at.fhv.popya.application.service.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import at.fhv.popya.application.transfer.MessageSenderTO;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.MessagesTO;
import at.fhv.popya.application.transfer.UserPreferencesTO;
import at.fhv.popya.application.transfer.UserTO;
import at.fhv.popya.application.transfer.UsersTO;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Util class for webserver interaction. see:
 * http://www.josecgomez.com/2010/04/30
 * /android-accessing-restfull-web-services-using-json/
 * 
 * @author Michael
 * 
 */
public class WebserviceUtil {
	private final static String GET_MESSAGES_METHOD = "/getMessages";
	private final static String CONNECT_METHOD = "/connect";
	private final static String SEND_MESSAGE_METHOD = "/sendMessage";

	/**
	 * Connect to the server.
	 * 
	 * @param preferences
	 *            The user preferences
	 * @param user
	 *            The user
	 * @return A list of all available chat partners
	 */
	public static List<UserTO> connect(UserPreferencesTO preferences,
			UserTO user) {
		WebService service = new WebService(user.getPreferences()
				.getServerAddress() + CONNECT_METHOD);

		// init the parameters
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("preferences", preferences);

		String response = service.webInvoke("", params);

		try {
			// Parse Response into our object
			UsersTO users = new Gson().fromJson(response, UsersTO.class);

			if (users != null) {
				return users.getUsers();
			}
		} catch (Exception e) {
			Log.d("Error: ", e.getMessage());
		}

		return new ArrayList<UserTO>();
	}

	/**
	 * Get all available messages based on the location of the user.
	 * 
	 * @param user
	 *            The user which is looking for new messages
	 * @return A list of all available messages for the specified user
	 */
	public static List<MessageTO<Object>> getMessages(UserTO user) {
		WebService service = new WebService(user.getPreferences()
				.getServerAddress() + GET_MESSAGES_METHOD);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);

		String response = service.webInvoke("", params);

		try {
			// Parse Response into our object
			@SuppressWarnings("unchecked")
			MessagesTO<Object> messages = new Gson().fromJson(response,
					MessagesTO.class);

			if (messages != null) {
				return messages.getMessages();
			}
		} catch (Exception e) {
			Log.d("Error: ", e.getMessage());
		}

		return new ArrayList<MessageTO<Object>>();
	}

	/**
	 * Send a message.
	 * 
	 * @param user
	 *            The user which is sending the message
	 * @param message
	 *            The message which should be sent
	 */
	public static void sendMessage(UserTO user, MessageTO<?> message) {
		
		WebService service = new WebService(user.getPreferences().getServerAddress() + SEND_MESSAGE_METHOD);

		// init the parameters
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("message", message);

		service.webInvoke("", params);
	}
	
	/**
	 * Send a message.
	 * 
	 * @param user
	 *            The user which is sending the message
	 * @param message
	 *            The message which should be sent
	 */
	public static void sendMessage(MessageSenderTO message) {
		

		WebService service = new WebService(message.getUser().getPreferences().getServerAddress() + SEND_MESSAGE_METHOD);

		// init the parameters
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("message", message.getMessage());

		service.webInvoke("", params);
		
	}
}
