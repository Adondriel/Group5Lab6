package ui.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * @author Bradley Solorzano
 * Design Patterns Group 5 lab 6
 *
 */
public class TestAttackCommand
{
	/**
	 * Test the the LifeForm turns west on command
	 */
	@Test
	public void testAttackCommand()
	{
		LifeForm bob = new MockLifeForm("bob", 23);
		LifeForm jon = new MockLifeForm("bob", 23);
		Attack attack = new Attack();
		attack.attackCommand(bob, jon); //bob will attack jon
		attack.execute();
		assertEquals(23, jon.getCurrentLifePoints());
	}
}
