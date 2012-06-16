package at.fhv.popya;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.model.User;
import at.fhv.popya.application.service.background.IMessageListener;
import at.fhv.popya.application.service.background.MessagingService;
import at.fhv.popya.application.view.MessageAdapter;
import at.fhv.popya.application.view.SendMessageListener;

/**
 * test push blaat The PopyaActivity class.
 * 
 * @author Michael Sieber
 */
public class PopyaActivity extends ListActivity implements IMessageListener {

	/**
	 * {@inheritDoc}
	 */
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ListView lv = getListView();

		View v = getLayoutInflater()
				.inflate(R.layout.message_list_footer, null);
		lv.addFooterView(v);
		lv.setAdapter(new MessageAdapter(this, R.layout.message_list_item,
				Messages));

		Button btnMessage = (Button) v.findViewById(R.id.btnMessage);
		btnMessage.setOnClickListener(new SendMessageListener(v));

		lv.setAdapter(new MessageAdapter(this, R.layout.message_list_item,
				Messages));

		// start the service
		startService(new Intent(this, MessagingService.class));

		// register for messages
		MessagingService.registerListener(this);
	}

	// for testing purpose of the UI, can be removed once the background service
	// is working.
	static final Message<?>[] Messages = new Message[] {
			new Message<String>(
					"Luuk Wullink",
					"The owner of the restaurant contacted us since he would love to get in contact with native Koreans.",
					new User("Luuk88", "Random dutch guy", null, null, null)),
			new Message<String>(
					"Luuk Wullink",
					"The owner of the restaurant contacted us since he would love to get in contact with native Koreans.",
					new User("Luuk88", "Random dutch guy", null, null, null)),
			new Message<String>(
					"Luuk Wullink",
					"The owner of the restaurant contacted us since he would love to get in contact with native Koreans.",
					new User("Luuk88", "Random dutch guy", null, null, null)) };

	@Override
	public void notify(List<Message<Object>> messages) {
		// TODO update list view here!!
	}
}