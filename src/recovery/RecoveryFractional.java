/**
 * @author Adam Pine
 * Recovery Fractional class, implements the RecoveryBehavior.
 * it will return the amount of HP that the LifeForm should have,
 * based on the percentRecovery set when created.
 * Uses direct multiplication, 1 = no change, .5 = lose 50% hp each time, and 1.5 = gain 50% each time.
 */
package recovery;

public class RecoveryFractional implements RecoveryBehavior {
	private double percentRecovery;

	/**
	 * 
	 * @param percent
	 *            double, multiplied by current life. Values less than 1 will
	 *            subtract life, values over will add life. 1.5 = 50% recovery.
	 */
	public RecoveryFractional(double percent) {
		percentRecovery = percent;
	}

	/**
	 * @param currentLife
	 *            The amount of life the lifeform currently has.
	 * @param maxLife
	 *            The max amount of life the lifeform can have.
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife) {
		double rVal;
		if (currentLife <= 0) {
			return 0;
		}
		rVal = currentLife * percentRecovery;
		if (rVal > maxLife) {
			rVal = maxLife;
		}
		return (int) rVal;
	}

}
