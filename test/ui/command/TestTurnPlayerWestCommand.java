package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class TestTurnPlayerWestCommand
{

	
	/**
	 * Test that the LifeForm turns west on command
	 */
	@Test
	public void testTurnLifeFormWest()
	{
		LifeForm bob = new MockLifeForm("bob", 23);
		West west = new West();
		west.execute(bob);
		assertEquals('w', bob.getDirection());
	}

}
