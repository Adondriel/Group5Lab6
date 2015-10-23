/**
 * Author: Jason LoBianco
 * Tests the functionality of the UserInterface.
 */
package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;

import weapon.ChainGun;
import Exceptions.RecovRateIsNegative;
import environment.Environment;

import javax.swing.*;

import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

public class TestUserInterface 
{
	private char north='n';
	private char south='s';
	private char east='e';
	private char west='w';

	/**
	 * Checks to see that the UserInterface is initialized correctly.
	 */
	@Test
	public void testInitialization() 
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did a window open up?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there an attack button on the top left?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a reload button on the bottom left?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there an aquire button next to attack?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a drop button next to reload?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a M button in the middle of 4 arrows?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a North button?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a South button?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a West button?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a East button?"));
	}
	
	/**
	 * Checks to see that clicking the turn north button turns the LifeForm north.
	 * @throws RecovRateIsNegative 
	 * @throws InterruptedException 
	 */
	@Test
	public void testTurnNorth() throws RecovRateIsNegative, InterruptedException
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human h = new Human("Bob", 30, 15);
		ui.setLifeForm(h);
		h.turnEast();
		JOptionPane.showInputDialog("Click the north button.");
		Thread.sleep(7500);
		assertEquals(north, h.getDirection());
	}
	
	/**
	 * Checks to see that clicking the turn east button turns the LifeForm east.
	 * @throws InterruptedException 
	 */
	@Test
	public void testTurnEast() throws InterruptedException
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human h = new Human("Bob", 30, 15);
		ui.setLifeForm(h);
		JOptionPane.showInputDialog("Click the east button.");
		Thread.sleep(7500);
		assertEquals(east, h.getDirection());
	}
	
	/**
	 * Checks to see that clicking the turn south button turns the LifeForm south.
	 * @throws InterruptedException 
	 */
	@Test
	public void testTurnSouth() throws InterruptedException
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human h = new Human("Bob", 30, 15);
		ui.setLifeForm(h);
		JOptionPane.showInputDialog("Click the south button.");
		Thread.sleep(7500);
		assertEquals(south, h.getDirection());
	}
	
	/**
	 * Checks to see that clicking the turn west button turns the LifeForm west.
	 * @throws InterruptedException 
	 */
	@Test
	public void testTurnWest() throws InterruptedException
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human h = new Human("Bob", 30, 15);
		ui.setLifeForm(h);
		JOptionPane.showInputDialog("Click the west button.");
		Thread.sleep(7500);
		assertEquals(west, h.getDirection());
	}
	
//	/**
//	 * Checks to see that clicking the acquire button causes LifeForm to pickup a weapon.
//	 */
//	@Test
//	public void testAcquire()
//	{
//		UserInterfaceBuilder ui = new UserInterfaceBuilder();
//		LifeForm entity = new MockLifeForm("Bob", 40);
//		ChainGun cg = new ChainGun();
//		//Click the acquire button to pick up a weapon.
//		assertEquals(cg, entity.getWeapon());
//	}
//	
//	/**
//	 * Checks to see that clicking the drop button causes LifeForm to drop a weapon.
//	 */
//	@Test
//	public void testDrop()
//	{
//		UserInterfaceBuilder ui = new UserInterfaceBuilder();
//		LifeForm entity = new MockLifeForm("Bob", 40);
//		ChainGun cg = new ChainGun();
//		//Click the acquire button to pick up a weapon.
//		assertEquals(cg, entity.getWeapon());
//		//Click the drop button to drop a weapon.
//		assertEquals(null, entity.getWeapon());
//	}
//	
//	/**
//	 * Checks to see that clicking the move button causes LifeForm to move.
//	 */
//	@Test
//	public void testMove()
//	{
//		UserInterfaceBuilder ui = new UserInterfaceBuilder();
//		LifeForm entity = new MockLifeForm("Bob", 40);
//	}
//	
//	/**
//	 * Checks to see that clicking the attack button causes LifeForm to attack another LifeForm.
//	 */
//	@Test
//	public void testAttack()
//	{
//		UserInterfaceBuilder ui = new UserInterfaceBuilder();
//		LifeForm entity = new MockLifeForm("Bob", 40);
//		LifeForm entity2 = new MockLifeForm("Fred", 30);
//	}
//	
	/**
	 * Checks to see that clicking the reload button causes LifeForm to reload a weapon.
	 */
	@Test
	public void testReload()
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		LifeForm entity = new MockLifeForm("Bob", 40);
		ChainGun cg = new ChainGun();
		//Click the acquire button to pick up a weapon.
		assertEquals(cg, entity.getWeapon());
		cg.setCurrentAmmo(10);
		//Click the reload button to reload the weapon.
		assertEquals(40, cg.getCurrentAmmo());
	}	
}
