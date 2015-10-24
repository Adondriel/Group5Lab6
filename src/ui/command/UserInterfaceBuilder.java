/**
 * Author: Jason LoBianco
 * Creates the UserInterface with all of the buttons.
 */
package ui.command;

import environment.Environment;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import lifeform.LifeForm;

@SuppressWarnings("serial")
public class UserInterfaceBuilder extends JPanel
{
	Environment e = Environment.getWorldInstance();
	UserInterface ui = new UserInterface();
	
	protected JButton reload, north, south, east, west, move, attack, drop, acquire;
	static String message = "Welcome";
	protected static LifeForm lifeform;
	
	/**
	 * Creates the Frame and Panel that all of the buttons go into.
	 */
	public UserInterfaceBuilder()
	{
		JFrame frame = new JFrame("Commands");
		frame.setVisible(true);
		frame.setSize(800, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		frame.getContentPane().add(buttonPanel, BorderLayout.WEST);
		GridBagConstraints c = new GridBagConstraints();
		
		ui.addCommand(0, new Reload());
		ui.addCommand(1, new North());
		ui.addCommand(2, new South());
		ui.addCommand(3, new East());
		ui.addCommand(4, new West());
		ui.addCommand(5, new Move());
		ui.addCommand(6, new Attack());
		ui.addCommand(7, new Drop());
		ui.addCommand(8, new Aquire());
		
		/**
		 * Creates the reload button with executeCommand.
		 */
		reload = new JButton("Reload");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10,10,10,10);
		buttonPanel.add(reload, c);
		reload.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(0, lifeform);
		}
		});
		
		/**
		 * Creates the North button with executeCommand.
		 */
		JButton north = new JButton("North");
		c.gridx = 4;
		c.gridy = 0;
		buttonPanel.add(north, c);
		north.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(1, lifeform);
		}
		});
		
		/**
		 * Creates the South button with executeCommand.
		 */
		JButton south = new JButton("South");
		c.gridx = 4;
		c.gridy = 2;
		buttonPanel.add(south, c);
		south.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(2, lifeform);
		}
		});
		
		/**
		 * Creates the East button with executeCommand.
		 */
		JButton east = new JButton("East");
		c.gridx = 5;
		c.gridy = 1;
		buttonPanel.add(east, c);
		east.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(3, lifeform);
		}
		});
		
		/**
		 * Creates the West button with executeCommand.
		 */
		JButton west = new JButton("West");
		c.gridx = 3;
		c.gridy = 1;
		buttonPanel.add(west, c);
		west.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(4, lifeform);
		}
		});
		
		/**
		 * Creates the Move button with executeCommand.
		 */
		JButton move = new JButton("M");
		c.gridx = 4;
		c.gridy = 1;
		buttonPanel.add(move, c);
		move.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(5, lifeform);
		}
		});
		
		/**
		 * Creates the Attack button with executeCommand.
		 */
		JButton attack = new JButton("Attack");
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(attack, c);
		attack.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(6, lifeform);
		}
		});
		
		/**
		 * Creates the Drop button with executeCommand.
		 */
		JButton drop = new JButton("Drop");
		c.gridx = 1;
		c.gridy = 2;
		buttonPanel.add(drop, c);
		drop.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(7, lifeform);
		}
		});
		
		/**
		 * Creates the Acquire button with executeCommand.
		 */
		JButton acquire = new JButton("Acquire");
		c.gridx = 1;
		c.gridy = 0;
		buttonPanel.add(acquire, c);
		acquire.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			ui.executeCommand(8, lifeform);
		}
		});
		
		/**
		 * Creates the Prompt message.
		 */
		JLabel prompt = new JLabel(message);
		c.gridx = 6;
		c.gridy = 0;
		buttonPanel.add(prompt, c);
		
		/**
		 * Creates the UserInput box.
		 */
		JTextField userinput = new JTextField(10);
		c.gridx = 6;
		c.gridy = 2;
		buttonPanel.add(userinput, c);
		
	}

	/**
	 * Sets a LifeForm to use the UserInterface.
	 * @param lf
	 */
	public void setLifeForm(LifeForm lf) 
	{
		lifeform = lf;
	}	
}