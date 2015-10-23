package GUI;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import Exceptions.RecovRateIsNegative;
import Exceptions.TooManyAttachmentsException;
import environment.Environment;
import gameplay.SimpleTimer;
import lifeform.Alien;
import lifeform.Human;
import ui.command.UserInterface;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.PowerBooster;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.Weapon;
/**
 * Tests for the GUI. Designed to be tested one test at a time to make it easier to click, then answer the question correctly.
 * @author Adam Pine
 *
 */
public class testGUI {
	Environment e = Environment.getWorldInstance(5, 5);
	
	/**
	 * Clearboard before each test.
	 */
	@Before
	public void beforeTest() {
		e.ClearBoard();
	}

	/**
	 * Test that the human icon is displaying correctly.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testHumanDisplayedCorrectly() throws RecovRateIsNegative, InterruptedException {
		Human h = new Human("Bob", 30, 15);
		e.addLifeForm(0, 0, h);
		Thread.sleep(3000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "is the human displayed correctly?"));
	}

	/**
	 * Test that alien icon is displaying correctly.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienDisplayedCorrectly() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30);
		e.addLifeForm(1, 1, a);
		Thread.sleep(3000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "is the Alien displayed correctly?"));
	}

	/**
	 * Test that a lifeform with a weapon is displaying it's weapon.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienWithWeaponDisplayed() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30);
		Weapon w = new Pistol();
		a.pickupWeapon(w);
		e.addLifeForm(0, 0, a);
		Thread.sleep(3000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"When you click on the alien, does it display the Alien's weapon correctly?"));
	}

	/**
	 * Test that a cell will display it's pistol.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testPistolInCell() throws InterruptedException {
		Weapon w = new Pistol();
		e.addWeapon(0, 0, w);
		Thread.sleep(3000);

		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a pistol at (0, 0)?"));
	}

	/**
	 * Test that a Cell can display a ChainGun
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testChainGunInCell() throws InterruptedException {
		Weapon w = new ChainGun();
		e.addWeapon(0, 0, w);
		Thread.sleep(3000);

		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Is there a Chain Gun at (0, 0)?"));
	}

	/**
	 * Tests that a Cell can Display a plasmacannon.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testPlasmaCannonInCell() throws InterruptedException {
		Weapon w = new PlasmaCannon();
		e.addWeapon(0, 0, w);
		Thread.sleep(3000);

		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Is there a Plasma Cannon at (0, 0)?"));
	}

	/**
	 * Tests that a Cell can display two weapons in one cell.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testTwoWeaponsOneCell() throws InterruptedException {
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		e.addWeapon(0, 0, w1);
		e.addWeapon(0, 0, w2);

		Thread.sleep(3000);

		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Are there TWO weapons at (0, 0)?"));
	}

	/**
	 * Test the legend, make sure it displays the icons correctly.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testLegend() throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Alien a = new Alien("ET", 30);
		Human h = new Human("Bob", 30, 15);
		e.addLifeForm(1, 1, a);
		e.addLifeForm(0, 0, h);
		Thread.sleep(3000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the legend display Human correctly?"));
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the legend display Alien correctly?"));
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the legend display Weapon correctly?"));
	}

	/**
	 * EXTRA TEST Tests that the HP is updated correctly.
	 * 
	 * @throws InterruptedException
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testLifeFormHP() throws InterruptedException, RecovRateIsNegative {
		// lifeform info will only update when you click on a Lifeform, so let's
		// click on a lifeform, then hurt it then click it again.
		Human h = new Human("Bob", 30, 5);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Is there a human at (0, 0)? is he at full hp?"));
		h.takeHit(10);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Did the Human take 5 damage?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays a Pistol
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormWeaponTypePistol() throws RecovRateIsNegative, InterruptedException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new Pistol();
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Human have a Pistol?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays a Chain Gun
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormWeaponTypeChainGun() throws RecovRateIsNegative, InterruptedException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new ChainGun();
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Human have a Chain Gun?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays a Plasma Cannon
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormWeaponTypePlasmaCannon() throws RecovRateIsNegative, InterruptedException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new PlasmaCannon();
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Human have a Plasma Cannon?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays a Scope Attachment
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormSingleAttachmentScope()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new Scope(new Pistol());
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Human have a Scope?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays a Stabilizer Attachment
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormSingleAttachmentStabilizer()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new Stabilizer(new Pistol());
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Human have a Stabilizer?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays a PowerBooster
	 * Attachment
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormSingleAttachmentPowerBooster()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new PowerBooster(new Pistol());
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Human have a Power Booster?"));
	}

	// Double attachments
	/**
	 * EXTRA TEST Test that a lifeform properly displays both Power Booster and
	 * Scope
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormDoubleAttachmentPowerBoosterAndScope()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new PowerBooster(new Scope(new Pistol()));
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Human have a Power Booster and Scope?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays both Power Booster and
	 * Stabilizer
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormDoubleAttachmentPowerBoosterAndStabilizer()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new PowerBooster(new Stabilizer(new Pistol()));
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Human have a Power Booster and Stabilizer?"));
	}

	/**
	 * EXTRA TEST Test that a lifeform properly displays both Scope and
	 * Stabilizer	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormDoubleAttachmentScopeAndStabilizer()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Human h = new Human("Bob", 30, 5);
		Weapon w = new Scope(new Stabilizer(new Pistol()));
		h.pickupWeapon(w);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Human have a Scope and Stabilizer?"));
	}

	// Cell testing
	// Cell weapon Attachments
	/**
	 * EXTRA TEST
	 * Test that a cell can display a Scope Attachment
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellSingleAttachmentScope()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Weapon w = new Scope(new Pistol());
		e.addWeapon(0, 0, w);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Cell have a Scope?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display a PowerBooster Attachment
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellSingleAttachmentPowerBooster()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Weapon w = new PowerBooster(new Pistol());
		e.addWeapon(0, 0, w);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Cell have a PowerBooster?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display a Stabilizer Attachment
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellSingleAttachmentStabilizer()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Weapon w = new Stabilizer(new Pistol());
		e.addWeapon(0, 0, w);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Does the Cell have a Stabilizer?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display both a PowerBooster and Scope Attachment
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	// Cell weapon double attachments
	@Test
	public void testCellDoubleAttachmentPowerBoosterAndScope()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Weapon w = new PowerBooster(new Scope(new Pistol()));
		e.addWeapon(0, 0, w);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Cell have a Power Booster and Scope?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display both a PowerBooster and Stabilizer Attachment
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellDoubleAttachmentPowerBoosterAndStabilizer()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Weapon w = new PowerBooster(new Stabilizer(new Pistol()));
		e.addWeapon(0, 0, w);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Cell have a Power Booster and Stabilizer?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display both a Scope and Stabilizer Attachment
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellDoubleAttachmentScopeAndStabilizer()
			throws RecovRateIsNegative, InterruptedException, TooManyAttachmentsException {
		Weapon w = new Scope(new Stabilizer(new Pistol()));
		e.addWeapon(0, 0, w);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION,
				JOptionPane.showConfirmDialog(null, "Does the Cell have a Scope and Stabilizer?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display two weapons, one with two Attachments, the other with one Attachment.
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellTwoWeaponsTwoAndOneAttachmentsEach() throws TooManyAttachmentsException, InterruptedException {
		Weapon w2 = new Scope(new Stabilizer(new Pistol()));
		Weapon w1 = new Scope(new Pistol());
		e.addWeapon(0, 0, w2);
		e.addWeapon(0, 0, w1);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Does the Cell's first weapon have a Scope and Stabilizer?\n Does the second Weapon have a Scope?"));
	}
	/**
	 * EXTRA TEST
	 * Test that a cell can display two weapons, both with two attachments.
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 * @throws TooManyAttachmentsException
	 */
	@Test
	public void testCellTwoWeaponsTwoAttachmentsEach() throws TooManyAttachmentsException, InterruptedException {
		Weapon doubleAttachments = new Scope(new PowerBooster(new Pistol()));
		Weapon doubleWeapon2 = new Stabilizer(new Scope(new Pistol()));
		e.addWeapon(0, 4, doubleAttachments);
		e.addWeapon(0, 4, doubleWeapon2);
		Thread.sleep(2000);
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Does the Cell's first weapon have a Scope and PowerBooster?\n Does the second Weapon have a Stabilizer and a Scope?"));
	}

	/**
	 * EXTRA TEST
	 * Test that all of the turning actually makes the lifeform turn!
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testLifeFormTurning() throws RecovRateIsNegative, InterruptedException {
		Human h = new Human("Bob", 30, 5);
		e.addLifeForm(0, 0, h);
		Thread.sleep(2000);
		h.turnEast();
		Thread.sleep(50);
		h.turnSouth();
		Thread.sleep(50);
		h.turnWest();
		Thread.sleep(50);
		h.turnNorth();
		Thread.sleep(500);
		h.turnRight();
		Thread.sleep(50);
		h.turnRight();
		Thread.sleep(50);
		h.turnRight();
		Thread.sleep(50);
		h.turnRight();
		Thread.sleep(500);
		h.turnAround();
		Thread.sleep(250);
		h.turnAround();
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null,
				"Did the lifeform move around a lot?"));
	}

}
