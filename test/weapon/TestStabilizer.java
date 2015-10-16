package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.TooManyAttachmentsException;

/**
 * @author Benjamin Uleau, Alex Fennen
 *
 */
public class TestStabilizer {
	/**
	 * Test weapons with stabilizer as the first attachment
	 * 
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testOnlyStabilizer() throws TooManyAttachmentsException {
		PlasmaCannon p = new PlasmaCannon();
		Attachment a = new Stabilizer(p);
		assertEquals(62, a.calcDmg(5));
	}

	@Test
	public void testStabilScope() throws TooManyAttachmentsException {
		PlasmaCannon p = new PlasmaCannon();
		Attachment a = new Scope(p);
		a = new Stabilizer(a);
		assertEquals(113, a.calcDmg(5));
	}

	@Test
	public void testTwoStabilizers() throws TooManyAttachmentsException {
		PlasmaCannon p = new PlasmaCannon();
		Attachment a = new Stabilizer(p);
		a = new Stabilizer(a);
		assertEquals(77, a.calcDmg(5));
	}

	@Test
	public void testStabilPB() throws TooManyAttachmentsException {
		PlasmaCannon p = new PlasmaCannon();
		Attachment a = new PowerBooster(p);
		a = new Stabilizer(a);
		assertEquals(108, a.calcDmg(5));
	}

}
