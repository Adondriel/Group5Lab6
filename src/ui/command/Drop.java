package ui.command;

import weapon.Weapon;
import environment.Environment;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Drop implements Command
{
	Environment e = Environment.getWorldInstance();
	private Weapon tempWeapon;
	
	@Override
	public void execute(LifeForm L)
	{
		if (L.getWeapon() != null)
		{
			tempWeapon = L.getWeapon();
			L.dropWeapon();
			try {
				e.getCellAt(L.getMyRow(), L.getMyCol()).addWeapon(tempWeapon);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
