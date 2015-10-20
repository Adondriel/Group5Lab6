/**
 * Author: Jason LoBianco
 * Tests the functionality of the UserInterface.
 */
package ui.command;

import static org.junit.Assert.*;

import org.junit.Test;
import javax.swing.*;

public class TestUserInterface 
{

	/**
	 * Checks to see that the UserInterface is initialized correctly.
	 */
	@Test
	public void testInitialization() 
	{
		UserInterface ui = new UserInterface();
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

}
