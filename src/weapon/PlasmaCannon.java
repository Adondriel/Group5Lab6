package weapon;

/**
 * 
 * @author Adam Pine
 *
 */
public class PlasmaCannon extends GenericWeapon {
	/**
	 * The constructor for plasmacannon, sets up the variables for itself.
	 */
	public PlasmaCannon() {
		setBaseDamage(50);
		setMaxRange(20);
		setROF(1);
		setMaxAmmo(4);
		setCurrentAmmo(getMaxAmmo());
	}

	/**
	 * The calcdmg() override to determine how much damage the plasmacannon will
	 * do. distance only effects if it can or cannot actually hit the target,
	 * and does not change the amount of damage it does. Damage depends on the
	 * amount of ammo still in the gun.
	 */
	@Override
	public int calcDmg(int distance) {
		if (!checkBeforeShoot()) {
			return 0;
		}

		if (distance > getMaxRange()) {
			shoot();
			return 0;
		}
		double division = (double) currentAmmo / (double) maxAmmo;
		double test = baseDamage * division;
		shoot();
		return (int) test;
	}
}
