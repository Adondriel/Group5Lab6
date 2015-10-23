package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.GenericWeapon;
import weapon.Pistol;

public class TestDropCommand
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
		GenericWeapon weapon = new Pistol();
		weapon.setCurrentAmmo(5);
		reload.weaponReloadCommand(weapon);
		reload.execute();
		assertEquals(10, weapon.getCurrentAmmo());
	}

}
