package at.fhv.popya;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import at.fhv.popya.application.model.Message;
import at.fhv.popya.application.model.User;
import at.fhv.popya.application.view.*;

/**
 * test push blaat The PopyaActivity class.
 * 
 * @author Michael Sieber
 */
public class PopyaActivity extends ListActivity {
	
	/**
	 * {@inheritDoc}
	 */
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setContentView(R.layout.main);
		setListAdapter(new MessageAdapter(this, R.layout.message_list_item,	Messages));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

	}

	// for testing purpose of the UI, can be removed once the background service is working.
	static final Message[] Messages = new Message[] {
			new Message(
					"Luuk Wullink",
					"The owner of the restaurant contacted us since he would love to get in contact with native Koreans.",
					new User("Luuk88", "Random dutch guy", null, null)),
			new Message(
					"Luuk Wullink",
					"The owner of the restaurant contacted us since he would love to get in contact with native Koreans.",
					new User("Luuk88", "Random dutch guy", null, null)),
			new Message(
					"Luuk Wullink",
					"The owner of the restaurant contacted us since he would love to get in contact with native Koreans.",
					new User("Luuk88", "Random dutch guy", null, null)), };
}