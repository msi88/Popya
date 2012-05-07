package at.fhv.popya.application.profile.impl;

import java.util.List;

import android.location.Location;
import at.fhv.popya.application.profile.IMovingProfile;

/**
 * The NonMovingProfile.
 * 
 * @author Michael
 * @version 1.0
 */
public class NonMovingProfile implements IMovingProfile {

	/**
	 * Default constructor.
	 */
	public NonMovingProfile() {

	}

	public IMovingProfile checkState(List<Location> locations) {
		return null;
	}

	public int getSpeed() {
		return 0;
	}

}