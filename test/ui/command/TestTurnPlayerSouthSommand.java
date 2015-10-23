package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestTurnPlayerSouthSommand
{

	/**
	 * Test that the LifeForm turns south on command
	 */
	@Test
	public void testTurnLifeFormSouth()
	{
		LifeForm bob = new MockLifeForm("bob", 23);
		South south = new South();
		south.execute(bob);
		assertEquals('S', bob.getDirection());
	}

}
