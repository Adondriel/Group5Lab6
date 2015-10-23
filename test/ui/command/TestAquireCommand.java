package ui.command;

import static org.junit.Assert.*;
import lifeform.Human;

import org.junit.Test;

import Exceptions.RecovRateIsNegative;
import environment.Environment;
import weapon.ChainGun;

public class TestAquireCommand
{
	Environment e = Environment.getWorldInstance(5, 5);

	@Test
	public void test() throws RecovRateIsNegative
	{
		Human h = new Human("Bob", 40, 0);
		ChainGun cg = new ChainGun();
		Aquire aquire = new Aquire();
		e.addLifeForm(0, 0, h);
		e.addWeapon(0, 0, cg);
		aquire.execute(h);
		assertEquals(cg, h.getWeapon());
	}

}
