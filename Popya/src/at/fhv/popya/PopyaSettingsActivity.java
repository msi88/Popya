package at.fhv.popya;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PopyaSettingsActivity extends PreferenceActivity {
	public static boolean MODIFIED = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		MODIFIED = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MODIFIED = true;
	}
}