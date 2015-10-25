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
		int stepSpaces=0;
		try
		{
			e.stepNSpaces(L.getMyRow(), L.getMyCol(), stepSpaces);
		} catch (RecovRateIsNegative e)
		{
			e.printStackTrace();
		}
	}

}
