/**
 * @author Adam Pine
 * Keeps Track of the information associated with a simple life form.
 * Also provides the functionality related to the life form.
 */
package lifeform;

import gameplay.TimeObserver;
import weapon.*;
import Exceptions.EnvironmentException;
import environment.Environment;

public abstract class LifeForm implements TimeObserver {
	private String name;
	protected int currentLifePoints;
	private int attackDmg;
	protected int myTime;
	protected Weapon weapon;
	private int myRow;
	private int myCol;

	/**
	 * @param name
	 *            The name of the life form
	 * @param points
	 *            The current starting life points of the life form
	 */
	public LifeForm(String name, int points) {
		this.name = name;
		currentLifePoints = points;
		setAttackDmg(0);
	}

	/**
	 * @return the name of the lifeform
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the amount of current life points the life form has.
	 */
	public int getCurrentLifePoints() {
		return currentLifePoints;
	}

	/**
	 * Makes the lifeform take damage.
	 * 
	 * @param dmg
	 *            Amount of dmg to subtract.
	 */
	public void takeHit(int dmg) {
		int math = currentLifePoints - dmg;
		if (math < 0) {
			math = 0;
		}
		currentLifePoints = math;
	}

	public void attackLF(LifeForm lf) throws EnvironmentException {
		int range = Environment.getWorldInstance().getDistance(this, lf);
		if (currentLifePoints > 0) {
			if (weapon != null) {
				if (weapon.getCurrentAmmo() > 0) {
					lf.takeHit(weapon.calcDmg(range));
				} else {
					if (range <= 5) {
						lf.takeHit(attackDmg);
					}
				}
			} else {
				if (range <= 5) {
					lf.takeHit(attackDmg);
				}
			}
		}
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	protected void setAttackDmg(int attackDmg) {
		this.attackDmg = attackDmg;
	}

	public void updateTime(int time) {
		myTime = time;
	}

	public int getMyTime() {
		return myTime;
	}

	public void pickupWeapon(GenericWeapon w) {
		if (weapon == null)
			weapon = w;
	}

	public void dropWeapon() {
		weapon = null;
	}

	public int getMyRow() {
		return myRow;
	}

	public void setMyRow(int myRow) {
		this.myRow = myRow;
	}

	public int getMyCol() {
		return myCol;
	}

	public void setMyCol(int myCol) {
		this.myCol = myCol;
	}

}
