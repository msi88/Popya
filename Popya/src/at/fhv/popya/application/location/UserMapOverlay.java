package at.fhv.popya.application.location;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class UserMapOverlay extends ItemizedOverlay<OverlayItem> {
	private static int MAX_ITEMS = 50;
	private final OverlayItem[] _items = new OverlayItem[MAX_ITEMS];
	private final Context _context;
	private boolean _isFull = false;
	private int _currIndex = 0;
	private OverlayItem _previousOverlay;

	public UserMapOverlay(Context context, Drawable defaultMarker) {
		super(defaultMarker);
		_context = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return _items[i];
	}

	@Override
	public int size() {
		if (_isFull) {
			return _items.length;
		}
		return _currIndex;
	}

	public void addOverlay(OverlayItem overlay) {
		if (_previousOverlay != null) {
			if (_currIndex < MAX_ITEMS) {
				_items[_currIndex] = _previousOverlay;
			} else {
				_currIndex = 0;
				_isFull = true;
				_items[_currIndex] = _previousOverlay;
			}
			_currIndex++;
			populate();
		}
		_previousOverlay = overlay;
	}
}
