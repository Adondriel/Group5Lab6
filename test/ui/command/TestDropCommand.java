package ui.command;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

import org.junit.Test;

import Exceptions.RecovRateIsNegative;
import environment.Environment;
import weapon.ChainGun;
import weapon.Pistol;

public class TestDropCommand
{
	Environment e = Environment.getWorldInstance(5, 5);

	/**
	 * Checks to see if the LifeForm will drop a weapon.
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testDropCommand() throws RecovRateIsNegative
	{
		Human bob = new Human("bob", 23, 0);
		Pistol p = new Pistol();
		Drop d = new Drop();
		e.addLifeForm(0, 0, bob);
		e.addWeapon(0, 0, p);
		bob.pickupWeapon(p);
		d.execute(bob);
		assertEquals(null, bob.getWeapon());
	}

}
