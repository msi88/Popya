package at.fhv.popya.application.model.transfer;

/**
 * Interface for all objects which can be transferred.
 * 
 * @author Michael
 * 
 * @param <T>
 *            The type of the transfer object (TO)
 */
public interface ITransferable<T> {

	/**
	 * Get the transfer object.
	 */
	public T getTransferObject();
}
