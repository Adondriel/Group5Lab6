package ui.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Exceptions.EnvironmentException;
import Exceptions.RecovRateIsNegative;
import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * @author Bradley Solorzano, Benjamin Uleau
 * Design Patterns Group 5 lab 6
 *
 */
public class TestAttackCommand
{
	Environment e = Environment.getWorldInstance(5, 5);
	/**
	 * Test the the LifeForm turns west on command
	 * @throws EnvironmentException 
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testAttackCommand() throws EnvironmentException, RecovRateIsNegative
	{
		LifeForm bob = new Human("bob", 30, 0);
		LifeForm jon = new Human("bob", 30, 0);
		LifeForm jim = new Human("bob", 30, 0);
		e.addLifeForm(3, 3, bob);
		e.addLifeForm(2, 3, jon);
		Attack attack = new Attack();
		attack.attackCommand(3, 3); //bob will attack jon
		assertEquals(25, jon.getCurrentLifePoints());
		
		e.ClearBoard();
		
		
	}
}
