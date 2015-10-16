package weapon;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Alex Fennen
 *
 */
public class PowerBooster extends Attachment {
	/**
	 * @param w
	 *            the weapon
	 * @throws TooManyAttachmentsException
	 */
	public PowerBooster(Weapon w) throws TooManyAttachmentsException {
		super(w);
	}

	@Override
	public int calcDmg(int distance) {
		double dmg = baseWeapon.calcDmg(distance);

		double DmgModifier = (1.0 + ((double) baseWeapon.getCurrentAmmo() / (double) baseWeapon.getMaxAmmo()));
		double newDmg = dmg * DmgModifier;
		return (int) newDmg;
	}
}
