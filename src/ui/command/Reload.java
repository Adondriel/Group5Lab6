package ui.command;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Reload implements Command
{
	private Weapon equiped;
	
	public void weaponReloadCommand(Weapon W)
	{
		equiped = W;
	}
	@Override
	public void execute()
	{
		equiped.reload();
	}
	
}
