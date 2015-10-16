package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Alex Fennen
 *
 */
public class TestPowerBooster {
	/**
	 * Test weapons with power booster as the first attachment.
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testBoosterOnly() throws TooManyAttachmentsException {
		ChainGun p = new ChainGun();
		Attachment a = new PowerBooster(p);
		assertEquals(3, a.calcDmg(5));
	}

	/**
	 * Tests a booster wrapped around a scope, wrapped around a chaingun
	 * 
	 * @throws TooManyAttachmentsException
	 * 
	 */
	@Test
	public void testBoosterScope() throws TooManyAttachmentsException {
		ChainGun p = new ChainGun();
		Attachment a = new Scope(p);
		a = new PowerBooster(a);
		assertEquals(5, a.calcDmg(5));
	}

	/**
	 * Tests a booster wrapped around a stabilizer, wrapped around a chaingun
	 * 
	 * @throws TooManyAttachmentsException
	 * 
	 */
	@Test
	public void testBoosterStabil() throws TooManyAttachmentsException {
		ChainGun p = new ChainGun();
		Attachment a = new Stabilizer(p);
		a = new PowerBooster(a);
		assertEquals(3, a.calcDmg(5));
	}

	/**
	 * Tests a booster wrapped around a booster, wrapped around a chaingun
	 * 
	 * @throws TooManyAttachmentsException
	 * 
	 */
	@Test
	public void testTwoBoosters() throws TooManyAttachmentsException {
		ChainGun p = new ChainGun();
		Attachment a = new PowerBooster(p);
		a = new PowerBooster(a);
		assertEquals(5, a.calcDmg(5));
	}

}
