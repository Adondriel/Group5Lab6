package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.Human;

public class TestTurnPlayerEastCommand
{
	private char east='e';

	/**
	 * Test that the LifeForm turns east on command
	 */
	@Test
	public void testTurnLifeFormEast()
	{
		Human h = new Human("bob", 23, 0);
		East e = new East();
		e.execute(h);
		assertEquals(east, h.getDirection());
	}

}
