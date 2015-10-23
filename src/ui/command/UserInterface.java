/**
 * Author: Jason LoBianco
 * The user interface of commands.
 */
package ui.command;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterface extends JFrame
{
	private Command[] Commands = new Command[9];
	
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

	/**
	 * Execute the command based on the number from the command array.
	 * @param i
	 */
	public void executeCommand(int i) 
	{
		executeCommand(Commands[i]);	
	}

	/**
	 * Execute the command.
	 * @param command
	 */
	private void executeCommand(Command command) 
	{
		command.execute();	
	}
}