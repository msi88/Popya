package at.fhv.popya;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import at.fhv.popya.application.location.LocationHelper;
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
		super.onCreate(savedInstanceState);
		_serviceIntent = new Intent(this, MessagingService.class);

		ListView lv = getListView();

		// layout adjustments
		lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		lv.setStackFromBottom(true);

		View v = getLayoutInflater()
				.inflate(R.layout.message_list_footer, null);
		lv.addFooterView(v);

		Button btnMessage = (Button) v.findViewById(R.id.btnMessage);
		btnMessage.setOnClickListener(new SendMessageListener(v));

		_adapter = new MessageAdapter(this, R.layout.message_list_item);
		lv.setAdapter(_adapter);

		initLocationManager();
		startService(_serviceIntent);

	}

	/**
	 * Initialize the location manager
	 */
	private void initLocationManager() {
		// init the location helper
		LocationHelper.init(getBaseContext());

		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// Check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to
		// go to the settings
		if (!enabled) {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
	}

	/**
	 * Restart the messaging servie
	 */
	private void restartMessagingService() {
		stopService(_serviceIntent);
		try {
			synchronized (this) {
				wait(1000);
			}
		} catch (InterruptedException e) {
			// ignore
		}

		// register for messages
		MessagingService.registerListener(this);

		startService(_serviceIntent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(_serviceIntent);
	}

	@Override
	public void onStart() {
		super.onStart();
		loadPrefs();
		MessagingService.registerListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MessagingService.registerListener(this);

		if (PopyaSettingsActivity.MODIFIED) {
			loadPrefs();
			restartMessagingService();

			// reset
			PopyaSettingsActivity.MODIFIED = false;
		}
	}

	public void loadPrefs() {
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
		String serverAddress = prefs.getString("serverAddress",
				"http://vps.luukwullink.nl:8080/PopyaWebserver/rest/popya/");
		int updateIntervall = Integer.valueOf(prefs.getString(
				"updateIntervall", "1000"));

		Settings.setUserPreferences(new UserPreferences(maxBroadcastDistance,
				maxReceiveDistance, serverAddress, updateIntervall));
		Settings.setUser(new User(chatName, description, null, Settings
				.getUserPreferences()));
	}

	@SuppressWarnings("unchecked")
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
		case R.id.map:

			// add all known users
			int count = getListView().getCount() - 1;
			List<User> users = new ArrayList<User>();
			for (int i = 0; i < count; i++) {
				Message<Object> message = (Message<Object>) getListView()
						.getItemAtPosition(i);
				if (message != null && !users.contains(message.get_user())) {
					users.add(message.get_user());
				}
			}

			// add local user if not in list
			if (!users.contains(Settings.getUser())) {
				// if the local user has no current location, set it
				if (Settings.getUser().getCurrentLocation() == null) {
					Settings.getUser().setCurrentLocation(
							LocationHelper.getLocation());
				}
				// add local user
				users.add(Settings.getUser());
			}

			ShowMapActivity.setUsers(users);
			Intent mapIntent = new Intent(this, ShowMapActivity.class);
			startActivity(mapIntent);
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