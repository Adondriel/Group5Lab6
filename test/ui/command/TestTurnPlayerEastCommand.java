package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestTurnPlayerEastCommand
{

	/**
	 * Test that the LifeForm turns east on command
	 */
	@Test
	public void testTurnLifeFormEast()
	{
		LifeForm bob = new MockLifeForm("bob", 23);
		East east = new East();
		east.execute(bob);
		assertEquals('E', bob.getDirection());
	}

}
