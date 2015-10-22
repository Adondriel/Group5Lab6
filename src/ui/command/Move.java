package ui.command;

import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Move implements Command
{

private LifeForm entity;
	
	/**
	 * @param Get the Selected LifeForm to 
	 * perform the action
	 */
	public void entityMoveCommand(LifeForm L)
	{
		entity = L;
	}
	
	@Override
	public void execute()
	{
		entity.moved();
	}

}
