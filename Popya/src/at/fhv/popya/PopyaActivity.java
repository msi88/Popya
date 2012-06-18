package at.fhv.popya;

import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.model.User;
import at.fhv.popya.application.model.UserPreferences;
import at.fhv.popya.application.service.background.IMessageListener;
import at.fhv.popya.application.service.background.MessagingService;
import at.fhv.popya.application.view.MessageAdapter;
import at.fhv.popya.application.view.SendMessageListener;
import at.fhv.popya.settings.Settings;

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
		getPrefs();
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
	public void onStart() {
		super.onStart();
		getPrefs();
	}

	public void getPrefs() {
		// Get the xml/preferences.xml preferences
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		String chatName = prefs.getString("chatName", "Guest" + Math.random());
		String description = prefs.getString("description",
				"No description entered");

		int maxBroadcastDistance = Integer.valueOf(prefs.getString(
				"maxBroadcastDistance", "1000"));

		int maxReceiveDistance = Integer.valueOf(prefs.getString(
				"maxReceiveDistance", "1000"));
		String serverAddress = prefs.getString("serverAddress",	"http://vps.luukwullink.nl:8080/PopyaWebserver/rest/popya/");
		int updateIntervall = Integer.valueOf(prefs.getString("updateIntervall", "100"));

		Settings.setUserPreferences(new UserPreferences(maxBroadcastDistance,
				maxReceiveDistance, serverAddress, updateIntervall));
		Settings.setUser(new User(chatName, description, null, null, Settings
				.getUserPreferences()));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.Settings:
			Intent settingsActivity = new Intent(this,
					PopyaSettingsActivity.class);
			startActivity(settingsActivity);
			return true;
		case R.id.quit:
			System.exit(0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
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