package weapon;

/**
 * 
 * @author Adam Pine
 *
 */
public abstract class GenericWeapon implements Weapon {
	private int myTime;
	protected int baseDamage;
	protected int maxRange;
	protected int ROF;
	protected int maxAmmo;
	protected int currentAmmo;
	protected int shotsFired = 0;

	/**
	 * Updates the time and resets the amount of shotsFired for that round.
	 */
	@Override
	public void updateTime(int time) {
		myTime = time;
		shotsFired = 0;
	}

	/**
	 * The default calcDmg() method, will return just the baseDamage of the
	 * weapon.
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
		return baseDamage;
	}

	/**
	 * Will reload the weapon, returning it to maxAmmo.
	 */
	public void reload() {
		currentAmmo = maxAmmo;
	}

	/**
	 * Provides some common thigns that happen when shooting, does not actually
	 * shoot anything. This updates diff variables which are common occurences
	 * when shooting stuff.
	 */
	protected void shoot() {
		setCurrentAmmo(getCurrentAmmo() - 1);
		shotsFired++;
	}

	/**
	 * Checks some common conditions before shooting the target.
	 * 
	 * @return
	 */
	protected boolean checkBeforeShoot() {
		if (shotsFired >= ROF) {
			return false;
		} else if (currentAmmo <= 0) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * The getters and setters of the genericWeapon class.
	 */
	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}

	public int getShotsFired() {
		return shotsFired;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public void setROF(int rOF) {
		ROF = rOF;
	}

	public void setMaxAmmo(int maxAmmo) {
		this.maxAmmo = maxAmmo;
	}

	public void setCurrentAmmo(int currentAmmo) {
		this.currentAmmo = currentAmmo;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public int getROF() {
		return ROF;
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public int getCurrentAmmo() {
		return currentAmmo;
	}

	public int getMyTime() {
		return myTime;
	}

}
