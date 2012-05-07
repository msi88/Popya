package at.fhv.popya.application.profile.impl;

import java.util.List;

import at.fhv.popya.application.model.Location;
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

	@Override
	public IMovingProfile checkState(List<Location> locations) {
		return null;
	}

	@Override
	public int getSpeed() {
		return 0;
	}

}