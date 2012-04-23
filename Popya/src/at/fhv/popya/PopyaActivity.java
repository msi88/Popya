package at.fhv.popya;

import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * The PopyaActivity class.
 * 
 * @author Michael Sieber
 */
public class PopyaActivity extends Activity {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}