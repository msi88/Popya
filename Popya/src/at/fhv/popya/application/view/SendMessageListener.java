package at.fhv.popya.application.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import at.fhv.popya.R;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.service.background.MessagingService;
import at.fhv.popya.application.service.ws.WebserviceUtil;
import at.fhv.popya.application.transfer.UserException;
import at.fhv.popya.settings.Settings;

public class SendMessageListener implements OnClickListener {

	private final View _v;

	public SendMessageListener(View v) {
		_v = v;
	}

	@Override
	public void onClick(View arg0) {

		EditText TxtMessage = (EditText) _v.findViewById(R.id.txtMessage);

		// read language from settings
		Message<Object> msg = new Message<Object>(Message.LANG_EN, TxtMessage
				.getText().toString(), Settings.getUser());

		try {
			WebserviceUtil.sendMessage(msg.getTransferObject());
		} catch (UserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			MessagingService.Service.sendMessage(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
