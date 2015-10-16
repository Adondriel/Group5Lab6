/**
 * @author Adam Pine
 * The alien class, subclass to the LifeForm. Holds information and functions for an alien.
 */
package lifeform;

import Exceptions.RecovRateIsNegative;
import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

public class Alien extends LifeForm {
	private int myTime;
	private RecoveryBehavior recoveryBehavior;
	private int maxLifePoints = 30;
	private int recoveryRate = 2;

	/**
	 * @return recoveryRate of the Alien.
	 */
	public int getRecoveryRate() {
		return recoveryRate;
	}

	/**
	 * Sets alien's recoveryRate.
	 * 
	 * @param recoveryRate
	 */
	public void setRecoveryRate(int recoveryRate) {
		this.recoveryRate = recoveryRate;
	}

	/**
	 * Constructor that will default to a recoveryNone RB, defaults recov rate
	 * to 1.
	 * 
	 * @param name
	 *            string, name of the alien
	 * @param points
	 *            int, amount of hp the alien will have.
	 * @throws RecovRateIsNegative
	 */
	public Alien(String name, int points) throws RecovRateIsNegative {
		this(name, points, 1, new RecoveryNone());
		setAttackDmg(10);
	}

	/**
	 * Constructor, with a RecoveryBehavior.
	 * 
	 * @param name
	 *            string, holds the alien's name
	 * @param points
	 *            how much HP it should have.
	 * @param recovRate
	 *            How many ticks/rounds before the recover function will be
	 *            called.
	 * @param rb
	 *            The recovery behavior. if recovRate <0 throws error, if = 0
	 *            does nothing. This is checked later on aswell in the
	 *            updateTime method.
	 */
	public Alien(String name, int points, int recovRate, RecoveryBehavior rb) throws RecovRateIsNegative {
		super(name, points);
		recoveryBehavior = rb;
		setAttackDmg(10);
		if (recovRate < 0) {
			throw new RecovRateIsNegative("You tried to initialize the recovery rate or below 0.");
		} else {
			setRecoveryRate(recovRate);
		}

	}

	/**
	 * The recover function, that can be called to have the alien recover
	 * health.
	 */
	public void recover() {
		setCurrentLifePoints(recoveryBehavior.calculateRecovery(currentLifePoints, maxLifePoints));
	}

	/**
	 * Set's the current HP value.
	 * 
	 * @param life
	 *            what the life should be set to.
	 */
	public void setCurrentLifePoints(int life) {
		currentLifePoints = life;
	}

	@Override
	public void updateTime(int time) {
		myTime = time;
		if (recoveryRate > 0) {
			if (time % recoveryRate == 0) {
				recover();
			}
		}
	}
}
