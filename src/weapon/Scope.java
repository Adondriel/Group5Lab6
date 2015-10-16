package weapon;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Alex Fennen
 *
 */
public class Scope extends Attachment {
	int ampedMaxRange;

	/**
	 * @param w
	 *            the weapon
	 * @throws TooManyAttachmentsException
	 */
	public Scope(Weapon w) throws TooManyAttachmentsException {
		super(w);
	}

	@Override
	public int calcDmg(int distance) {
		ampedMaxRange = baseWeapon.getMaxRange() + 10;
		if (baseWeapon.getMaxRange() < distance && distance <= ampedMaxRange) {
			return calcDmg(baseWeapon.getMaxRange()) + 5;
		} else if (this.baseWeapon.getMaxRange() >= distance) {
			double baseDamage = baseWeapon.calcDmg(distance);
			double numerator = ampedMaxRange - distance;
			double denominator = ampedMaxRange;
			double rangeFraction = numerator / denominator;
			double scopeDamage = baseDamage * (1.0 + rangeFraction);
			return (int) scopeDamage;
		} else {
			return 0;
		}
	}
}
