package weapon;

/**
 * @author Adam Pine
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlasmaCannon {
	/**
	 * This weapon does not care about distance, other than max range. This
	 * weapon's damage depends on the current ammo of the gun. This is the
	 * single test needed to ensure it calculates damage and reloads properly.
	 */
	@Test
	public void testPlasmaCannon() {
		// Test 3 shots, 1 shot per round.
		PlasmaCannon pc = new PlasmaCannon();
		int dmg1 = pc.calcDmg(5);
		pc.updateTime(1);
		int dmg2 = pc.calcDmg(5);
		pc.updateTime(2);
		int dmg3 = pc.calcDmg(5);
		pc.updateTime(3);
		assertEquals(50, dmg1);
		assertEquals(37, dmg2);
		assertEquals(25, dmg3);
		// Test reloading
		pc.reload();
		assertEquals(4, pc.getCurrentAmmo());
		pc.updateTime(4);
		// Test damage after reloading.
		assertEquals(50, pc.calcDmg(5));
		assertEquals(0, pc.calcDmg(5));
		assertEquals(3, pc.getCurrentAmmo());
		// test dmg at max range
		pc.reload();
		pc.updateTime(5);
		assertEquals(50, pc.calcDmg(20));
		// test dmg above max range
		pc.reload();
		pc.updateTime(6);
		assertEquals(0, pc.calcDmg(21));

	}

}
