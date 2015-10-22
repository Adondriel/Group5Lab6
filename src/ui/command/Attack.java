package ui.command;

import Exceptions.EnvironmentException;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Attack implements Command
{
	private LifeForm entity;
	private LifeForm target;
	public void attackCommand(LifeForm L, LifeForm T)
	{
		entity = L;
		target = T;
	}
	@Override
	public void execute()
	{
		try
		{
			entity.attackLF(target);
		} catch (EnvironmentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
