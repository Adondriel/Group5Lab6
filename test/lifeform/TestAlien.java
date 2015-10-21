/**
 * @author Adam Pine, Benjamin Uleau
 * The tests for the alien class. Tests all of their recovery methods, and initialization.
 */
package lifeform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.EnvironmentException;
import Exceptions.RecovRateIsNegative;
import environment.Environment;
import gameplay.SimpleTimer;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;

public class TestAlien {
	Environment env = Environment.getWorldInstance(5, 5);
	@Before
	public void clearBoard(){
		env.ClearBoard();
	}
	//Refactored tests for Singleton starts here!
	/**
	 * EXTRA TEST - lab5
	 * Tests the takeHit method of the human, using an alien to do the
	 * attacking.
	 * 
	 * 
	 * @throws RecovRateIsNegative
	 * @throws EnvironmentException 
	 */
	// lab5: added environment
	@Test
	public void testAlienAttackHumanDmgEqualArmor() throws RecovRateIsNegative, EnvironmentException {
		Alien a = new Alien("ET", 30, 1, new RecoveryLinear(3));
		Human h = new Human("Steve", 30, 10);
		env.addLifeForm(0, 0, a);
		env.addLifeForm(0, 1, h);
		a.attackLF(h);
		assertEquals(30, h.getCurrentLifePoints());
	}
	
	
	/**
	 * EXTRA TEST - lab5
	 * Tests the takeHit method of the human, using an alien to do the
	 * attacking.
	 * 
	 * 
	 * @throws RecovRateIsNegative
	 * @throws EnvironmentException 
	 */
	//lab 5: added environment
	@Test
	public void testAlienAttackHumanSomeArmor() throws RecovRateIsNegative, EnvironmentException {
		Alien a = new Alien("ET", 30, 1, new RecoveryLinear(3));
		Human h = new Human("Steve", 30, 5);
		env.addLifeForm(0, 0, a);
		env.addLifeForm(0, 1, h);
		a.attackLF(h);
		assertEquals(25, h.getCurrentLifePoints());
	}
	
	//
	// Lab 5: added environment
	// EXTRA TESTS HERE:
	// These tests use an alien to do the attacking of the human, to ensure they
	// all cooperate well.
	//
	/**
	 * EXTRA TEST - lab5
	 * Tests the takeHit method of the human, using an alien to do the
	 * attacking.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws EnvironmentException 
	 */
	@Test
	public void testAlienAttackHumanNoArmor() throws RecovRateIsNegative, EnvironmentException {
		Alien a = new Alien("ET", 30, 1, new RecoveryLinear(3));
		Human h = new Human("Steve", 30, 0);
		env.addLifeForm(0, 0, a);
		env.addLifeForm(0, 1, h);
		a.attackLF(h);
		assertEquals(20, h.getCurrentLifePoints());
	}	
	//!!!!
	//DECORATOR PATTERN TESTS START HERE
	//!!!!
	/**
	 * Test's the aliens default attack strength.
	 */
	@Test
	public void testAlienDefaultAtkStr() throws RecovRateIsNegative {
		Alien a = new Alien("ET", 30);
		assertEquals(10, a.getAttackDmg());
	}
	/**
	 * Tests the construction value of an alien's recov rate. Also tests the get
	 * and set recoveryRate() method.
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testAlienGetSetRecovRateandInit() throws RecovRateIsNegative {
		Alien a = new Alien("ET", 30, 2, new RecoveryLinear(3));
		assertEquals(2, a.getRecoveryRate());
		a.setRecoveryRate(3);
		assertEquals(3, a.getRecoveryRate());
	}
	/**
	 * If the recov rate is < 0 it will throw an error. Don't do that!
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test(expected = Exception.class)
	public void testAlienInitRecovRateLessThanZero() throws RecovRateIsNegative {
		Alien a = new Alien("ET", 30, -1, new RecoveryLinear(3));
	}
	/**
	 * Alien should not recover when he has 0 recovrate.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienCombatRecoveryAtRR0() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30, 0, new RecoveryLinear(2));
		SimpleTimer st = new SimpleTimer(1000);
		st.addTimeObserver(a);
		st.start();
		a.takeHit(10);
		// health between 0-1 seconds.
		assertEquals(20, a.getCurrentLifePoints());
		Thread.sleep(1250);
		// health a little bit after 1 seconds.
		assertEquals(20, a.getCurrentLifePoints());
		Thread.sleep(1250);
		// Health at 2 seconds after taking dmg.
		assertEquals(20, a.getCurrentLifePoints());
	}
	/**
	 * Alien should recover after each tick.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienCombatRecoveryAtRR1() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30, 1, new RecoveryLinear(2));
		SimpleTimer st = new SimpleTimer(1000);
		st.addTimeObserver(a);
		st.start();
		a.takeHit(10);
		// health between 0-1 seconds.
		assertEquals(20, a.getCurrentLifePoints());
		Thread.sleep(1250);
		// health a little bit after 1 seconds.
		assertEquals(22, a.getCurrentLifePoints());
		Thread.sleep(1250);
		// Health at 2 seconds after taking dmg.
		assertEquals(24, a.getCurrentLifePoints());
	}
	/**
	 * Alien should recover every 2 ticks.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienCombatRecoveryAtRR2() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30, 2, new RecoveryLinear(1));
		SimpleTimer st = new SimpleTimer(1000);
		st.addTimeObserver(a);
		st.start();
		a.takeHit(10);
		// health between 0-2 seconds.
		assertEquals(20, a.getCurrentLifePoints());
		Thread.sleep(2250);
		// health a little bit after 2 seconds.
		assertEquals(21, a.getCurrentLifePoints());
	}
	/**
	 * Alien should recover every 3 ticks.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienCombatRecoveryAtRR3() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30, 3, new RecoveryLinear(4));
		SimpleTimer st = new SimpleTimer(1000);
		st.addTimeObserver(a);
		st.start();
		a.takeHit(10);
		// health between 0-3 seconds.
		assertEquals(20, a.getCurrentLifePoints());
		Thread.sleep(3250);
		// health a little bit after 3 seconds.
		assertEquals(24, a.getCurrentLifePoints());
	}
	/**
	 * Tests that we can remove an alien from the observer list.
	 * 
	 * @throws RecovRateIsNegative
	 * @throws InterruptedException
	 */
	@Test
	public void testAlienRemoveObserver() throws RecovRateIsNegative, InterruptedException {
		Alien a = new Alien("ET", 30, 2, new RecoveryLinear(4));
		SimpleTimer st = new SimpleTimer(1000);
		st.addTimeObserver(a);
		st.start();
		a.takeHit(10);
		// health between 0-3 seconds.
		assertEquals(20, a.getCurrentLifePoints());
		Thread.sleep(2250);
		// health a little bit after 3 seconds.
		assertEquals(24, a.getCurrentLifePoints());
		st.removeTimeObserver(a);
		Thread.sleep(2250);
		// Remains the same because we removedthe observers.
		assertEquals(24, a.getCurrentLifePoints());
	}
	// !!!!
	// STRATEGY PATTERN TESTS BEGIN HERE.
	// !!!!
	/**
	 * Tests the Aliens default recovery, along with that it also tests the
	 * recoverynone.
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testAlienDefaultRecovNone() throws RecovRateIsNegative {
		// Test creation and the default recovery, also tests the recoverynone
		// alien behavior.
		Alien a = new Alien("ET", 30);
		int startHP = a.getCurrentLifePoints();
		a.recover();
		int endHP = a.getCurrentLifePoints();
		assertEquals(startHP, endHP);
	}
	/**
	 * Tests the linear recovery type, the alien recovers exactly 3 hp, when the
	 * method is called.
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testAlienRecovLinear() throws RecovRateIsNegative {
		Alien a = new Alien("ET", 30, 1, new RecoveryLinear(3));
		a.takeHit(10);
		a.recover();
		assertEquals(23, a.getCurrentLifePoints());
	}
	/**
	 * Tests the fractional alien recovery, it will recover 50% of it's hp once
	 * the recover function is called.
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testAlienRecovFract() throws RecovRateIsNegative {
		Alien a = new Alien("ET", 30, 1, new RecoveryFractional(1.5));
		a.takeHit(10);
		a.recover();
		assertEquals((int) (20 * 1.5), a.getCurrentLifePoints(), 0);
	}
	/**
	 * Test double recovery
	 * 
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testAlienDoubleRecovery() throws RecovRateIsNegative {
		Alien a = new Alien("ET", 30, 1, new RecoveryLinear(3));
		a.takeHit(10);
		a.recover();
		assertEquals(23, a.getCurrentLifePoints());
		a.recover();
		assertEquals(26, a.getCurrentLifePoints());
	}
	
	@Test
	public void testMaxSpeed() throws RecovRateIsNegative{
		Alien a=new Alien("ET", 30, 1, new RecoveryLinear(3));
		assertEquals(2, a.getMaxSpeed());
	}
}