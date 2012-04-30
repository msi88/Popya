package at.fhv.popya.application.location;
import at.fhv.popya.application.profile.IMovingProfile;
import at.fhv.popya.application.profile.impl.NonMovingProfile;

/**
 * The location controller is responsible for getting the current user location.
 * 
 * @author Michael
 * @version 1.0
 */
public class LocationController {

	private final IMovingProfile _profile;

	/**
	 * Default constructor.
	 */
	public LocationController() {
		_profile = new NonMovingProfile();
	}

	/**
	 * Get the current moving profile.
	 * 
	 * @return The current moving profile
	 */
	public IMovingProfile getProfile() {
		return _profile;
	}
}