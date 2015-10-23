package ui.command;

import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Reload implements Command
{
	@Override
	public void execute(LifeForm lf)
	{
		lf.getWeapon().reload();
	}
	
}
