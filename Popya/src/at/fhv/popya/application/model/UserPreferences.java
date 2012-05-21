package at.fhv.popya.application.model;

import at.fhv.popya.application.model.transfer.ITransferable;
import at.fhv.popya.application.transfer.UserPreferencesTO;

/**
 * The user preferences.
 * 
 * @author Michael
 * @version 1.0
 */
public class UserPreferences implements ITransferable<UserPreferencesTO> {

	private final int _maxBroadcastDistance;
	private final int _maxReceiveDistance;
	private final String _serverAddress;
	private final int _updateIntervall;

	/**
	 * Create new UserPreferences.
	 * 
	 * @param maxBroadcastDistance
	 *            The maximum distance of users to receive the sended messages
	 *            in meters
	 * @param maxReceiveDistance
	 *            The maximum distance of users which can send messages to me in
	 *            meters
	 * @param serverAddress
	 *            The server address for handling the connection
	 * @param updateIntervall
	 *            The update intervall in seconds in which the server will be
	 *            asked for new messages
	 */
	public UserPreferences(int maxBroadcastDistance, int maxReceiveDistance,
			String serverAddress, int updateIntervall) {
		_maxBroadcastDistance = maxBroadcastDistance;
		_maxReceiveDistance = maxReceiveDistance;
		_serverAddress = serverAddress;
		_updateIntervall = updateIntervall;
	}

	/**
	 * Get the maximum broadcast distance in meters.
	 * 
	 * @return The maximum broadcast distance in meters
	 */
	public int getMaxBroadcastDistance() {
		return _maxBroadcastDistance;
	}

	/**
	 * Get the maximum receiving distance in meters.
	 * 
	 * @return The maximum receiving distance in meters
	 */
	public int getMaxReceiveDistance() {
		return _maxReceiveDistance;
	}

	/**
	 * Get the server address.
	 * 
	 * @return The server address
	 */
	public String getServerAddress() {
		return _serverAddress;
	}

	/**
	 * Get the message update intervall.
	 * 
	 * @return The intervall in seconds in which the server should be asked for
	 *         new messages
	 */
	public int getUpdateIntervall() {
		return _updateIntervall;
	}

	@Override
	public UserPreferencesTO getTransferObject() {

		int i = 10;
		UserPreferencesTO pref = new UserPreferencesTO();

		pref.setMaxBroadcastDistance(100);
		pref.setMaxReceiveDistance(100);
		pref.setServerAddress(this.getServerAddress());
		pref.setUpdateIntervall(this.getUpdateIntervall());
		
		return pref;

		// return new
		// UserPreferencesTO(this.getMaxBroadcastDistance(),this.getMaxReceiveDistance(),this.getServerAddress(),this.getUpdateIntervall());
	}

}