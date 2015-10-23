package ui.command;

import org.eclipse.jdt.annotation.Nullable;

import weapon.Weapon;
import environment.Environment;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Aquire implements Command
{
	Environment e = Environment.getWorldInstance();
	private Weapon tempWeapon;
	/**
	 * @param Get the Selected LifeForm to 
	 * perform the action
	 * @throws CloneNotSupportedException 
	 */
	
	@Override	
	public void execute(LifeForm L)
	{
		Weapon localW = L.getWeapon();
		if (localW != null)
		{
			
			tempWeapon = L.getWeapon();
			try {
				L.dropWeapon();
				L.pickupWeapon(e.getCellAt(L.getMyRow(), L.getMyCol()).getWeaponAtIndex(0));
				e.getCellAt(L.getMyRow(), L.getMyCol()).removeWeapon(e.getCellAt(L.getMyRow(), L.getMyCol()).getWeaponAtIndex(0));
				e.getCellAt(L.getMyRow(), L.getMyCol()).addWeapon(tempWeapon);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else
		{
			try {
				L.pickupWeapon(e.getCellAt(L.getMyRow(), L.getMyCol()).getWeaponAtIndex(0));
				e.getCellAt(L.getMyRow(), L.getMyCol()).removeWeapon(e.getCellAt(L.getMyRow(), L.getMyCol()).getWeaponAtIndex(0));

			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
