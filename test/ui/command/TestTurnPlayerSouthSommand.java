package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.Human;

public class TestTurnPlayerSouthSommand
{
	private char south='s';

	/**
	 * Test that the LifeForm turns south on command
	 */
	@Test
	public void testTurnLifeFormSouth()
	{
		Human bob = new Human("bob", 23, 0);
		South s = new South();
		s.execute(bob);
		assertEquals(south, bob.getDirection());
	}

}
