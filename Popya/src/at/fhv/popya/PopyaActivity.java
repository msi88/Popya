package at.fhv.popya;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import at.fhv.popya.application.model.Message;
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
	private MessageAdapter _adapter;
	private Intent _serviceIntent;

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

		Button btnMessage = (Button) v.findViewById(R.id.btnMessage);
		btnMessage.setOnClickListener(new SendMessageListener(v));

		_adapter = new MessageAdapter(this, R.layout.message_list_item);
		lv.setAdapter(_adapter);

		// start the service
		_serviceIntent = new Intent(this, MessagingService.class);
		startService(_serviceIntent);

		// register for messages
		MessagingService.registerListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(_serviceIntent);
	}

	@Override
	public void notify(final List<Message<Object>> messages) {
		// the notify call probably comes from an other thread, which cannot
		// manipulate things on the ui thread. The following statement fixes
		// this issue
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				_adapter.addMessages(messages);
			}
		});
	}
}