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
import at.fhv.popya.application.location.LocationHelper;
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
	private static final long SENDING_INTERVAL = 5000;
	private static List<IMessageListener> LISTENER;
	private List<Message<Object>> _messages;
	private static Queue<Message<Object>> MESSAGE_SEND_QUEUE;
	private Timer _timer = new Timer();

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
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		_timer.cancel();
		_timer = new Timer();
		_messages = new ArrayList<Message<Object>>();
		connect();

		// define task for sending messages
		TimerTask senderTask = new TimerTask() {

			@Override
			public void run() {
				if (MESSAGE_SEND_QUEUE != null) {
					while (!MESSAGE_SEND_QUEUE.isEmpty()) {
						try {
							MessageTO<?> tmp = MESSAGE_SEND_QUEUE.poll()
									.getTransferObject();
							tmp.getUser().setCurrentLocation(
									LocationHelper.getLocation());
							WebserviceUtil.sendMessage(tmp);
						} catch (UserException e) {
							Log.e(getClass().toString(),
									"Error sending message.", e);
						}
					}
				}
			}
		};
		_timer.schedule(senderTask, 500, SENDING_INTERVAL);

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
		_timer.schedule(receiverTask, 500, Settings.getUserPreferences()
				.getUpdateIntervall());

		Toast.makeText(this, "Messaging service started", Toast.LENGTH_LONG)
				.show();
	}

	/**
	 * Notify all listeners
	 */
	private void notifyListener() {
		if (LISTENER != null) {
			if (_messages != null) {
				for (IMessageListener listener : LISTENER) {
					listener.notify(new ArrayList<Message<Object>>(_messages));
				}
				_messages.clear();
			}
		}
	}

	@Override
	public void onDestroy() {

		_messages = null;
		MESSAGE_SEND_QUEUE = null;
		_timer.cancel();

		Toast.makeText(this, "Messaging service stopped", Toast.LENGTH_LONG)
				.show();
	}

	/**
	 * Register a new listener
	 * 
	 * @param listener
	 *            The listener to register
	 */
	public static void registerListener(IMessageListener listener) {
		if (LISTENER == null) {
			LISTENER = new ArrayList<IMessageListener>();
		}

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
		if (MESSAGE_SEND_QUEUE == null) {
			MESSAGE_SEND_QUEUE = new LinkedList<Message<Object>>();
		}

		MESSAGE_SEND_QUEUE.offer(message);
	}
}