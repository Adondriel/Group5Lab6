package weapon;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Alex Fennen
 *
 */
public class Stabilizer extends Attachment {
	/**
	 * @param w
	 *            the weapon
	 * @throws TooManyAttachmentsException
	 */
	public Stabilizer(Weapon w) throws TooManyAttachmentsException {
		super(w);
	}

	@Override
	public int calcDmg(int distance) {
		double base = baseWeapon.calcDmg(5);
		double newDmg = base * 1.25;
		if (baseWeapon.getCurrentAmmo() == 0) {
			baseWeapon.reload();
		}
		return (int) newDmg;

	}
}
