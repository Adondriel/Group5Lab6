package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.Human;

public class TestTurnPlayerNorthCommand
{
	private char north='n';

	/**
	 * Test that the LifeForm turns north on command
	 */
	@Test
	public void testTurnLifeFormNorth()
	{
		Human bob = new Human("bob", 23, 0);
		North n = new North();
		n.execute(bob);
		assertEquals(north, bob.getDirection());
	}

}
