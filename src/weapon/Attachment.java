package weapon;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Adam Pine, Alex Fennen
 */
public abstract class Attachment implements Weapon {
	protected Weapon baseWeapon;
	private int attachmentCount = 0;

	public Attachment(Weapon w) throws TooManyAttachmentsException {
		if (attachmentCount < 2) {
			baseWeapon = w;
			attachmentCount++;
		} else {
			throw new TooManyAttachmentsException("TOO MANY ATTACHMENTS.");
		}

	}

	/**
	 * call the baseWeapon's updateTime().
	 */
	@Override
	public void updateTime(int time) {
		baseWeapon.updateTime(time);
	}

	/**
	 * @return baseWeapon's time.
	 */
	@Override
	public int getMyTime() {
		return baseWeapon.getMyTime();
	}

	/**
	 * @return baseWeapon's calcDmg() method..
	 */
	@Override
	public int calcDmg(int distance) {
		return baseWeapon.calcDmg(distance);
	}

	/**
	 * @return baseWeapon's MaxAmmo.
	 */
	@Override
	public int getMaxAmmo() {
		return baseWeapon.getMaxAmmo();
	}

	/**
	 * @return baseWeapon's MaxRange.
	 */
	@Override
	public int getMaxRange() {
		return baseWeapon.getMaxRange();
	}

	/**
	 * @return baseWeapon's CurrentAmmo.
	 */
	@Override
	public int getCurrentAmmo() {
		return baseWeapon.getCurrentAmmo();
	}

	/**
	 * reload the baseWeapon.
	 */
	@Override
	public void reload() {
		baseWeapon.reload();
	}

	/**
	 * @return baseWeapon's BaseDamage.
	 */
	@Override
	public int getBaseDamage() {
		// TODO Auto-generated method stub
		return baseWeapon.getBaseDamage();
	}

	/**
	 * @return the baseWeapon's rate of fire.
	 */
	@Override
	public int getROF() {
		// TODO Auto-generated method stub
		return baseWeapon.getROF();
	}

	/**
	 * @return baseWeapon's shotsFired variable.
	 */
	@Override
	public int getShotsFired() {
		// TODO Auto-generated method stub
		return baseWeapon.getShotsFired();
	}

}
