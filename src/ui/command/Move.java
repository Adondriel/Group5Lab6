package ui.command;

import environment.Environment;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */
public class Move implements Command
{

	private LifeForm entity;
	Environment e = Environment.getWorldInstance();
	/**
	 * @param Get the Selected LifeForm to 
	 * perform the action
	 */
	
	@Override
	public void execute(LifeForm L)
	{
		//L.moved(); still needs work
	}

}
