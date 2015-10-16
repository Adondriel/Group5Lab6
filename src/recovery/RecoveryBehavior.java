/**
 * @author Adam Pine
 * The RecoveryBehavior interface, for the other's to use for implementation.
 */
package recovery;

public interface RecoveryBehavior {
	/**
	 * @param currentLife
	 *            The amount of life the lifeform currently has.
	 * @param maxLife
	 *            The max amount of life the lifeform can have.
	 */
	public int calculateRecovery(int currentLife, int maxLife);
}
