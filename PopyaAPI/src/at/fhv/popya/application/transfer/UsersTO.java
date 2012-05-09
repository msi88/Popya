package at.fhv.popya.application.transfer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsersTO {
	private List<UserTO> _users;

	/**
	 * Default constructor.
	 */
	public UsersTO() {
		_users = new ArrayList<UserTO>();
	}

	public List<UserTO> getUsers() {
		return _users;
	}

	@XmlElement(name = "Items")
	public void setUsers(List<UserTO> users) {
		_users = users;
	}

}
