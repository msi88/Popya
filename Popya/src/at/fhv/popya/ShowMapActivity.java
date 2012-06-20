package at.fhv.popya;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import at.fhv.popya.application.location.UserMapOverlay;
import at.fhv.popya.application.model.User;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

/**
 * 
 * Activity for showing the user map.
 * 
 * @author Michael Sieber
 */
public class ShowMapActivity extends MapActivity {
	private MapController _mapController;
	private MapView _mapView;
	private UserMapOverlay _itemizedoverlay;
	private MyLocationOverlay _myLocationOverlay;
	private static List<User> _users = new ArrayList<User>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		// Configure the Map
		_mapView = (MapView) findViewById(R.id.mapview);
		_mapView.setBuiltInZoomControls(true);
		_mapView.setSatellite(true);
		_mapController = _mapView.getController();
		_mapController.setZoom(14); // Zoon 1 is world view

		_myLocationOverlay = new MyLocationOverlay(this, _mapView);
		_mapView.getOverlays().add(_myLocationOverlay);

		_myLocationOverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				_mapView.getController().animateTo(
						_myLocationOverlay.getMyLocation());
			}
		});

		Drawable drawable = this.getResources().getDrawable(R.drawable.user);
		_itemizedoverlay = new UserMapOverlay(this, drawable);

		if (_users != null) {
			for (User u : _users) {
				createMarker(u);
			}
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void createMarker(User user) {
		if (user.getCurrentLocation() != null) {
			int lat = (int) (user.getCurrentLocation().getLatitude() * 1E6);
			int lang = (int) (user.getCurrentLocation().getLongitude() * 1E6);
			GeoPoint p = new GeoPoint(lat, lang);
			OverlayItem overlayitem = new OverlayItem(p, user.getChatName(), "");
			_itemizedoverlay.addOverlay(overlayitem);
			if (_itemizedoverlay.size() > 0) {
				_mapView.getOverlays().add(_itemizedoverlay);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		_myLocationOverlay.enableMyLocation();
		_myLocationOverlay.enableCompass();
	}

	@Override
	protected void onPause() {
		super.onResume();
		_myLocationOverlay.disableMyLocation();
		_myLocationOverlay.disableCompass();
	}

	/**
	 * Set the users to display
	 * 
	 * @param users
	 *            The users to display
	 */
	public static void setUsers(List<User> users) {
		_users = users;
	}
}
