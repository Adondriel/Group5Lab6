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
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

public class GUI extends JFrame implements ActionListener {
	JButton textButton, imageButton;
	// Cell info labels
	JLabel cellInfoHeader, numOfWeaponsLabel, numOfWeaponsValueLabel, weaponListHeaderLabel, weaponListWeapon1,
			weaponListWeapon2, cellLfTypeLabel;
	// Player info labels
	JLabel lifeFormInfoHeader, lfTypeLabel, lfTypeValueLabel, lfWeaponTypeValue, lfWeaponTypeLabel,lfWeaponAmmoLabel,lfWeaponAmmoValue,
	lfWeaponAmmoOutOfSign, lfWeaponAmmoMaxValue;

	Environment e = Environment.getWorldInstance(5, 5);
	public static GUI globalGUI = new GUI();
	int gridRows = e.getMaxRow();
	int gridCols = e.getMaxCol();

	ImageIcon alienIcon, alienIconEast, alienIconSouth, alienIconWest = null;
	ImageIcon humanIcon, humanIconEast, humanIconSouth, humanIconWest = null;
	ImageIcon defaultIcon, weaponIcon = null;

	JButton[][] buttonArray = new JButton[gridRows][gridCols];
	Cell[][] theCells = e.getCellsArray();
	JPanel map = new JPanel(new MigLayout("wrap " + gridCols));

	public static void main(String[] args) {

	}

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
		setLayout(new MigLayout("wrap 6"));

		for (int r = 0; r < gridRows; r++) {
			for (int c = 0; c < gridCols; c++) {
				LifeForm lf = theCells[r][c].getLifeForm();
				buttonArray[r][c] = new JButton(defaultIcon);
				buttonArray[r][c].setPreferredSize(new Dimension(15, 15));
				buttonArray[r][c].addActionListener(this);
				buttonArray[r][c].setBorderPainted(false);
				buttonArray[r][c].setFocusPainted(false);
				buttonArray[r][c].setBackground(Color.LIGHT_GRAY);
				map.add(buttonArray[r][c]);
			}
		}
		add(map);

		// The cell info
		// Displays info about the clicked cell.
		JPanel cellInfo = new JPanel(new MigLayout("wrap 2"));
		cellInfoHeader = new JLabel("Cell Info");
		numOfWeaponsLabel = new JLabel("# of Weapons: ");
		numOfWeaponsValueLabel = new JLabel(String.valueOf(theCells[0][0].getWeapons().size()));
		weaponListHeaderLabel = new JLabel("Weapons List:");
		weaponListWeapon1 = new JLabel("N/A");
		weaponListWeapon2 = new JLabel("N/A");

		cellInfo.add(cellInfoHeader, "span 2");
		cellInfo.add(numOfWeaponsLabel);
		cellInfo.add(numOfWeaponsValueLabel);
		cellInfo.add(weaponListHeaderLabel, "span 2");
		cellInfo.add(weaponListWeapon1);
		cellInfo.add(weaponListWeapon2);
		add(cellInfo);

		// This is the player's info
		// This contains all info about the players.
		JPanel lifeFormInfo = new JPanel(new MigLayout("wrap 2"));
		lifeFormInfoHeader = new JLabel("LifeForm Info");
		lfTypeLabel = new JLabel("LF Type: ");
		lfTypeValueLabel = new JLabel("");
		lfWeaponTypeLabel = new JLabel("Weapon Type: ");
		lfWeaponTypeValue = new JLabel("");
		lfWeaponAmmoLabel = new JLabel("Ammo: ");
		lfWeaponAmmoValue = new JLabel("");
		lfWeaponAmmoOutOfSign = new JLabel("");
		lfWeaponAmmoMaxValue = new JLabel("");

		lifeFormInfo.add(lifeFormInfoHeader, "span 2");
		lifeFormInfo.add(lfTypeLabel);
		lifeFormInfo.add(lfTypeValueLabel);
		lifeFormInfo.add(lfWeaponTypeLabel);
		lifeFormInfo.add(lfWeaponTypeValue);
		lifeFormInfo.add(lfWeaponAmmoLabel);
		lifeFormInfo.add(lfWeaponAmmoValue);
		//lifeFormInfo.add(lfWeaponAmmoOutOfSign);
		//lifeFormInfo.add(lfWeaponAmmoMaxValue);
		add(lifeFormInfo);

		JPanel legend = new JPanel(new MigLayout("wrap 2"));
		JLabel legendHeader = new JLabel("Legend");
		JLabel legendHumanIcon = new JLabel(humanIcon);
		JLabel legendHumanText = new JLabel("= Human");
		JLabel legendAlienIcon = new JLabel(alienIcon);
		JLabel legendAlienText = new JLabel("= Alien");
		JLabel legendWeaponIcon = new JLabel(weaponIcon);
		JLabel legendWeaponText = new JLabel("= Weapon");

		legend.add(legendHeader, "span 2");
		legend.add(legendHumanIcon);
		legend.add(legendHumanText);
		legend.add(legendAlienIcon);
		legend.add(legendAlienText);
		legend.add(legendWeaponIcon);
		legend.add(legendWeaponText);
		add(legend);

		pack();
		setVisible(true);
		weaponListWeapon1.setVisible(false);
		weaponListWeapon2.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int r = 0; r < gridRows; r++) {
			for (int c = 0; c < gridCols; c++) {
				if (e.getSource() == buttonArray[r][c]) {
					numOfWeaponsValueLabel.setText(String.valueOf(theCells[r][c].getWeapons().size()));
					if (theCells[r][c].getWeapons().size() == 0) {
						weaponListWeapon1.setVisible(false);
						weaponListWeapon2.setVisible(false);
					}
					if (theCells[r][c].getWeapons().size() == 1) {
						weaponListWeapon1.setText(getWeaponType(theCells[r][c].getWeaponAtIndex(0)));
						weaponListWeapon1.setVisible(true);
						weaponListWeapon2.setVisible(false);
					}
					if (theCells[r][c].getWeapons().size() == 2) {
						weaponListWeapon1.setText(getWeaponType(theCells[r][c].getWeaponAtIndex(0)));
						weaponListWeapon2.setText(getWeaponType(theCells[r][c].getWeaponAtIndex(1)));
						weaponListWeapon1.setVisible(true);
						weaponListWeapon2.setVisible(true);
					}
					if (theCells[r][c].getLifeForm() != null) {
						if (theCells[r][c].getLifeForm() instanceof Human) {
							lfTypeValueLabel.setText("Human");
						}
						if (theCells[r][c].getLifeForm() instanceof Alien) {
							lfTypeValueLabel.setText("Alien");
						}
						if (theCells[r][c].getLifeForm().getWeapon() != null){
							lfWeaponTypeValue.setText(getWeaponType(theCells[r][c].getLifeForm().getWeapon()));
							lfWeaponAmmoValue.setText(String.valueOf(theCells[r][c].getLifeForm().getWeapon().getCurrentAmmo())+
									"/" + String.valueOf(theCells[r][c].getLifeForm().getWeapon().getMaxAmmo()));
						}else{
							lfWeaponTypeValue.setText("");
							lfWeaponAmmoValue.setText("");
						}						
					}else{
						lfTypeValueLabel.setText("");
						lfWeaponTypeValue.setText("");
						lfWeaponAmmoValue.setText("");
					}

				}
			}
		}
	}

	public void addLifeFormEvent(LifeForm lf, int row, int col) throws RecovRateIsNegative {
		if (lf instanceof Alien) {
			buttonArray[row][col].setIcon(getAlienIcon(lf, row, col));
		}
		if (lf instanceof Human) {
			buttonArray[row][col].setIcon(getHumanIcon(lf, row, col));
		}
	}

	public void removeLifeFormEvent(int row, int col) {
		buttonArray[row][col].setIcon(defaultIcon);
	}

	public void weaponAddedEvent(Weapon w, int row, int col) {
		buttonArray[row][col].setIcon(weaponIcon);
	}

	public void weaponRemovedEvent(Weapon w, int row, int col) {
		buttonArray[row][col].setIcon(defaultIcon);
	}

	private ImageIcon getAlienIcon(LifeForm lf, int row, int col) {
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

	private ImageIcon getHumanIcon(LifeForm lf, int row, int col) {
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

	private String getWeaponType(Weapon w) {
		if (w instanceof Pistol) {
			return "Pistol";
		}
		if (w instanceof ChainGun) {
			return "Chain Gun";
		}
		if (w instanceof PlasmaCannon) {
			return "PlasmaCannon";
		} else {
			return "none";
		}
	}

}
