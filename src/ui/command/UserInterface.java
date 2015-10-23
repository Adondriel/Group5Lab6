/**
 * Author: Jason LoBianco
 * The user interface of commands.
 */
package ui.command;

import javax.swing.*;

import lifeform.LifeForm;

@SuppressWarnings("serial")
public class UserInterface extends JFrame
{
	private Command[] Commands = new Command[9];
	private LifeForm lifeform;
	
	/**
	 * Creates the UserInterface.
	 * @param args
	 */
	public static void main(String[] args)
	{
		UserInterfaceBuilder ui = new UserInterfaceBuilder();
	}

	/**
	 * Adds the command to the command array.
	 * @param i the position in the array.
	 * @param command the specific command
	 */
	public void addCommand(int i, Command command)
	{
		Commands[i] = command;
	}

	public void executeCommand(int i, LifeForm lf) 
	{
		lifeform = lf;
		executeCommand(Commands[i], lifeform);
	}

	private void executeCommand(Command command, LifeForm lf) 
	{
		lifeform = lf;
		command.execute(lifeform);
	}
}