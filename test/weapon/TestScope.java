package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Alex Fennen
 *
 */
public class TestScope {

	/**
	 * Test the functionality of all weapons that have the scope as the first
	 * attachment
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testScopeOnly() throws TooManyAttachmentsException {
		// The weapon used for testing
		Pistol p = new Pistol();
		assertEquals(10, p.calcDmg(5));

		// The attachment that wraps the pistol
		Attachment s = new Scope(p);
		assertEquals(18, s.calcDmg(5));

		Pistol p2 = new Pistol();
		assertEquals(2, p2.calcDmg(25));
		Attachment s2 = new Scope(p2);
		assertEquals(7, s2.calcDmg(27));
	}

	@Test
	public void testTwoScopes() throws TooManyAttachmentsException {
		Pistol p = new Pistol();
		Attachment s1 = new Scope(p);
		s1 = new Scope(s1);
		assertEquals(33, s1.calcDmg(5));
	}

	@Test
	public void testScopeStabilizer() throws TooManyAttachmentsException {
		Pistol p = new Pistol();
		Attachment s1 = new Stabilizer(p);
		s1 = new Scope(s1);
		assertEquals(22, s1.calcDmg(5));
	}

	@Test
	public void testScopePB() throws TooManyAttachmentsException {
		Pistol p = new Pistol();
		Attachment s1 = new PowerBooster(p);
		s1 = new Scope(s1);
		assertEquals(35, s1.calcDmg(5));
	}
}
