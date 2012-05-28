package at.fhv.popya.application.service.helper;

import at.fhv.popya.application.transfer.LocationTO;

/**
 * 
 * The LocationHelper class for location based calculations.
 * 
 * @author Michael Sieber
 */
public class LocationHelper {
	private final static double EARTH_RADIUS_IN_KM = 6.371;

	/**
	 * Calculate the distance of to gps points with the help of the Haversine
	 * formula. <br/>
	 * http://en.wikipedia.org/wiki/Haversine_formula
	 * 
	 * a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlong/2) <br/>
	 * c = 2.atan2(√a, √(1−a)) <br/>
	 * d = R.c
	 * 
	 * @param first
	 *            The first point
	 * @param second
	 *            The seond point
	 * @return The distance between the two points in km.
	 */
	public static double getDistanceInMeters(LocationTO first, LocationTO second) {
		if (first != null && second != null) {
			double dLat = Math.toRadians((first.getLatitude() - second
					.getLatitude()));
			double dLon = Math.toRadians((first.getLongitude() - second
					.getLongitude()));

			double lat1 = Math.toRadians(first.getLatitude());
			double lat2 = Math.toRadians(second.getLatitude());

			double a = (Math.sin(dLat / 2) * Math.sin(dLat / 2))
					+ (Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math
							.cos(lat2));
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

			return EARTH_RADIUS_IN_KM * c;
		}

		return Integer.MAX_VALUE;
	}
}
