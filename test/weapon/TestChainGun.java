package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Adam Pine
 */
public class TestChainGun {

	/**
	 * Test the chaingun at multiple different ranges.
	 */
	@Test
	public void testChaingunAt0() {
		GenericWeapon cg = new ChainGun();
		assertEquals(0, cg.calcDmg(0));
	}

	@Test
	public void testChaingunAt1() {
		GenericWeapon cg = new ChainGun();
		assertEquals(0, cg.calcDmg(1));
	}

	@Test
	public void testChaingunAt2() {
		GenericWeapon cg = new ChainGun();
		assertEquals(1, cg.calcDmg(2));
	}

	@Test
	public void testChaingunAt5() {
		GenericWeapon cg = new ChainGun();
		assertEquals(2, cg.calcDmg(5));
	}

	@Test
	public void testChaingunAt10() {
		GenericWeapon cg = new ChainGun();
		assertEquals(5, cg.calcDmg(10));
	}

	@Test
	public void testChaingunAt15() {
		GenericWeapon cg = new ChainGun();
		assertEquals(7, cg.calcDmg(15));
	}

	@Test
	public void testChaingunAt20() {
		GenericWeapon cg = new ChainGun();
		assertEquals(10, cg.calcDmg(20));
	}

	@Test
	public void testChaingunAt25() {
		GenericWeapon cg = new ChainGun();
		assertEquals(12, cg.calcDmg(25));
	}

	@Test
	public void testChaingunAt30() {
		GenericWeapon cg = new ChainGun();
		assertEquals(15, cg.calcDmg(30));
	}

	@Test
	public void testChaingunAt31() {
		GenericWeapon cg = new ChainGun();
		assertEquals(0, cg.calcDmg(31));
	}
	// End testing of chaingun at different ranges.

	/**
	 * Test the chaingun's ammo calculations
	 */
	@Test
	public void testChaingunAmmoAndReload() {
		GenericWeapon cg = new ChainGun();
		assertEquals(40, cg.getCurrentAmmo());
		cg.calcDmg(5);
		assertEquals(39, cg.getCurrentAmmo());
		cg.calcDmg(5);
		cg.calcDmg(5);
		assertEquals(37, cg.getCurrentAmmo());
		cg.calcDmg(35);
		assertEquals(36, cg.getCurrentAmmo());
	}
}
