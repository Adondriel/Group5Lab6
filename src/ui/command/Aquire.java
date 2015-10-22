package ui.command;

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

	private LifeForm entity;
	private GenericWeapon weapon;
	
	/**
	 * @param Get the Selected LifeForm to 
	 * perform the action
	 */
	public void entityDropCommand(LifeForm L, Weapon W)
	{
		entity = L;
		weapon = (GenericWeapon) W;
	}
	
	@Override
	public void execute()
	{
		entity.pickupWeapon(weapon);
	}

}
