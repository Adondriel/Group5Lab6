package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestTurnPlayerNorthCommand
{

	/**
	 * Test that the LifeForm turns north on command
	 */
	@Test
	public void testTurnLifeFormNorth()
	{
		LifeForm bob = new MockLifeForm("bob", 23);
		North north = new North();
		north.execute(bob);
		assertEquals('N', bob.getDirection());
	}

}
