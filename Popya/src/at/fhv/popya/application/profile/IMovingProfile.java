package at.fhv.popya.application.profile;

import java.util.List;

import at.fhv.popya.application.model.impl.Location;

/**
 * Interface for all moving profiles.
 * 
 * @author Michael
 * @version 1.0
 */
public interface IMovingProfile {

	public static final int MOVEMENT_DISTANCE = 50;
	public static final int MOVEMENT_SWITCH_TIME = 5;

	/**
	 * Check the current state.
	 * 
	 * @param locations
	 *            The locations which where used to check the state
	 * @return The new moving state
	 */
	public IMovingProfile checkState(List<Location> locations);

	/**
	 * Get the speed in km/h.
	 * 
	 * @return The speed in km/h
	 */
	public int getSpeed();

}