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
	Environment e = Environment.getWorldInstance(5, 5);

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
		e.ClearBoard();
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
		JOptionPane.showInputDialog("Click ok, then click the north button.");
		Thread.sleep(7500);
		assertEquals(north, h.getDirection());
		e.ClearBoard();
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
		JOptionPane.showInputDialog("Click ok, then click the east button.");
		Thread.sleep(7500);
		assertEquals(east, h.getDirection());
		e.ClearBoard();
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
		JOptionPane.showInputDialog("Click ok, then click the south button.");
		Thread.sleep(7500);
		assertEquals(south, h.getDirection());
		e.ClearBoard();
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
		JOptionPane.showInputDialog("Click ok, then click the west button.");
		Thread.sleep(7500);
		assertEquals(west, h.getDirection());
		e.ClearBoard();
	}
	
	/**
	 * Checks to see that clicking the acquire button causes LifeForm to pickup a weapon.
	 * @throws InterruptedException 
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testAcquire() throws InterruptedException, RecovRateIsNegative
	{
		
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human h = new Human("Bob", 40, 0);
		ChainGun cg = new ChainGun();
		e.addLifeForm(0, 0, h);
		e.addWeapon(0, 0, cg);
		ui.setLifeForm(h);
		JOptionPane.showInputDialog("Click ok, then click the acquire button.");
		Thread.sleep(7500);
		assertEquals(cg, h.getWeapon());
		e.ClearBoard();
	}
	
	/**
	 * Checks to see that clicking the drop button causes LifeForm to drop a weapon.
	 * @throws InterruptedException 
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testDrop() throws InterruptedException, RecovRateIsNegative
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		LifeForm entity = new MockLifeForm("Bob", 40);
		ChainGun cg = new ChainGun();
		e.addLifeForm(0, 0, entity);
		e.addWeapon(0, 0, cg);
		entity.pickupWeapon(cg);
		ui.setLifeForm(entity);
		assertEquals(cg, entity.getWeapon());
		JOptionPane.showInputDialog("Click ok, then click the drop button.");
		Thread.sleep(7500);
		assertEquals(null, entity.getWeapon());
		e.ClearBoard();
	}
	
	/**
	 * Checks to see that clicking the move button causes LifeForm to move.
	 * @throws RecovRateIsNegative 
	 * @throws InterruptedException 
	 */
	@Test
	public void testMove() throws RecovRateIsNegative, InterruptedException
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human h = new Human("Bob", 40, 0);
		ui.setLifeForm(h);
		e.addLifeForm(4, 2, h);
		JOptionPane.showInputDialog("Click ok, then click the move button.");
		Thread.sleep(7500);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the Human move?"));
		e.ClearBoard();
	}
	
	/**
	 * Checks to see that clicking the attack button causes LifeForm to attack another LifeForm.
	 * @throws InterruptedException 
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testAttack() throws InterruptedException, RecovRateIsNegative
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		Human bob = new Human("Bob", 40, 0);
		Human fred = new Human("Fred", 30, 0);
		e.addLifeForm(2, 1, fred);
		e.addLifeForm(1, 1, bob);
		ui.setLifeForm(fred);
		JOptionPane.showInputDialog("Click ok, Then click the attack button.");
		Thread.sleep(7500);
		assertEquals(35, bob.getCurrentLifePoints());
		e.ClearBoard();
	}
	
	/**
	 * Checks to see that clicking the reload button causes LifeForm to reload a weapon.
	 * @throws InterruptedException 
	 */
	@Test
	public void testReload() throws InterruptedException
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
		LifeForm entity = new MockLifeForm("Bob", 40);
		ChainGun cg = new ChainGun();
		entity.pickupWeapon(cg);
		cg.setCurrentAmmo(10);
		ui.setLifeForm(entity);
		JOptionPane.showInputDialog("Click ok, Then click the reload button.");
		Thread.sleep(7500);
		assertEquals(40, cg.getCurrentAmmo());
		e.ClearBoard();
	}	
}
