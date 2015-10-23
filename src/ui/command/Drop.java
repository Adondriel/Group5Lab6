package ui.command;

import environment.Environment;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Drop implements Command
{

	private LifeForm entity;
	
	/**
	 * @param Get the Selected LifeForm to 
	 * perform the action
	 */
	
	@Override
	public void execute(LifeForm L)
	{
		entity.dropWeapon();
	}

}
