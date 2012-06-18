package at.fhv.popya.application.service;

import java.net.URI;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Exception for all types of errors concerning the user.
 * 
 * @author Michael
 * 
 */
public class UserException extends WebApplicationException {
	private static final long serialVersionUID = 1L;

	public UserException() {
		this(null, null);
	}

	public UserException(URI location) {
		this(location, null);
	}

	public UserException(URI location, Object entity) {
		super(Response.status(Status.CONFLICT).location(location)
				.entity(entity).build());
	}
}
