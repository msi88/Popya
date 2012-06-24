package at.fhv.popya;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import at.fhv.popya.application.model.User;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
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
	private MapView _mapView;
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
		_mapView.getController().setZoom(14);
		_mapView.setBuiltInZoomControls(true);

		Drawable marker = this.getResources().getDrawable(R.drawable.user);
		marker.setBounds(0, 0, marker.getIntrinsicWidth(),
				marker.getIntrinsicHeight());
		_mapView.getOverlays().add(new UserMapOverlay(marker));

		_myLocationOverlay = new MyLocationOverlay(this, _mapView);
		_mapView.getOverlays().add(_myLocationOverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		_myLocationOverlay.enableCompass();
	}

	@Override
	protected void onPause() {
		super.onResume();
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

	private GeoPoint getPoint(double lat, double lon) {
		return (new GeoPoint((int) (lat * 1000000.0), (int) (lon * 1000000.0)));
	}

	private class UserMapOverlay extends ItemizedOverlay<OverlayItem> {
		private final List<OverlayItem> items = new ArrayList<OverlayItem>();

		public UserMapOverlay(Drawable marker) {
			super(marker);

			boundCenterBottom(marker);

			if (_users != null) {
				for (int i = 0; i < _users.size(); i++) {
					User u = _users.get(i);

					if (u.getCurrentLocation() != null) {
						GeoPoint p = getPoint(u.getCurrentLocation()
								.getLatitude(), u.getCurrentLocation()
								.getLongitude());

						// set first point as center of the map
						if (i == 0) {
							_mapView.getController().setCenter(p);
						}

						items.add(new OverlayItem(p, u.getChatName(), null));
					}
				}
			}

			populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return (items.get(i));
		}

		@Override
		protected boolean onTap(int i) {
			Toast.makeText(ShowMapActivity.this, items.get(i).getTitle(),
					Toast.LENGTH_SHORT).show();

			return (true);
		}

		@Override
		public int size() {
			return (items.size());
		}
	}
}
