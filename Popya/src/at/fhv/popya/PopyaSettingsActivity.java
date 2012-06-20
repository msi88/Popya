package at.fhv.popya;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PopyaSettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}