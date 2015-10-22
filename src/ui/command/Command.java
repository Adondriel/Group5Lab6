package ui.command;

import java.awt.event.ActionEvent;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public interface Command
{
	/**
	 * when ever the command is called will do
	 * call the action of the corresponding button
	 */
	public void execute();

}
