/**
 * @author Adam Pine
 * Adds an integer value to the life of the current lifeform.
 */

package recovery;

public class RecoveryLinear implements RecoveryBehavior {
	private int recoveryStep;

	/**
	 * @param step
	 *            how much to add each time we recover.
	 */
	public RecoveryLinear(int step) {
		recoveryStep = step;
	}

	/**
	 * @param currentLife
	 *            The amount of life the lifeform currently has.
	 * @param maxLife
	 *            The max amount of life the lifeform can have.
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife) {
		int rVal;
		if (currentLife <= 0) {
			return 0;
		}
		rVal = currentLife + recoveryStep;
		if (rVal > maxLife) {
			rVal = maxLife;
		}
		return rVal;
	}

}
