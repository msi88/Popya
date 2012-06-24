package at.fhv.popya.application.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import at.fhv.popya.application.transfer.LocationTO;

/**
 * 
 * Helper class for location operations.
 * 
 * @author Michael Sieber
 */
public class LocationHelper {
	private static LocationManager _manager;

	/**
	 * Get the location
	 * 
	 * @return The transfer object for locations
	 */
	public static LocationTO getLocation() {
		if (_manager != null) {
			// Define the criteria how to select the locatioin provider -> use
			// default
			Criteria criteria = new Criteria();
			String provider = _manager.getBestProvider(criteria, false);
			Location location = _manager.getLastKnownLocation(provider);

			// Initialize the location fields
			if (location != null) {
				double lat = location.getLatitude();
				double lang = location.getLongitude();
				double alt = location.getAltitude();
				return new LocationTO(lat, lang, alt);
			} else {
				// TODO remove
				double lat = 47.479842;
				double lang = 9.764936;
				double alt = 0d;
				return new LocationTO(lat, lang, alt);
			}
		}

		return null;
	}

	/**
	 * Intialize the location helper
	 * 
	 * @param baseContext
	 *            The context
	 */
	public static void init(Context baseContext) {
		_manager = (LocationManager) baseContext
				.getSystemService(Context.LOCATION_SERVICE);
	}
}
