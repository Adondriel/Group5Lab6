package ui.command;

import environment.Environment;
import lifeform.LifeForm;

/**
 * 
 * @author Bradley Solorzano
 *	Design Pattners Group 5 lab 6
 */

public class East implements Command
{

private LifeForm entity;
	
	@Override
	public void execute(LifeForm lf) 
	{
		entity = lf;
		entity.turnEast();	
	}
}
