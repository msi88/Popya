package at.fhv.popya.application.service.ws;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import at.fhv.popya.application.service.ws.http.Poster;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.MessagesTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.application.transfer.UserTO;

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
	private final static String GET_MESSAGES_METHOD = "getMessages";
	private final static String CONNECT_METHOD = "connect";
	private final static String SEND_MESSAGE_METHOD = "sendMessage";

	/**
	 * Connect to the server.
	 * 
	 * @param user
	 *            The user
	 * @return A list of all available chat partners
	 * @throws UserException
	 *             Thrown if the username already exists
	 */
	public static void connect(UserTO user) throws UserException {
		// init the parameters
		Poster p = new Poster(new Gson().toJson(user), user.getPreferences()
				.getServerAddress() + CONNECT_METHOD);
		p.postData();
	}

	/**
	 * Get all available messages based on the location of the user.
	 * 
	 * @param user
	 *            The user which is looking for new messages
	 * @return A list of all available messages for the specified user
	 */
	public static List<MessageTO<Object>> getMessages(UserTO user) {
		Poster p = new Poster(new Gson().toJson(user), user.getPreferences()
				.getServerAddress() + GET_MESSAGES_METHOD);
		String ret = p.postData();

		if (ret != null && !ret.equals("null")) {
			try {
				// this fixes the wrong json output
				ret = ret.replace("\"_messages\":", "\"_messages\":[")
						.substring(0, ret.length() - 1) + "}]}";

				@SuppressWarnings("unchecked")
				MessagesTO<Object> out = new Gson().fromJson(ret,
						MessagesTO.class);

				List<MessageTO<Object>> outList = new ArrayList<MessageTO<Object>>();

				for (MessageTO<Object> messageTO : out.getMessages()) {
					// fixes the generic type issue
					@SuppressWarnings("unchecked")
					Object message = ((com.google.gson.internal.StringMap<String>) messageTO
							.getMessage()).get("$");
					outList.add(new MessageTO<Object>(messageTO.getLanguage(),
							message, messageTO.getUser()));
				}

				return outList;
			} catch (Exception e) {
				Log.d("Error: ", e.getMessage());
			}
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
	 * @throws UserException
	 *             Thrown if the user does not exist or was disconnected
	 */
	public static void sendMessage(MessageTO<?> message) throws UserException {
		// init the parameters
		Poster p = new Poster(new Gson().toJson(message), message.getUser()
				.getPreferences().getServerAddress()
				+ SEND_MESSAGE_METHOD);
		p.postData();

	}
}
