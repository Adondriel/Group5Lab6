/**
 * @author Adam Pine
 * Recovery none, do no recovery.
 */
package recovery;

public class RecoveryNone implements RecoveryBehavior {
	/**
	 * There is nothing to init so just leave it blank.
	 */
	public RecoveryNone() {
	}

	/**
	 * There is no calc required, just return the currentLife.
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife) {
		return currentLife;
	}

}
