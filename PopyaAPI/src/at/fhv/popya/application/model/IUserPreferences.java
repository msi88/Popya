package at.fhv.popya.application.model;

public interface IUserPreferences {
	/**
	 * Get the maximum broadcast distance in meters.
	 * 
	 * @return The maximum broadcast distance in meters
	 */
	public int getMaxBroadcastDistance();

	/**
	 * Get the maximum receiving distance in meters.
	 * 
	 * @return The maximum receiving distance in meters
	 */
	public int getMaxReceiveDistance();

	/**
	 * Get the server address.
	 * 
	 * @return The server address
	 */
	public String getServerAddress();

	/**
	 * Get the message update intervall.
	 * 
	 * @return The intervall in seconds in which the server should be asked for
	 *         new messages
	 */
	public int getUpdateIntervall();
}
