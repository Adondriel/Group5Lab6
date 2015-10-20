/**
 * Author: Jason LoBianco
 * The user interface of commands.
 */
package ui.command;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame
{
	
	/**
	 * Creates the UserInterface with all of the buttons and text boxes.
	 */
	static String action = null;
	static String message = "Welcome";
	public UserInterface()
	{
		JFrame frame = new JFrame("Commands");
		frame.setVisible(true);
		frame.setSize(800, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();
		
		JButton reload = new JButton("Reload");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10,10,10,10);
		buttonPanel.add(reload, c);
		reload.addActionListener(new Action());
		
		JButton north = new JButton("North");
		c.gridx = 4;
		c.gridy = 0;
		buttonPanel.add(north, c);
		north.addActionListener(new Action());
		
		JButton south = new JButton("South");
		c.gridx = 4;
		c.gridy = 2;
		buttonPanel.add(south, c);
		south.addActionListener(new Action());
		
		JButton east = new JButton("East");
		c.gridx = 5;
		c.gridy = 1;
		buttonPanel.add(east, c);
		east.addActionListener(new Action());
		
		JButton west = new JButton("West");
		c.gridx = 3;
		c.gridy = 1;
		buttonPanel.add(west, c);
		west.addActionListener(new Action());
		
		JButton move = new JButton("M");
		c.gridx = 4;
		c.gridy = 1;
		buttonPanel.add(move, c);
		move.addActionListener(new Action());
		
		JButton attack = new JButton("Attack");
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(attack, c);
		attack.addActionListener(new Action());
		
		JButton drop = new JButton("Drop");
		c.gridx = 1;
		c.gridy = 2;
		buttonPanel.add(drop, c);
		drop.addActionListener(new Action());
		
		JButton acquire = new JButton("Acquire");
		c.gridx = 1;
		c.gridy = 0;
		buttonPanel.add(acquire, c);
		acquire.addActionListener(new Action());
		
		JLabel prompt = new JLabel(message);
		c.gridx = 6;
		c.gridy = 0;
		buttonPanel.add(prompt, c);
		
		JTextField userinput = new JTextField(10);
		c.gridx = 6;
		c.gridy = 2;
		buttonPanel.add(userinput, c);
	}
	
	/**
	 * Returns the action performed as a string.
	 */
	static class Action implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			action = e.getActionCommand();
		}
	}
	
	/**
	 * Creates the UserInterface.
	 * @param args
	 */
	public static void main(String[] args)
	{
		UserInterface ui = new UserInterface();
	}
}
