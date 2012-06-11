package at.fhv.popya.application.service.background;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.service.ws.WebserviceUtil;
import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.settings.Settings;

public class MessagingBackgroundWorker extends AsyncTask<Void, Void, List<Message<Object>>> {

	     protected void onPostExecute() {
	     }

		@Override
		protected List<Message<Object>> doInBackground(Void... params) {

			List<Message<Object>> returnMessages = new ArrayList<Message<Object>>();
			
			if(MessagingService.getMessageSendQueue() != null && MessagingService.getMessageSendQueue().size() > 0)
			{
				for(Message<Object> message : MessagingService.getMessageSendQueue())
				{
					try {
						WebserviceUtil.sendMessage(message.getTransferObject());
					} catch (UserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
							
				List<MessageTO<Object>> transferMessages = WebserviceUtil.getMessages(Settings.getUser().getTransferObject());
				
				while(returnMessages.size() < transferMessages.size())
				{
					returnMessages.add((Message<Object>)returnMessages.get(returnMessages.size()).getMessage() );
				}
				

		}
			return returnMessages;
	 }

		protected void onPostExecute (List<Message<Object>> result)
		{
			MessagingService.Service.setMessages(result);		
		}
}