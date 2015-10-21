package GUI;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import Exceptions.RecovRateIsNegative;
import environment.Environment;
import gameplay.SimpleTimer;
import lifeform.Alien;
import lifeform.Human;
import ui.command.UserInterface;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.Weapon;

public class testGUI {
	
	
	@Test
	public void test() throws RecovRateIsNegative, InterruptedException {
		Environment e = Environment.getWorldInstance(5, 5);
		Alien a = new Alien("ET", 30);
		Human h = new Human("Bob", 30, 15);
		e.addLifeForm(1, 1, a);
		e.addLifeForm(0, 0, h);
		e.addLifeForm(2, 2, h);
		e.removeLifeForm(0, 0);
		e.addWeapon(0, 0, new Pistol());
		h.turnEast();
		h.turnRight();
		e.stepNSpaces(1, 1, 1);
		h.pickupWeapon(new ChainGun());
		Thread.sleep(15000);
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, "Create Cell Image Icon Correct For\nHuman(0,0) and Alien(1,1)\nDoes it look right?"));
	}

}
