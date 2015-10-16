package weapon;

/**
 * @author Adam Pine
 */
import static org.junit.Assert.*;

import org.junit.Test;

import gameplay.SimpleTimer;

class MockGW extends GenericWeapon {
	public MockGW() {
		setBaseDamage(1);
		setMaxRange(20);
		setROF(3);
		setMaxAmmo(50);
		setCurrentAmmo(getMaxAmmo());
	}
}

public class TestGenericWeapon {

	/**
	 * Test the genericWeapon, and be sure that it observes time correctly.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testGWFireRate() throws InterruptedException {
		SimpleTimer st = new SimpleTimer(1000);
		GenericWeapon gw = new MockGW();
		st.addTimeObserver(gw);
		st.start();
		Thread.sleep(250);
		assertNotEquals(0, gw.calcDmg(5));
		assertNotEquals(0, gw.calcDmg(5));
		assertNotEquals(0, gw.calcDmg(5));
		assertEquals(0, gw.calcDmg(5));
		assertEquals(3, gw.getShotsFired());
		Thread.sleep(1000);
		// This calls the updateTime method, which will reset the amount of
		// shotsFired.
		// Some weapons may also need to reload at this time, so be sure to call
		// that if your weapon does not have enough ammo.
		assertEquals(0, gw.getShotsFired());
		assertNotEquals(0, gw.calcDmg(5));
		assertNotEquals(0, gw.calcDmg(5));
		assertNotEquals(0, gw.calcDmg(5));
	}

}
