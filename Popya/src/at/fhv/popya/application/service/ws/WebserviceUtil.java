package at.fhv.popya.application.service.ws;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserPreferencesTO;
import at.fhv.popya.application.transfer.UserTO;

/**
 * Util class for webserver interaction.
 * 
 * @author Michael
 * 
 */
public class WebserviceUtil {
	private final static String NAMESPACE = "http://impl.service.application.popya.fhv.at/";
	private final static String GET_MESSAGES_METHOD = "getMessages";
	private final static String CONNECT_METHOD = "connect";
	private final static String SEND_MESSAGE_METHOD = "sendMessage";

	/**
	 * Connect to the server.
	 * 
	 * @param preferences
	 *            The user preferences
	 * @param user
	 *            The user
	 * @return A list of all available chat partners
	 */
	@SuppressWarnings("unchecked")
	public List<UserTO> connect(UserPreferencesTO preferences, UserTO user) {
		SoapObject Request = new SoapObject(NAMESPACE, CONNECT_METHOD);
		String url = preferences.getServerAddress();

		// add parameters
		PropertyInfo prefPi = new PropertyInfo();
		prefPi.setName("preferences");
		prefPi.setValue(preferences);
		prefPi.setType(UserPreferencesTO.class);
		Request.addProperty(prefPi);

		PropertyInfo userPi = new PropertyInfo();
		userPi.setName("user");
		userPi.setValue(user);
		userPi.setType(UserTO.class);
		Request.addProperty(userPi);

		// prepare transmission
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(Request);

		// request
		HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
		try {
			androidHttpTransport.call(NAMESPACE + CONNECT_METHOD, envelope);
			SoapObject response = (SoapObject) envelope.getResponse();
			return (List<UserTO>) response.getProperty(0);
		} catch (Exception e) {
			e.printStackTrace();
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
	@SuppressWarnings("unchecked")
	public static List<MessageTO<?>> getMessages(UserTO user) {
		SoapObject Request = new SoapObject(NAMESPACE, GET_MESSAGES_METHOD);
		String url = user.getPreferences().getServerAddress();

		// add parameters
		PropertyInfo pi = new PropertyInfo();
		pi.setName("user");
		pi.setValue(user);
		pi.setType(UserTO.class);
		Request.addProperty(pi);

		// prepare transmission
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(Request);

		// request
		HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
		try {
			androidHttpTransport
					.call(NAMESPACE + GET_MESSAGES_METHOD, envelope);
			SoapObject response = (SoapObject) envelope.getResponse();
			return (List<MessageTO<?>>) response.getProperty(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<MessageTO<?>>();
	}

	/**
	 * Send a message.
	 * 
	 * @param user
	 *            The user which is sending the message
	 * @param message
	 *            The message which should be sent
	 */
	public void sendMessage(UserTO user, MessageTO<?> message) {
		SoapObject Request = new SoapObject(NAMESPACE, SEND_MESSAGE_METHOD);
		String url = user.getPreferences().getServerAddress();

		// add parameters
		PropertyInfo userPi = new PropertyInfo();
		userPi.setName("user");
		userPi.setValue(user);
		userPi.setType(UserTO.class);
		Request.addProperty(userPi);

		PropertyInfo messagePi = new PropertyInfo();
		messagePi.setName("message");
		messagePi.setValue(message);
		messagePi.setType(MessageTO.class);
		Request.addProperty(messagePi);

		// prepare transmission
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(Request);

		// request
		HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
		try {
			androidHttpTransport
					.call(NAMESPACE + SEND_MESSAGE_METHOD, envelope);
			envelope.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
