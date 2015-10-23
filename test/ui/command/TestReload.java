package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.GenericWeapon;
import weapon.Pistol;
import weapon.Weapon;

public class TestReload
{

	/**
	 * Test the the LifeForm will reload 
	 * its weapon
	 */
	@Test
	public void testAttackCommand()
	{
		LifeForm bob = new MockLifeForm("bob", 23);
		Reload reload = new Reload();
		Weapon weapon = new Pistol();
		bob.pickupWeapon(weapon);
		((GenericWeapon) bob.getWeapon()).setCurrentAmmo(5);
		reload.execute(bob);
		assertEquals(10, weapon.getCurrentAmmo());
	}
}
