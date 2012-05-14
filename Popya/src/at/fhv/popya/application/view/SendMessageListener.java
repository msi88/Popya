package at.fhv.popya.application.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import at.fhv.popya.R;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.model.User;
import at.fhv.popya.application.model.UserPreferences;
import at.fhv.popya.application.service.ws.WebserviceUtil;
import at.fhv.popya.application.transfer.UserPreferencesTO;

public class SendMessageListener implements OnClickListener {

	private View _v;
	
	public SendMessageListener(View v)
	{
	_v = v;
	}
	
	@Override
	public void onClick(View arg0) {


		EditText TxtMessage = (EditText) _v.findViewById(R.id.txtMessage);
		
		UserPreferencesTO to = new UserPreferencesTO();
		
		// load preferences from user itself next time
		UserPreferences tempPref = new UserPreferences(100, 100,"http://vps.luukwullink.nl:8080/PopyaWebserver/rest/popya", 1000);
		
		// Load real user
		User tempUsr = new User("Luuk88", "Random dutch guy", null, null,tempPref);
		
		// read language from settings
		Message<String> msg = new Message<String>(Message.LANG_EN,TxtMessage.getText().toString(),tempUsr);
		
		WebserviceUtil.connect(tempPref.getTransferObject(), tempUsr.getTransferObject());
		WebserviceUtil.sendMessage(tempUsr.getTransferObject(), msg.getTransferObject());
		
	}

}
