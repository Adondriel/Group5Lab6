package weapon;

/**
 * @author Adam Pine
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class TestPistol {
	/**
	 * Test pistol damage at different ranges
	 */
	@Test
	public void testPistolDamageAt0() {
		GenericWeapon p = new Pistol();
		assertEquals(12, p.calcDmg(0));
	}

	@Test
	public void testPistolDamageAt1() {
		GenericWeapon p = new Pistol();
		assertEquals(11, p.calcDmg(1));
	}

	@Test
	public void testPistolDamageAt5() {
		GenericWeapon p = new Pistol();
		assertEquals(10, p.calcDmg(5));
	}

	@Test
	public void testPistolDamageAt15() {
		GenericWeapon p = new Pistol();
		assertEquals(6, p.calcDmg(15));
	}

	@Test
	public void testPistolDamageAt25() {
		GenericWeapon p = new Pistol();
		assertEquals(2, p.calcDmg(25));
	}
	// End testing of pistol damage.

	/**
	 * Test when the target is too far away. Make sure it updates ammo as well.
	 */
	@Test
	public void testPistolDamageAt30() {
		GenericWeapon p = new Pistol();
		assertEquals(10, p.getCurrentAmmo());
		assertEquals(0, p.calcDmg(30));
		assertEquals(9, p.getCurrentAmmo());
	}

	/**
	 * Check that ammo is updated when calcDmg is called.
	 */
	@Test
	public void testPistolAmmo() {
		GenericWeapon p = new Pistol();
		assertEquals(10, p.getCurrentAmmo());
		// make sure it removed ammo when shot.
		p.calcDmg(5);
		assertEquals(9, p.getCurrentAmmo());
		// Make sure it removes ammo when shot too far away.
		p.calcDmg(30);
		assertEquals(8, p.getCurrentAmmo());
	}

	/**
	 * Test the ROF causing the weapon to not fire, until the time is updated.
	 * Test that the ammo is updated when reload is called.
	 */
	@Test
	public void testPistolReloadandROF() {
		GenericWeapon p = new Pistol();
		assertEquals(10, p.getCurrentAmmo());
		p.calcDmg(5);
		assertEquals(9, p.getCurrentAmmo());
		p.calcDmg(5);
		p.calcDmg(5);
		assertEquals(8, p.getCurrentAmmo());
		p.calcDmg(5);
		p.calcDmg(5);
		assertEquals(8, p.getCurrentAmmo());
		p.reload();
		assertEquals(10, p.getCurrentAmmo());
		p.calcDmg(5);
		assertEquals(10, p.getCurrentAmmo());
		p.updateTime(1);
		p.calcDmg(5);
		assertEquals(9, p.getCurrentAmmo());
	}
}
