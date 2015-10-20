/**
 * Author: Jason LoBianco
 * Provides functionality to the user interface.
 */
package ui.command;

import environment.Environment;
import lifeform.LifeForm;

public class UserInterfaceBuilder 
{
	private String Commands[] = new String[9];
	
	public void CommandArray()
	{
		Commands[0] = new Reload();
		Commands[1] = new North();
		Commands[2] = new South();
		Commands[3] = new East();
		Commands[4] = new West();
		Commands[5] = new Move();
		Commands[6] = new Attack();
		Commands[7] = new Drop();
		Commands[8] = new Aquire();
		
	}
	
	
}
	
