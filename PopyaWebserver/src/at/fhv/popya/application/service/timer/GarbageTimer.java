package at.fhv.popya.application.service.timer;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import at.fhv.popya.application.transfer.MessageTO;
import at.fhv.popya.application.transfer.UserTO;

/**
 * 
 * The GarbageTimer class is responsible for cleaning the messages list for
 * users which are offline.
 * 
 * @author Michael Sieber
 */
public class GarbageTimer extends TimerTask {
	private final int CLEAN_INTERVAL_SEC = 5 * 60;
	private final Map<UserTO, List<MessageTO<Object>>> _clean;

	/**
	 * Create a new GarbageTimer
	 * 
	 * @param messagesToClean
	 *            The list which should be cleaned
	 */
	public GarbageTimer(Map<UserTO, List<MessageTO<Object>>> messagesToClean) {
		_clean = messagesToClean;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
