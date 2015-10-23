package ui.command;

import environment.Environment;
import lifeform.LifeForm;
import weapon.GenericWeapon;
import weapon.Weapon;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Aquire implements Command
{
	Environment e = Environment.getWorldInstance();
	/**
	 * @param Get the Selected LifeForm to 
	 * perform the action
	 */
	
	@Override
	public void execute(LifeForm L)
	{
		
		//L.pickupWeapon(); //needs work
	}

}
