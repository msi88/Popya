package at.fhv.popya.application.service.background;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.model.transfer.TransferHelper;
import at.fhv.popya.application.service.ws.WebserviceUtil;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.application.transfer.UserTO;
import at.fhv.popya.settings.Settings;

/**
 * Background service for messaging.
 * 
 * @author Michael
 * @version 1.0
 */
public class MessagingService extends Service {
	private static List<IMessageListener> LISTENER;
	private List<Message<Object>> _messages;
	private static Queue<Message<Object>> MESSAGE_SEND_QUEUE;

	static {
		LISTENER = new ArrayList<IMessageListener>();
		MESSAGE_SEND_QUEUE = new LinkedList<Message<Object>>();
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
	private void connect() {

		try {
			UserTO user = Settings.getUser().getTransferObject();
			WebserviceUtil.connect(user);

		} catch (UserException e) {
			Log.e(getClass().toString(), "Error connecting to the server.", e);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		_messages = new ArrayList<Message<Object>>();
		Settings.loadSettings();
		connect();

		// define task for sending messages
		TimerTask senderTask = new TimerTask() {

			@Override
			public void run() {
				if (MESSAGE_SEND_QUEUE != null) {
					while (!MESSAGE_SEND_QUEUE.isEmpty()) {
						try {
							WebserviceUtil.sendMessage(MESSAGE_SEND_QUEUE
									.poll().getTransferObject());
						} catch (UserException e) {
							Log.e(getClass().toString(),
									"Error sending message.", e);
						}
					}
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(senderTask, 500, 10000);

		// creating task for receiving messages
		TimerTask receiverTask = new TimerTask() {

			@Override
			public void run() {
				List<MessageTO<Object>> messages = WebserviceUtil
						.getMessages(Settings.getUser().getTransferObject());

				for (MessageTO<Object> messageTO : messages) {
					_messages.add(new Message<Object>(messageTO.getLanguage(),
							messageTO.getMessage(), TransferHelper
									.getUser(messageTO.getUser())));
				}
				notifyListener();
			}
		};
		timer.schedule(receiverTask, 500, Settings.getUserPreferences()
				.getUpdateIntervall());
	}

	/**
	 * Notify all listeners
	 */
	private void notifyListener() {
		for (IMessageListener listener : LISTENER) {
			listener.notify(_messages);
		}
	}

	@Override
	public void onDestroy() {

		_messages = null;
		MESSAGE_SEND_QUEUE = null;

		Toast.makeText(this, "Messaging service stopped", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onStart(Intent intent, int startid) {

	}

	/**
	 * Register a new listener
	 * 
	 * @param listener
	 *            The listener to register
	 */
	public static void registerListener(IMessageListener listener) {
		if (!LISTENER.contains(listener)) {
			LISTENER.add(listener);
		}
	}

	/**
	 * Remove a listener
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	public static void removeListener(IMessageListener listener) {
		if (LISTENER.contains(listener)) {
			LISTENER.remove(listener);
		}
	}

	/**
	 * Add a message to the sending queue
	 * 
	 * @param message
	 *            The message to add to the queue
	 */
	public static void enqueueMessage(Message<Object> message) {
		MESSAGE_SEND_QUEUE.offer(message);
	}
}