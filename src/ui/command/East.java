package ui.command;

import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */

public class East implements Command
{

private LifeForm entity;
	
	public void entityCommand(LifeForm L)
	{
		entity = L;
	}

	@Override
	public void execute()
	{
		entity.turnEast();
	}
}
