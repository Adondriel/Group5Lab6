/**
 * @author Adam Pine, Benjamin Uleau
 * Used to test the Environment Class.
 */

package environment;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import recovery.RecoveryFractional;
import Exceptions.EnvironmentException;
import Exceptions.RecovRateIsNegative;
import environment.Environment;
import gameplay.SimpleTimer;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;
//Just to make sure testInit() is run first, because singleton and what not...
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEnvironment {
	Environment e = Environment.getWorldInstance(5, 5);
	@Before
	public void clearBoard(){
		e.ClearBoard();
	}
	/**
	 * Tests the init of the environment. 1 was added so that it is always run first.
	 */
	@Test
	public void test1Init() {
		for(int i=0; i < 5; i++){
			for (int j=0; j<5; j++){
				assertNull(e.getLifeForm(i, j));
			}
		}		
	}	
	/**
	 * Test to show that even if I try to create another world,
	 * with a large size it will not work because we have
	 * already declared an environment.
	 * Proving that it is correctly initialized as a singleton!
	 */
	@Test
	public void Test2initAsSingleton(){
		//try to create larger world.
		Environment e2 = Environment.getWorldInstance(6, 6);
		//show that this new "larger" world is equal to the smaller world!
		assertEquals(e, e2);
	}	
	/**
	 * Tests adding a removing of a single weapon.
	 * If you are going to remove a weapon, you will know what weapon to remove.
	 */
	@Test
	public void testAddAndRemoveWeapon() {	
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		assertTrue(e.addWeapon(0, 0, w1));
		assertTrue(e.removeWeapon(0, 0, w1));
		assertTrue(e.addWeapon(0, 0, w2));
	}	
	/**
	 * Tests getting the distance between lifeforms:
	 * 		along the same row,
	 * 		along the same column,
	 * 		and NOT along the same row OR column.
	 * @throws EnvironmentException
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testGetdistanceBetweenLifeForms() throws EnvironmentException, RecovRateIsNegative{
		LifeForm l1 = new MockLifeForm("Bob", 30);
		LifeForm l2 = new MockLifeForm("Steve", 30);
		LifeForm l3 = new MockLifeForm("Harold", 30);
		e.addLifeForm(0, 0, l1);
		e.addLifeForm(0, 2, l2);
		e.addLifeForm(3, 2, l3);
		//same row
		assertEquals(10, e.getDistance(l1, l2));
		//same column
		assertEquals(15, e.getDistance(l2, l3));
		//not the same column or row. (diagonal!)
		assertEquals(18, e.getDistance(l1, l3));
	}	
	/**
	 * EXTRA TEST
	 * This test's the get distance method itself, without the use of a LifeForm.
	 * @throws EnvironmentException 
	 */
	@Test
	public void testGetDistanceBetweenCoords() throws EnvironmentException{
		//3 apart
		assertEquals(15, e.getDistanceBetweenCoords(0, 2, 3, 2));
		//2 apart
		assertEquals(10, e.getDistanceBetweenCoords(0, 0, 0, 2));
		//diagonal
		assertEquals(18, e.getDistanceBetweenCoords(0, 0, 3, 2));
		//top left corner, bottom right corner
		assertEquals(28, e.getDistanceBetweenCoords(0, 0, 4, 4));
		//some more tests
		assertEquals(14, e.getDistanceBetweenCoords(0, 1, 2, 3));
		//range is zero, they are on top of each other. This could be an error if desired.
		assertEquals(0,  e.getDistanceBetweenCoords(0, 2, 0, 2));
	}		
	/**
	 * EXTRA TEST
	 * Tests adding of multiple weapons.
	 * @throws CloneNotSupportedException 
	 */
	@Test
	public void testAddMultipleWeapons() throws CloneNotSupportedException{
		//Had to add final to these for some reason
		final Weapon w1 = new Pistol();
		final Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		assertTrue(e.addWeapon(0, 1, w1));
		assertTrue(e.addWeapon(0, 1, w2));
		//oh no we added more than two!
		assertFalse(e.addWeapon(0, 1, w3));
		//it just won't add the weapon
		assertEquals(new ArrayList<Weapon>() {{add(w1); add(w2);}}, e.getCellAt(0, 1).getWeapons());
	}	
	/**
	 * EXTRA TEST
	 * Test that e.getCellAt() does not allow you to modify the cells.
	 * Had to add a clone method to my Cell to allow for this to work :D
	 * @throws CloneNotSupportedException 
	 * @throws RecovRateIsNegative 
	 */
	public void testEnvironmentGetCellAt() throws CloneNotSupportedException, RecovRateIsNegative{
		Cell badCell = new Cell();
		LifeForm lf = new Human("steve", 30, 20);
		
		//Had to add final to these for some reason
		final Weapon w = new Pistol();
		final Weapon w2 = new ChainGun();
		e.addLifeForm(0, 0, lf);
		e.addWeapon(0, 0, w);
		e.addWeapon(0, 0, w2);
		Cell origCell = e.getCellAt(0, 0);
		origCell = badCell;
		assertEquals(new ArrayList<Weapon>() {{add(w); add(w2);}}, e.getCellAt(0, 1).getWeapons());
	}	
	/**
	 * EXTRA TEST
	 * Tests removing too many weapons.
	 */
	@Test
	public void testRemoveTooManyWeapons(){
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		assertTrue(e.addWeapon(1, 1, w1));
		assertTrue(e.addWeapon(1, 1, w2));
		assertFalse(e.addWeapon(1, 1, w3));
		assertTrue(e.removeWeapon(1, 1, w1));
		assertTrue(e.removeWeapon(1,1,w2));
		assertFalse(e.removeWeapon(1,1,w1));
	}	
	/**
	 * EXTRA TEST
	 * Tests to make sure we do not return null, if the cell was not created first.
	 */
	/*@Test
	public void test2RemoveWeaponIfCellDoesNotExist(){
		Weapon w1 = new Pistol();
		Weapon w2 = new ChainGun();
		Weapon w3 = new PlasmaCannon();
		assertFalse(e.removeWeapon(2, 1, w1));
		assertFalse(e.removeWeapon(2,1, w2));
		assertFalse(e.removeWeapon(2,1, w1));
	}	*/
	//
	//OLD TESTS
	//Had to modify them, to use new locations because of the singleton but that is all.
	//Then I saw the very last paragraph in the manual about @Before...
	//
	/**
	 * Tests the addLifeForm function of the Environment class.
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testAddLifeForm() throws RecovRateIsNegative {
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm steve = new MockLifeForm("Steve", 40);		
		assertTrue(e.addLifeForm(0, 1, bob));
		assertTrue(e.addLifeForm(1, 1, steve));
		assertFalse(e.addLifeForm(0, 1, steve));
		assertFalse(e.addLifeForm(1, 1, bob));
		assertFalse(e.addLifeForm(6, 6, bob));
	}
	/**
	 * Tests the removeLifeForm function of the environment class.
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testRemoveLifeForm() throws RecovRateIsNegative {
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm steve = new MockLifeForm("Steve", 40);
		e.addLifeForm(2, 0, bob);
		e.addLifeForm(2, 1, steve);
		assertEquals(bob, e.removeLifeForm(2, 0));
		assertNull(e.removeLifeForm(4, 0));
	}
	/**
	 * Tests the getLifeForm function of the environment class.
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testGetLifeForm() throws RecovRateIsNegative {
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm steve = new MockLifeForm("Steve", 40);
		e.addLifeForm(3, 0, bob);
		e.addLifeForm(3, 1, steve);
		assertEquals(bob, e.getLifeForm(3, 0));
		assertEquals(steve, e.getLifeForm(3, 1));
	}
	
	/**
	 * Test human movement mechanics
	 * @throws CloneNotSupportedException
	 * @throws RecovRateIsNegative 
	 */
	@Test
	public void testMoveHuman() throws CloneNotSupportedException, RecovRateIsNegative{
		//The humans
		Human h1=new Human("h1", 30, 30);
		Human h2=new Human("h2", 30, 30);
		Human h3=new Human("h3", 30, 30);
		Human h4=new Human("h4", 30, 30);
		Human h5=new Human("h5", 30, 30);
		SimpleTimer st=new SimpleTimer(100);
		st.addTimeObserver(h1);
		st.addTimeObserver(h2);
		
		e.ClearBoard();
		
		e.addLifeForm(2, 2, h1);
		
		//Make sure the human can move north
		//Also serves as initial test to make sure that movement works
		e.step(2, 2);
		assertEquals(h1, e.getCellAt(1, 2).getLifeForm());
		
		//Make sure the human gets removed from the cell after its moved north
		//EXTRA TEST
		assertNull(e.getCellAt(2, 2).getLifeForm());
		
		//Make sure the human can move east
		//EXTRA TEST
		h1.turnRight();
		assertTrue(e.step(1, 2));
		assertEquals(h1, e.getCellAt(1, 3).getLifeForm());
		
		//Make sure the human can move south
		h1.turnRight();
		e.step(1, 3);
		assertEquals(h1, e.getCellAt(2, 3).getLifeForm());
		
		//Make sure the human cannot go more than its maximum moves in one turn
		//EXTRA TEST
		h1.turnRight();
		assertFalse(e.step(2, 3));
		
		//Make sure the human can move west
		//EXTRA TEST
		st.timeChanged();
		assertTrue(e.step(2, 3));
		assertEquals(h1, e.getCellAt(2, 2).getLifeForm());
		
		h1.turnNorth();
		
		e.addLifeForm(1, 2, h2);
		
		st.timeChanged();
		
		//Make sure a human can move more than 1 space and that its current moves are updated accordingly
		//EXTRA TEST
		assertTrue(e.stepNSpaces(2, 2, 2));
		
		//EXTRA TEST
		assertEquals(2, h1.getCurrentMoves());
		assertEquals(h1, e.getCellAt(0, 2).getLifeForm());
		
		//Make sure a human can't go off the north side of the environment
		assertFalse(e.step(0, 2));
		st.timeChanged();
		
		//Make sure a human can't go off the west side of the environment
		//EXTRA TEST
		h2.turnLeft();
		assertFalse(e.stepNSpaces(1, 2, 3));
		
		st.timeChanged();
		
		e.ClearBoard();
		e.addLifeForm(4, 0, h1);
		e.addLifeForm(0, 4, h2);
		h1.turnSouth();
		h2.turnEast();
		//Make sure a human can't go off the south side of the environment
		assertFalse(e.stepNSpaces(4, 0, 3));
		
		//Make sure a human can't go off the east side fothe environment
		assertFalse(e.stepNSpaces(0, 4, 3));
		
		e.ClearBoard();
		e.addLifeForm(2, 2, h1);
		e.addLifeForm(1, 2, h2);
		e.addLifeForm(3, 2, h3);
		e.addLifeForm(2, 3, h4);
		e.addLifeForm(2, 1, h5);
		
		//Make sure a human can't step north into an occupied cell
		assertFalse(e.step(2, 2));
		
		//Make sure a human can't step east into an occupied cell
		//EXTRA TEST
		h1.turnEast();
		assertFalse(e.step(2, 2));
		
		//Make sure a human can't step south into an occupied cell
		h1.turnSouth();
		assertFalse(e.step(2, 2));
		
		st.timeChanged();
		
		h1.turnWest();
		assertFalse(e.step(2, 2));
	}
	
	/**
	 * Test alien movement mechanics
	 * @throws RecovRateIsNegative
	 */
	@Test
	public void testMoveAlien() throws RecovRateIsNegative{
		Alien a1 = new Alien("a1", 30, 1, new RecoveryFractional(1.5));
		Alien a2 = new Alien("a2", 30, 1, new RecoveryFractional(1.5));
		
		SimpleTimer st=new SimpleTimer(100);
		st.addTimeObserver(a1);
		st.addTimeObserver(a2);
		
		e.ClearBoard();
		
		e.addLifeForm(2, 1, a1);
		
		//Make sure aliens can move east
		a1.turnEast();
		assertTrue(e.step(2, 1));
		assertEquals(a1, e.getLifeForm(2, 2));
		
		//Make sure aliens can move west
		a1.turnWest();
		assertTrue(e.step(2, 2));
		assertEquals(a1, e.getLifeForm(2, 1));
		
		//Make sure an alien can't go over its maximum moves
		//EXTRA TEST
		assertFalse(e.step(2, 1));
		
		st.timeChanged();
		e.addLifeForm(2, 2, a2);
		
		a1.turnEast();
		a2.turnWest();
		
		//Make sure aliens can't step east into an occupied cell
		assertFalse(e.step(2, 1));
		
		//Make sure aliens can't step west into an occupied cell
		assertFalse(e.step(2, 2));
		
		e.ClearBoard();
		st.timeChanged();
		
		e.addLifeForm(0, 0, a1);
		e.addLifeForm(4, 4, a2);
		
		a1.turnWest();
		a2.turnEast();
		//Make sure aliens can't go off the east side of the world
		//And make sure stepNSpaces is usable by aliens
		assertFalse(e.stepNSpaces(4, 4, 2));
		
		//Make sure aliens can't go off the west side of the world
		//And make sure stepNSpaces is usable by aliens
		assertFalse(e.stepNSpaces(0, 0, 2));
	}
	
	/**
	 * Used to help remember where each lifeform is stored.
	 */
	public void listLifeForms(){
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(e.getLifeForm(i, j)!=null)
					System.out.println(i+", "+j+": "+e.getLifeForm(i, j).getName()+"->"+e.getLifeForm(i, j).getDirection());
			}
		}
	}
}