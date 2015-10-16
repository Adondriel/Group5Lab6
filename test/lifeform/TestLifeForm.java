/**
 * @author Adam Pine-Lab5, Alex Fennen-lab4
 * Tests the functionality of the LifeForm class. 
 */
package lifeform;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Exceptions.EnvironmentException;
import environment.Environment;
import gameplay.SimpleTimer;
import lifeform.LifeForm;
import weapon.*;
public class TestLifeForm {
	//Singleton Tests Start Here
	//TESTS THAT HAD TO BE UPDATED BECAUSE OF ENVIRONMENT!
	//	 
	Environment env = Environment.getWorldInstance(5, 5);
	@Before
	public void clearEnv(){
		env.ClearBoard();
	}	
	/**
	 * Test that the dead are not able to attack.
	 * @throws EnvironmentException 
	 */
	@Test
	public void testDeadCannotAttack() throws EnvironmentException {
		LifeForm e = new MockLifeForm("bob", 0);
		LifeForm e2 = new MockLifeForm("Steve", 30);
		//their range does not matter 100%, cause they are dead. Prevents a null pointer
		env.addLifeForm(0, 0, e);
		env.addLifeForm(0, 1, e2);
		e.setAttackDmg(10);
		e.attackLF(e2);
		assertEquals(30, e2.getCurrentLifePoints());
	}	
	/**
	 * Tests that a lifeform without ammo and outside of the melee damage range
	 * does not deal damage
	 * @throws EnvironmentException 
	 */
	@Test
	public void testNoAmmoAndPastRange() throws EnvironmentException {
		LifeForm e = new MockLifeForm("Bob", 40);
		LifeForm e2 = new MockLifeForm("Fred", 40);
		//by adding them at these points the range should still be 15
		GenericWeapon p = new Pistol();
		e.pickupWeapon(p);
		p.setCurrentAmmo(0);
		//Range.distance = 15;
		System.out.println(env.addLifeForm(0, 2, e));
		System.out.println(env.addLifeForm(3, 2, e2));
		System.out.println(env.getDistance(e, e2));
		e.setAttackDmg(5);
		e.attackLF(e2);
		assertEquals(40, e2.getCurrentLifePoints());
	}	
	/**
	 * Tests that a lifeform can attack correctly with different weapons from
	 * different ranges
	 * @throws EnvironmentException 
	 */
	@Test
	public void testAttackingWithWeapon() throws EnvironmentException {
		LifeForm e = new MockLifeForm("Bob", 40);
		LifeForm e2 = new MockLifeForm("Fred", 40);
		GenericWeapon p = new Pistol();
		GenericWeapon c = new ChainGun();
		e.pickupWeapon(p);
		e2.pickupWeapon(c);
//		Range.distance = 5;
		env.ClearBoard();
		System.out.println(env.addLifeForm(1, 2, e));
		System.out.println(env.addLifeForm(2, 2, e2));
		System.out.println(env.getDistance(e, e2));
		e.attackLF(e2);
		assertEquals(30, e2.getCurrentLifePoints());
		e2.attackLF(e);
		assertEquals(38, e.getCurrentLifePoints());
		//remove them from their old place, they aren't there any more!
		System.out.println(env.removeLifeForm(0, 1));
		System.out.println(env.removeLifeForm(0, 2));
		//makes their distance 15
		System.out.println(env.addLifeForm(0, 0, e));
		System.out.println(env.addLifeForm(0, 3, e2));
		System.out.println(env.getDistance(e, e2));
	//	Range.distance = 15;
		e.attackLF(e2);
		assertEquals(24, e2.getCurrentLifePoints());
		e2.attackLF(e);
		assertEquals(31, e.getCurrentLifePoints());
	}	
	/**
	 * !!Extra test!!, just to make sure that if there are any changes to checking ammo,
	 *  that it does not cause a bad call to get range.
	 *  
	 * Tests that a lifeform whose weapon is out of ammo attacks using its base
	 * attack
	 * @throws EnvironmentException 
	 */
	@Test
	public void testAttackingWithoutAmmo() throws EnvironmentException {
		LifeForm entity = new MockLifeForm("Bob", 40);
		LifeForm entity2 = new MockLifeForm("Fred", 40);
		env.addLifeForm(0, 0, entity);
		env.addLifeForm(0, 1, entity2);
		GenericWeapon p = new Pistol();
		entity.pickupWeapon(p);
		p.setCurrentAmmo(0);
		//Range.distance = 1;
		entity.setAttackDmg(5);
		entity.attackLF(entity2);
		assertEquals(35, entity2.getCurrentLifePoints());
	}	
	// !!!!
	// DECORATOR PATTERN TEST START HERE.
	// !!!!	
	/**
	 * Tests that a lifeform can equip a weapon
	 */
	@Test
	public void testPickupWeapon() {
		LifeForm entity = new MockLifeForm("Bob", 40);
		GenericWeapon w = new Pistol();
		entity.pickupWeapon(w);
		assertEquals(w, entity.weapon);
	}
	/**
	 * Tests that a lifeform can drop a weapon
	 */
	@Test
	public void testdropWeapon() {
		LifeForm entity = new MockLifeForm("Bob", 40);
		GenericWeapon w = new Pistol();
		entity.pickupWeapon(w);
		assertEquals(w, entity.weapon);
		entity.dropWeapon();
		assertEquals(null, entity.weapon);
	}
	/**
	 * Tests that a lifeform can't equip a weapon if it already has one
	 */
	@Test
	public void testCantOverrideWeapon() {
		LifeForm entity = new MockLifeForm("Bob", 40);
		GenericWeapon w = new Pistol();
		entity.pickupWeapon(w);
		assertEquals(w, entity.weapon);
		GenericWeapon c = new ChainGun();
		entity.pickupWeapon(c);
		assertEquals(w, entity.weapon);
	}
	// !!!!
	// OBSERVATION PATTERN TEST START HERE.
	// !!!!
	/**
	 * Tests if the lifeform can set attackStr.
	 */
	@Test
	public void testSetAttack() {
		LifeForm e = new MockLifeForm("Bob", 30);
		assertEquals(0, e.getAttackDmg());
		e.setAttackDmg(25);
		assertEquals(25, e.getAttackDmg());
	}
	/**
	 * Tests to ensure that a lifeform can attack another lifeform and the other
	 * lifeform takes damage correctly.
	 * @throws EnvironmentException 
	 */
	@Test
	public void testAttackMethod() throws EnvironmentException {
		//Range.distance = 1;
		LifeForm e = new MockLifeForm("bob", 30);
		LifeForm e2 = new MockLifeForm("Steve", 30);
		e.setAttackDmg(10);
		e.attackLF(e2);
		assertEquals(20, e2.getCurrentLifePoints());
	}
	/**
	 * Tests that the lifeform can track the passage of time.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testCanTrackPassageOfTime() throws InterruptedException {
		SimpleTimer st = new SimpleTimer(1000);
		LifeForm e = new MockLifeForm("bob", 20);
		st.addTimeObserver(e);
		st.start();
		Thread.sleep(250); // 1/4 a second diff from the simpletimer sleep.
		for (int x = 0; x < 5; x++) {
			assertEquals(x, e.myTime);
			Thread.sleep(1000);
		}
	}
	// !!!!
	// STRATEGY PATTERN TEST START HERE.
	// !!!!
	/**
	 * When a LifeForm is created, it should know it's name and how many life
	 * points it has.
	 */
	/**
	 * Tests the storing of the values for the lifeforms.
	 */
	@Test
	public void testInitialization() {
		LifeForm entity = new MockLifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
	}
	/**
	 * Tests getting hit.
	 */
	@Test
	public void testTakeHit() {
		LifeForm entity = new MockLifeForm("Bob", 30);
		entity.takeHit(10);
		assertEquals(20, entity.getCurrentLifePoints());
	}
	/**
	 * Test getting hit into death.
	 */
	@Test
	public void testTakeHitToDeath() {
		LifeForm e2 = new MockLifeForm("Steve", 10);
		e2.takeHit(12);
		assertEquals(0, e2.getCurrentLifePoints());
	}
	@Test
	public void testTakeMultiHit() {
		LifeForm e2 = new MockLifeForm("Bob", 30);
		e2.takeHit(5);
		assertEquals(25, e2.getCurrentLifePoints());
		e2.takeHit(5);
		assertEquals(20, e2.getCurrentLifePoints());
	}
}