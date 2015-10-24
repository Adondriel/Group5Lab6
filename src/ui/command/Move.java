package ui.command;

import Exceptions.EnvironmentException;
import Exceptions.RecovRateIsNegative;
import environment.Environment;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Move implements Command
{

	Environment e = Environment.getWorldInstance();

	
	@Override
	public void execute(LifeForm L)
	{
		
		try
		{
			e.stepNSpaces(L.getMyRow(), L.getMyCol(), L.getMaxSpeed());
		} catch (RecovRateIsNegative e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
