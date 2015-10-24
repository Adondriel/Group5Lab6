package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.RecovRateIsNegative;
import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestPlayerMoveCommand
{

	/**
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testMoveCommand() throws RecovRateIsNegative
	{
		Move move = new Move();
		Environment e = Environment.getWorldInstance(5, 5);
		Human bob = new Human("bob", 23, 0); //default direction is north
		e.addLifeForm(3, 1, bob);
		move.execute(bob);
		assertEquals(0, bob.getMyRow());
		assertEquals(1, bob.getMyCol());
		
	}

}
