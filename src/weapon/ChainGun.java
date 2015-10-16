package weapon;

/**
 * 
 * @author Adam Pine
 *
 */
public class ChainGun extends GenericWeapon {
	/**
	 * Constructor, to initialize the values of the different variables.
	 */
	public ChainGun() {
		setBaseDamage(15);
		setMaxRange(30);
		setROF(4);
		setMaxAmmo(40);
		setCurrentAmmo(getMaxAmmo());
	}

	/**
	 * The calcDmg() override, specific to the chaingun.
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
		double division = (double) distance / (double) maxRange;
		double math = baseDamage * division;
		return (int) math;
	}
}
