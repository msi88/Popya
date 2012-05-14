package at.fhv.popya.application.transfer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Represents the current location.
 * 
 * @author Michael Sieber
 */
@XmlRootElement
public class LocationTO {
	private double _longitude;
	private double _latitude;
	private double _altitude;

	/**
	 * Default constructor.
	 */
	public LocationTO() {

	}

	public double getLongitude() {
		return _longitude;
	}

	@XmlElement(name = "Longitude")
	public void setLongitude(double longitude) {
		_longitude = longitude;
	}

	public double getLatitude() {
		return _latitude;
	}

	@XmlElement(name = "Latitude")
	public void setLatitude(double latitude) {
		_latitude = latitude;
	}

	public double getAltitude() {
		return _altitude;
	}

	@XmlElement(name = "Altitude")
	public void setAltitude(double altitude) {
		_altitude = altitude;
	}

}
