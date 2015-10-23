package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Exceptions.RecovRateIsNegative;
import environment.Cell;
import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import net.miginfocom.swing.MigLayout;
import ui.command.UserInterface;
import weapon.Attachment;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.PowerBooster;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.Weapon;

/**
 * @author Adam Pine
 * The main GUI, used for the Map, Cell info, Lifeform Info, and Legend.
 */
public class GUI extends JFrame implements ActionListener {
	JButton textButton, imageButton;
	// Cell info labels
	JLabel cellInfoHeader, numOfWeaponsLabel, numOfWeaponsValueLabel, weaponListHeaderLabel, weaponListWeapon1,
			weaponListWeapon2, cellLfTypeLabel, cellAttachmentListLabel, cellAttachmentListValue,
			cellAttachmentListLabel2, cellAttachmentListValue2;
	// Player info labels
	JLabel lifeFormInfoHeader, lfTypeLabel, lfTypeValueLabel, lfWeaponTypeValue, lfWeaponTypeLabel, lfWeaponAmmoLabel,
			lfWeaponAmmoValue, lfWeaponAmmoOutOfSign, lfWeaponAmmoMaxValue, lfNameLabel, lfNameValue, lfHpLabel,
			lfHpValue, lfAttachmentLabel, lfAttachmentValue;

	Environment e = Environment.getWorldInstance(5, 5);
	public static GUI globalGUI = new GUI();
	public static UserInterface cmdUI = new UserInterface();
	int gridRows = e.getMaxRow();
	int gridCols = e.getMaxCol();

	//Groups of image icons, sorted by alien,human and other.
	ImageIcon alienIcon, alienIconEast, alienIconSouth, alienIconWest = null;
	ImageIcon humanIcon, humanIconEast, humanIconSouth, humanIconWest = null;
	ImageIcon defaultIcon, weaponIcon = null;

	//the buttonArray corresponds to theCells array.
	JButton[][] buttonArray = new JButton[gridRows][gridCols];
	Cell[][] theCells = e.getCellsArray();
	JPanel map = new JPanel(new MigLayout("wrap " + gridCols));

	public static void main(String[] args) {}
	//The main GUI code, this builds and displays the entire map/cell/lifeform/legend UI.
	public GUI() {
		try {
			// north
			alienIcon = new ImageIcon(ImageIO.read(new File("resources/alieniconnorth.png")));
			humanIcon = new ImageIcon(ImageIO.read(new File("resources/humaniconnorth.png")));
			// east
			alienIconEast = new ImageIcon(ImageIO.read(new File("resources/alieniconeast.png")));
			humanIconEast = new ImageIcon(ImageIO.read(new File("resources/humaniconeast.png")));
			// south
			alienIconSouth = new ImageIcon(ImageIO.read(new File("resources/alieniconsouth.png")));
			humanIconSouth = new ImageIcon(ImageIO.read(new File("resources/humaniconsouth.png")));
			// west
			alienIconWest = new ImageIcon(ImageIO.read(new File("resources/alieniconwest.png")));
			humanIconWest = new ImageIcon(ImageIO.read(new File("resources/humaniconwest.png")));
			// defaultIcon
			defaultIcon = new ImageIcon(ImageIO.read(new File("resources/default.png")));
			weaponIcon = new ImageIcon(ImageIO.read(new File("resources/weaponIcon.png")));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFrame mapGui = new JFrame("MapGui");
		mapGui.setLayout(new MigLayout("wrap 6"));
		mapGui.setBounds(1000, 0, 900, 225);
		mapGui.setTitle("Map Gui");
		//Create all of the cells.
		for (int r = 0; r < gridRows; r++) {
			for (int c = 0; c < gridCols; c++) {
				LifeForm lf = theCells[r][c].getLifeForm();
				buttonArray[r][c] = new JButton(defaultIcon);
				buttonArray[r][c].setPreferredSize(new Dimension(15, 15));
				buttonArray[r][c].addActionListener(this);
				buttonArray[r][c].setBorderPainted(false);
				buttonArray[r][c].setFocusPainted(false);
				//give it a nice, sleek look instead of ugly gradient.
				buttonArray[r][c].setBackground(Color.LIGHT_GRAY);
				map.add(buttonArray[r][c]);
			}
		}
		mapGui.add(map);

		// The cell info
		// Displays info about the clicked cell.
		JPanel cellInfo = new JPanel(new MigLayout("wrap 2"));
		cellInfoHeader = new JLabel("Cell Info");
		numOfWeaponsLabel = new JLabel("# of Weapons: ");
		numOfWeaponsValueLabel = new JLabel("");
		weaponListHeaderLabel = new JLabel("Weapons List:");
		weaponListWeapon1 = new JLabel("");
		cellAttachmentListLabel = new JLabel("Attachments of Weapon 1:");
		cellAttachmentListValue = new JLabel("");
		cellAttachmentListLabel2 = new JLabel("Attachments of Weapon 2:");
		cellAttachmentListValue2 = new JLabel("");
		weaponListWeapon2 = new JLabel("");

		//Add all the cell labels to the cell panel.
		cellInfo.add(cellInfoHeader, "span 2");
		cellInfo.add(numOfWeaponsLabel);
		cellInfo.add(numOfWeaponsValueLabel);
		cellInfo.add(weaponListHeaderLabel, "span 2");
		cellInfo.add(weaponListWeapon1);
		cellInfo.add(weaponListWeapon2);
		cellInfo.add(cellAttachmentListLabel, "span 2");
		cellInfo.add(cellAttachmentListValue, "span 2");
		cellInfo.add(cellAttachmentListLabel2, "span 2");
		cellInfo.add(cellAttachmentListValue2, "span 2");
		mapGui.add(cellInfo);

		// This is the player's info
		// This contains all info about the players.
		JPanel lifeFormInfo = new JPanel(new MigLayout("wrap 2"));
		lifeFormInfoHeader = new JLabel("LifeForm Info");
		lfTypeLabel = new JLabel("LF Type: ");
		lfTypeValueLabel = new JLabel("");
		lfNameLabel = new JLabel("Name: ");
		lfNameValue = new JLabel("");
		lfHpLabel = new JLabel("HP: ");
		lfHpValue = new JLabel("");
		lfWeaponTypeLabel = new JLabel("Weapon Type: ");
		lfWeaponTypeValue = new JLabel("");
		lfAttachmentLabel = new JLabel("Attachments: ");
		lfAttachmentValue = new JLabel("");
		lfWeaponAmmoLabel = new JLabel("Ammo: ");
		lfWeaponAmmoValue = new JLabel("");

		//add all the labels to the lifeforminfo panel.
		lifeFormInfo.add(lifeFormInfoHeader, "span 2");
		lifeFormInfo.add(lfTypeLabel);
		lifeFormInfo.add(lfTypeValueLabel);
		lifeFormInfo.add(lfNameLabel);
		lifeFormInfo.add(lfNameValue);
		lifeFormInfo.add(lfHpLabel);
		lifeFormInfo.add(lfHpValue);
		lifeFormInfo.add(lfWeaponTypeLabel);
		lifeFormInfo.add(lfWeaponTypeValue);
		lifeFormInfo.add(lfAttachmentLabel);
		lifeFormInfo.add(lfAttachmentValue);
		lifeFormInfo.add(lfWeaponAmmoLabel);
		lifeFormInfo.add(lfWeaponAmmoValue);
		mapGui.add(lifeFormInfo);

		JPanel legend = new JPanel(new MigLayout("wrap 2"));
		JLabel legendHeader = new JLabel("Legend");
		JLabel legendHumanIcon = new JLabel(humanIcon);
		JLabel legendHumanText = new JLabel("= Human");
		JLabel legendAlienIcon = new JLabel(alienIcon);
		JLabel legendAlienText = new JLabel("= Alien");
		JLabel legendWeaponIcon = new JLabel(weaponIcon);
		JLabel legendWeaponText = new JLabel(">= 1 Weapon");
		JLabel helpText = new JLabel("Click on a cell to select it!");
		
		//Add all the legend info to the legend panel.
		legend.add(legendHeader, "span 2");
		legend.add(legendHumanIcon);
		legend.add(legendHumanText);
		legend.add(legendAlienIcon);
		legend.add(legendAlienText);
		legend.add(legendWeaponIcon);
		legend.add(legendWeaponText);
		
		mapGui.add(legend);
		mapGui.add(helpText, "span");

		mapGui.setVisible(true);
		weaponListWeapon1.setVisible(false);
		weaponListWeapon2.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int r = 0; r < gridRows; r++) {
			for (int c = 0; c < gridCols; c++) {
				if (e.getSource() == buttonArray[r][c]) {
					LifeForm localLF = theCells[r][c].getLifeForm();
					Cell localCell = theCells[r][c];
					numOfWeaponsValueLabel.setText(String.valueOf(localCell.getWeapons().size()));
					//Determine if there are weapons in the cell, if 0, don't display them.
					if (localCell.getWeapons().size() == 0) {
						weaponListWeapon1.setVisible(false);
						weaponListWeapon2.setVisible(false);
					}
					//If there is only 1 weapon, display that weapon, and display it's attachment.
					if (localCell.getWeapons().size() == 1) {
						weaponListWeapon1.setText(getWeaponType(localCell.getWeaponAtIndex(0)));
						if (localCell.getWeaponAtIndex(0) instanceof Attachment){
							cellAttachmentListValue.setText(getAttachmentList(localCell.getAttachmentAtIndex(0)));
							cellAttachmentListValue.setVisible(true);
							cellAttachmentListLabel.setVisible(true);
							cellAttachmentListValue2.setVisible(false);
							cellAttachmentListLabel2.setVisible(false);
						}
						weaponListWeapon1.setVisible(true);
						weaponListWeapon2.setVisible(false);
					}
					//If there are 2 weapons, display both weapons and display both weapon's attachments.
					if (localCell.getWeapons().size() == 2) {
						weaponListWeapon1.setText(getWeaponType(localCell.getWeaponAtIndex(0)));
						weaponListWeapon2.setText(getWeaponType(localCell.getWeaponAtIndex(1)));
						if (localCell.getWeaponAtIndex(0) instanceof Attachment){
							cellAttachmentListValue.setText(getAttachmentList(localCell.getAttachmentAtIndex(0)));
							cellAttachmentListValue.setVisible(true);
							cellAttachmentListLabel.setVisible(true);
						}
						if (localCell.getWeaponAtIndex(1) instanceof Attachment){
							cellAttachmentListValue2.setText(getAttachmentList(localCell.getAttachmentAtIndex(1)));
							cellAttachmentListValue2.setVisible(true);
							cellAttachmentListLabel2.setVisible(true);
						}

						weaponListWeapon1.setVisible(true);
						weaponListWeapon2.setVisible(true);
					}
					//Lifeform update section
					if (localLF != null) {
						//update the lifeform name.
						lfNameValue.setText(localLF.getName());
						//update the lifeform hp.
						lfHpValue.setText(String.valueOf(localLF.getCurrentLifePoints()));
						//detect the type of lifeform, if human update the text.
						if (localLF instanceof Human) {
							lfTypeValueLabel.setText("Human");
						}
						//detect the type of lifeform, if human update the text.
						if (localLF instanceof Alien) {
							lfTypeValueLabel.setText("Alien");
						}
						//if the lifeform has a weapon, update the weapon type.
						if (localLF.getWeapon() != null) {
							lfWeaponTypeValue.setText(getWeaponType(localLF.getWeapon()));
							//update the lifeform's weapon's ammo.
							lfWeaponAmmoValue.setText(String.valueOf(localLF.getWeapon().getCurrentAmmo()) + "/"
									+ String.valueOf(localLF.getWeapon().getMaxAmmo()));
							if (localLF.getWeapon() instanceof Attachment){
								//if the weapon is an attachment, determine the attachment list.
								lfAttachmentValue.setText(getAttachmentList((Attachment)localLF.getWeapon()));
							} else {
								//if it is just a weapon, make sure it doesn't have an attachment.
								lfAttachmentValue.setText("");
							}							
						} else {
							lfAttachmentValue.setText("");
							lfWeaponTypeValue.setText("");
							lfWeaponAmmoValue.setText("");
						}
					} else {
						lfAttachmentValue.setText("");
						lfHpValue.setText("");
						lfNameValue.setText("");
						lfTypeValueLabel.setText("");
						lfWeaponTypeValue.setText("");
						lfWeaponAmmoValue.setText("");
					}

				}
			}
		}
	}

	/**
	 * When called, will add an icon to the button, at the specified location, based on the LifeForm given.
	 * @param lf
	 * @param row
	 * @param col
	 * @throws RecovRateIsNegative
	 */
	public void addLifeFormEvent(LifeForm lf, int row, int col) throws RecovRateIsNegative {
		if (lf instanceof Alien) {
			buttonArray[row][col].setIcon(getAlienIcon(lf));
		}
		if (lf instanceof Human) {
			buttonArray[row][col].setIcon(getHumanIcon(lf));
		}
	}

	/**
	 * Remove a lifeform Icon from the specified location.
	 * @param row
	 * @param col
	 */
	public void removeLifeFormEvent(int row, int col) {
		buttonArray[row][col].setIcon(defaultIcon);
	}

	/**
	 * Call when you add a weapon to theCells, adds a Weapon Icon to the button.
	 * @param w
	 * @param row
	 * @param col
	 */
	public void weaponAddedEvent(Weapon w, int row, int col) {
		buttonArray[row][col].setIcon(weaponIcon);
	}
	
	/**
	 * Remove a weapon icon from the buttons.
	 * @param w
	 * @param row
	 * @param col
	 */
	public void weaponRemovedEvent(Weapon w, int row, int col) {
		buttonArray[row][col].setIcon(defaultIcon);
	}

	/**
	 * Determines what icon to use, based on the LifeForm passed in.
	 * @param lf
	 * @return ImageIcon
	 */
	private ImageIcon getAlienIcon(LifeForm lf) {
		if (lf.getDirection() == 'n') {
			return alienIcon;
		}
		if (lf.getDirection() == 'e') {
			return alienIconEast;
		}
		if (lf.getDirection() == 's') {
			return alienIconSouth;
		}
		if (lf.getDirection() == 'w') {
			return alienIconWest;
		} else {
			return alienIcon;
		}
	}
	
	/**
	 * Determines what icon to use, based on the LifeForm passed in.
	 * @param lf
	 * @return ImageIcon
	 */
	private ImageIcon getHumanIcon(LifeForm lf) {
		if (lf.getDirection() == 'n') {
			return humanIcon;
		}
		if (lf.getDirection() == 'e') {
			return humanIconEast;
		}
		if (lf.getDirection() == 's') {
			return humanIconSouth;
		}
		if (lf.getDirection() == 'w') {
			return humanIconWest;
		} else {
			return humanIcon;
		}
	}

	/**
	 * Determines the weapon type of the weapon passed in
	 * @param w
	 * @return String
	 */
	private String getWeaponType(Weapon w) {
		if (w instanceof Attachment) {
			Attachment aw = ((Attachment) w);
			if (aw.getBaseWeapon() instanceof Attachment) {
				w = aw.getBaseAttachment().getBaseWeapon();
			} else {
				w = aw.getBaseWeapon();
			}
		}
		if (w instanceof Pistol) {
			return "Pistol";
		}
		if (w instanceof ChainGun) {
			return "Chain Gun";
		}
		if (w instanceof PlasmaCannon) {
			return "PlasmaCannon";
		}
		return "none";
	}

	/**
	 * Returns a string, which is a list of all the attachments on a weapon.
	 * @param a
	 * @return
	 */
	private String getAttachmentList(Attachment a) {
		String rString = "";
		/*
		 * if (a instanceof Weapon){ return getWeaponType((Weapon)a); }
		 */
		rString = a.getClass().getName().substring(7);
		//change "if" to a "while" and it would be infinite... to support more than 2 
		if (a.getBaseWeapon() instanceof Attachment) {
			rString = rString + ", " + a.getBaseWeapon().getClass().getName().substring(7);
		}
		return rString;
	}
}
