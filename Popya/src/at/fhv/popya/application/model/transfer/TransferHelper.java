package at.fhv.popya.application.model.transfer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import at.fhv.popya.application.model.User;
import at.fhv.popya.application.model.UserPreferences;
import at.fhv.popya.application.transfer.UserTO;

/**
 * 
 * Helper class for transfer objects
 * 
 * @author Michael Sieber
 */
public class TransferHelper {

	/**
	 * Get the user from a user transfer object
	 * 
	 * @param transfer
	 *            The user transfer object
	 * @return The converted user
	 */
	public static User getUser(UserTO transfer) {
		UserPreferences prefs = new UserPreferences(transfer.getPreferences()
				.getMaxBroadcastDistance(), transfer.getPreferences()
				.getMaxReceiveDistance(), transfer.getPreferences()
				.getServerAddress(), transfer.getPreferences()
				.getUpdateIntervall());

		Bitmap b = null;
		if (transfer.getPicture() != null) {
			b = BitmapFactory.decodeByteArray(transfer.getPicture(), 0,
					transfer.getPicture().length);
		}

		User user = new User(transfer.getChatName(), transfer.getDescription(),
				b, prefs);
		user.setCurrentLocation(transfer.getCurrentLocation());

		return user;
	}
}
