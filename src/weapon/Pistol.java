package weapon;

/**
 * 
 * @author Adam Pine
 *
 */
public class Pistol extends GenericWeapon {
	/**
	 * Constructor, to construct the pistol.
	 */
	public Pistol() {
		setBaseDamage(10);
		setMaxRange(25);
		setROF(2);
		setMaxAmmo(10);
		setCurrentAmmo(getMaxAmmo());
	}

	/**
	 * The calcdmg override to determine the damage that the pistol is supposed
	 * to do.
	 */
	@Override
	public int calcDmg(int distance) {
		if (!checkBeforeShoot()) {
			return 0;
		}
		shoot();
		if (distance > getMaxRange()) {
			return 0;
		}
		double toppart = maxRange - distance + 5;
		double division = toppart / maxRange;
		double math = (baseDamage * division);
		return (int) math;
	}
}
