/**
 * @author Adam Pine
 * The test cases for the Cell class
 */
package environment;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import environment.Cell;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;
public class TestCell {
	/**
	 * at initialization, the cell should be empty and not contain a lifeform.
	 * Should also not contain any weapons.
	 */
	@Test
	public void testInitialization() {
		Cell cell = new Cell();
		assertNull(cell.getLifeForm());
		//test the new weapons init.
		assertNull(cell.getWeapons());
	}	
	/**
	 * Tests that we can add and remove two and ONLY two weapons.
	 */
	@Test
	public void testSetAndRemoveTwoWeapons(){
		Cell c = new Cell();
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		//add 2 weapons.
		assertTrue(c.addWeapon(w1));
		assertTrue(c.addWeapon(w2));
		//adding a 3rd will fail.
		assertFalse(c.addWeapon(w3));
		//remove each of the weapons now.
		assertTrue(c.removeWeapon(w1));
		assertTrue(c.removeWeapon(w2));
		//try to remove w3, but it doesn't exist. will fail, cause it isn't there.
		assertFalse(c.removeWeapon(w3));		
	}	
	/**
	 * takes place of text Can't place a weapon in a slot that already has a weapon.
	 * 
	 * Because of the way this is coded, the lifeform does not add a weapon to a specific slot,
	 * rather if the cell has room, it will be added to the end of the arraylist of weapons.
	 */
	@Test
	public void testCantAddAWeaponIfTheWeaponsAreFull(){
		Cell c = new Cell();
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		//add 2 weapons.
		assertTrue(c.addWeapon(w1));
		assertTrue(c.addWeapon(w2));
		//adding a 3rd will fail.
		assertFalse(c.addWeapon(w3));
	}
	/**
	 * EXTRA TEST
	 * Tests the ordering and function of the Weapons Array.
	 */
	@Test
	public void testWeaponsArray(){
		Cell c = new Cell();
		final Weapon w1 = new Pistol();
		final Weapon w2 = new ChainGun();
		//add the weapons
		assertTrue(c.addWeapon(w1));
		assertTrue(c.addWeapon(w2));
		//compare the array of w1, w2, vs the array of .getWeapons(). They are in the same order/the order added.
		assertEquals(new ArrayList<Weapon>() {{add(w1); add(w2);}}, c.getWeapons());
		//Compare the array of w2, w1, vs the actual array in the cell. They are not equal anymore, because they were added in a different order!
		assertNotEquals(new ArrayList<Weapon>() {{add(w2); add(w1);}}, c.getWeapons());
	}	
	/**
	 * EXTRA TEST
	 * Tests that the return values are not able to be modified by the test code.
	 * Make sure that it can't modify the weapons array values.
	 */
	@Test
	public void testWeaponReturnValues(){
		Cell c = new Cell();
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		c.addWeapon(w1);
		c.addWeapon(w2);
		//set test to the array of weapons
		ArrayList<Weapon> test = c.getWeapons();
		//add a new weapon to that arrow
		test.add(w3);
		//now make sure that the actual value of c.getWeapons()
		//does not equal the value of what we just edited.
		assertNotEquals(test, c.getWeapons());		
	}
	/**
	 * Extra Test
	 * Tests that the return Weapon of Cell.getWeaponAtIndex() is not a modifiable item.
	 */
	@Test
	public void testGetWeaponAtIndexReturnVals(){
		Cell c = new Cell();
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		c.addWeapon(w1);
		c.addWeapon(w2);
		//bring in the return value
		Weapon test = c.getWeaponAtIndex(0);
		//set it to another object
		test = w3;
		//make sure the actual object is still the original.
		assertEquals(w1, c.getWeaponAtIndex(0));
	}	
	//!!!!
	//Old Tests
	//!!!!
	/**
	 * Checks to see if we can change the LifeForm held by the Cell that
	 * getLifeForm properly responds to this change
	 */
	@Test
	public void testSetLifeForm() {
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm fred = new MockLifeForm("Fred", 40);
		Cell cell = new Cell();
		// the cell is empty so this should work.
		boolean success = cell.addLifeForm(bob);
		assertTrue(success);
		assertEquals(bob, cell.getLifeForm());
		// the cell is not empty so this should fail
		success = cell.addLifeForm(fred);
		assertFalse(success);
		assertEquals(bob, cell.getLifeForm());
	}
	/**
	 * Check the removal of lifeform from a cell.
	 */
	@Test
	public void testRemoveLifeForm() {
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm steve = new MockLifeForm("Steve", 40);
		Cell cell = new Cell();
		cell.addLifeForm(bob);
		assertEquals(bob, cell.removeLifeForm());
		cell.addLifeForm(steve);
		assertEquals(steve, cell.getLifeForm());
		Cell nullcell = new Cell();
		assertNull(nullcell.removeLifeForm());
	}
}