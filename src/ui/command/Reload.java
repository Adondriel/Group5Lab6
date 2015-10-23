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
	
	@Override
	public void execute(LifeForm lf)
	{
		equiped = lf.getWeapon();
		equiped.reload();
	}
	
}
