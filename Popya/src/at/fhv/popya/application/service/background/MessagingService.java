package at.fhv.popya.application.service.background;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.service.ws.WebserviceUtil;
import at.fhv.popya.application.transfer.LocationTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.settings.Settings;

/**
 * Background service for messaging.
 * 
 * @author Michael
 * @version 1.0
 */
public class MessagingService extends Service {

	private static List<Message<Object>> Messages;
	private static List<Message<Object>> MessageSendQueue;
	public static MessagingService Service;

	public static List<Message<Object>> getMessages() {
		return Messages;
	}

	public static void setMessages(List<Message<Object>> messages) {
		Messages = messages;
	}

	public static List<Message<Object>> getMessageSendQueue() {
		return MessageSendQueue;
	}

	/**
	 * Default constructor.
	 */
	public MessagingService() {
		this.Service = this;
	}

	/**
	 * Connect to the webserver.
	 * 
	 * @param preferences
	 *            The user preferences
	 * @param user
	 *            The user
	 * @return A list of all available chat partners or an empty list if no chat
	 *         partner can be found
	 */
	public void connect() {
		try {
			WebserviceUtil.connect(Settings.getUser().getTransferObject());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get all available messages based on my location.
	 * 
	 * @param location
	 *            The location
	 * @return A list of all available messages based on my location or an empty
	 *         list if no messages are available
	 */
	public List<Message<Object>> getMessages(LocationTO location) {

		return Messages;
	}

	/**
	 * Send a message.
	 * 
	 * @param message
	 *            The message which should be sent
	 * @param user
	 *            The user which has sent the message
	 */
	public void sendMessage(Message<Object> message) {
		MessageSendQueue.add(message);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		Messages = new ArrayList<Message<Object>>();
		MessageSendQueue = new ArrayList<Message<Object>>();
		Settings.loadSettings();
	}

	@Override
	public void onDestroy() {

		Messages = null;
		MessageSendQueue = null;

		Toast.makeText(this, "Messaging service stopped", Toast.LENGTH_LONG)
				.show();

		/*
		 * Log.d(TAG, "onDestroy"); player.stop();
		 */
	}

	@Override
	public void onStart(Intent intent, int startid) {

			new MessagingBackgroundWorker().execute();
	}
}