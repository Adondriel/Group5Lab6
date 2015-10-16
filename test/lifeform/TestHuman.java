/**
 * @author Adam Pine
 * Tests for the human, tests initialization and armor setting/getting along with armor value validation.
 */
package lifeform;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.RecovRateIsNegative;
import recovery.RecoveryLinear;

public class TestHuman {
	@Test
	public void testHumanDefaultAttackStr() {
		Human h = new Human("Steve", 30, 10);
		assertEquals(5, h.getAttackDmg());
	}

	/**
	 * Tests the takehit method of Human, when the DMG = Armor
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testHumanDmgLessThanArmor() throws RecovRateIsNegative {
		Human h = new Human("Steve", 30, 10);
		h.takeHit(5);
		assertEquals(30, h.getCurrentLifePoints());
	}

	/**
	 * Tests the takeHit method of the human, when dmg > armor
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testHumanDmgGreaterThanArmor() throws RecovRateIsNegative {
		Human h = new Human("Steve", 30, 5);
		h.takeHit(10);
		assertEquals(25, h.getCurrentLifePoints());
	}

	/**
	 * Tests the takehit method of Human, when the DMG = Armor
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testHumanDmgEqualArmor() throws RecovRateIsNegative {
		Human h = new Human("Steve", 30, 10);
		h.takeHit(10);
		assertEquals(30, h.getCurrentLifePoints());
	}

	/**
	 * Tests the takeHit method of the human, using an alien to do the
	 * attacking.
	 * 
	 * @throws RecovRateIsNegative
	 *             EXTRA TEST
	 */
	@Test
	public void testHumanNoArmor() throws RecovRateIsNegative {
		Human h = new Human("Steve", 30, 0);
		h.takeHit(10);
		assertEquals(20, h.getCurrentLifePoints());
	}

	// !!!!
	// STRATEGY TESTS START HERE
	// !!!!
	@Test
	public void testHumanInit() {
		// Test init.
		Human h = new Human("Bob", 30, 20);
		assertEquals(20, h.getArmorPoints());
	}

	@Test
	public void testHumanSetArmor() {
		Human h = new Human("Steve", 30, -50);
		// test the setting of armor, even though the init does this for us.
		h.setArmorPoints(20);
		assertEquals(20, h.getArmorPoints());
	}

	@Test
	public void testHumanArmorLessThanZero() {
		// Test armor <0
		Human h = new Human("Steve", 30, -50);
		assertEquals(0, h.getArmorPoints());
	}

	@Test
	public void testHumanArmorEqualToZero() {
		// Test armor =0
		Human h = new Human("Steve", 30, 0);
		assertEquals(0, h.getArmorPoints());
	}

}
