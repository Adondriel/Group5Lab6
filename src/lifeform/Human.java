/**
 * @author Adam Pine, Benjamin Uleau
 * The Human class, holds information and functions for the human.
 */
package lifeform;

public class Human extends LifeForm {
	private int armorPoints;
	private int myTime;

	/**
	 * Contructor for human, initializes the human
	 * 
	 * @param name
	 * @param points
	 * @param armor
	 */
	public Human(String name, int points, int armor) {
		super(name, points);
		setArmorPoints(armor);
		setAttackDmg(5);
		maxSpeed=3;
	}

	/**
	 * @return int, armorPoints for the instantiated human.
	 */
	public int getArmorPoints() {
		return armorPoints;
	}

	/**
	 * Sets the armor value for the instantiated human.
	 * 
	 * @param armor
	 *            Int
	 */
	public void setArmorPoints(int armor) {
		if (armor < 0) {
			armor = 0;
		}
		armorPoints = armor;
	}

	@Override
	public void takeHit(int dmg) {
		if (dmg > armorPoints) {
			int math = currentLifePoints - (dmg - armorPoints);
			if (math < 0) {
				math = 0;
			}
			currentLifePoints = math;
		}
	}

	@Override
	public void updateTime(int time) {
		myTime = time;
		currentMoves=0;
	}
	
	/**
	 * turn the lifeform left
	 */
	public void turnRight(){
		super.turnRight();
	}
	
	/**
	 * turn the lifeform right
	 */
	public void turnLeft(){
		super.turnLeft();
	}
	
	/**
	 * turn the lifeform around
	 */
	public void turnAround(){
		super.turnAround();
	}
	
	/**
	 * @return the direction the lifeform is currently facing
	 */
	public char getDirection(){
		return currentDirection;
	}
}
