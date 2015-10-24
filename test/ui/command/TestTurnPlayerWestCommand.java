package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.Human;
/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class TestTurnPlayerWestCommand
{
	private char west='w';

	
	/**
	 * Test that the LifeForm turns west on command
	 */
	@Test
	public void testTurnLifeFormWest()
	{
		Human h = new Human("bob", 23, 0);
		West w = new West();
		w.execute(h);
		assertEquals(west, h.getDirection());
	}

}
