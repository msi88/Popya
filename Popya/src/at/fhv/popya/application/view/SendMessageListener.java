package at.fhv.popya.application.view;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import at.fhv.popya.R;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.service.background.MessagingService;
import at.fhv.popya.settings.Settings;

public class SendMessageListener implements OnClickListener {

	private final View _view;

	public SendMessageListener(View v) {
		_view = v;
	}

	@Override
	public void onClick(View button) {

		EditText txtMessage = (EditText) _view.findViewById(R.id.txtMessage);

		// read language from settings
		Message<Object> msg = new Message<Object>(Message.LANG_EN, txtMessage
				.getText().toString(), Settings.getUser());

		try {
			MessagingService.enqueueMessage(msg);
		} catch (Exception e) {
			Log.e(getClass().toString(), "Error sending message.", e);
		}

		// reset textbox
		txtMessage.setText("");
	}
}
