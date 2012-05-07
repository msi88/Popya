package at.fhv.popya.application.profile.impl;

import java.util.List;

import android.location.Location;
import at.fhv.popya.application.profile.IMovingProfile;

/**
 * The MovingProfile.
 * 
 * @author Michael
 * @version 1.0
 */
public class MovingProfile implements IMovingProfile {

	/**
	 * Default constructor.
	 */
	public MovingProfile() {

	}

	public IMovingProfile checkState(List<Location> locations) {
		return null;
	}

	/**
	 * Get the movement direction.
	 * 
	 * @return The movement direction
	 */
	public int getDirection() {
		// TODO use enumeration
		return 0;
	}

	public int getSpeed() {
		return 0;
	}

}