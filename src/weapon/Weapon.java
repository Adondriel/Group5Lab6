package weapon;

/**
 * @author Adam Pine
 */
import gameplay.TimeObserver;

public interface Weapon extends TimeObserver {
	/**
	 * Calc the damage that the weapon will do.
	 * 
	 * @param distance
	 * @return the amount of damage that should be done.
	 */
	public abstract int calcDmg(int distance);

	/**
	 * @return baseDamage
	 */
	public abstract int getBaseDamage();

	/**
	 * @return maxRange
	 */
	public abstract int getMaxRange();

	/**
	 * @return Rate of Fire
	 */
	public abstract int getROF();

	/**
	 * @return maxAmmo
	 */
	public abstract int getMaxAmmo();

	/**
	 * @return currentAmmo
	 */
	public abstract int getCurrentAmmo();

	/**
	 * @return shotsFired
	 */
	public abstract int getShotsFired();

	/**
	 * used to reload the weapon.
	 */
	public abstract void reload();
}
