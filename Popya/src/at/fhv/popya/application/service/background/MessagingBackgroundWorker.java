package at.fhv.popya.application.service.background;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.service.ws.WebserviceUtil;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.settings.Settings;

public class MessagingBackgroundWorker extends
		AsyncTask<Void, Void, List<MessageTO<Object>>> {

	protected void onPostExecute() {
	}

	@Override
	protected List<MessageTO<Object>> doInBackground(Void... params) {

		List<MessageTO<Object>> transferMessages = new ArrayList<MessageTO<Object>>();

		if (MessagingService.getMessageSendQueue() != null
				&& MessagingService.getMessageSendQueue().size() > 0) {
			while (!MessagingService.getMessageSendQueue().isEmpty()) {
				try {
					WebserviceUtil.sendMessage(MessagingService
							.getMessageSendQueue().peek().getTransferObject());
				} catch (UserException e) {
					Log.e(getClass().toString(), "Error sending message.", e);
				}
			}

			transferMessages = WebserviceUtil.getMessages(Settings.getUser()
					.getTransferObject());
		}
		return transferMessages;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onPostExecute(List<MessageTO<Object>> result) {
		for (MessageTO<Object> msg : result) {
			MessagingService.addMessages(((Message<Object>) msg.getMessage()));
		}
	}
}