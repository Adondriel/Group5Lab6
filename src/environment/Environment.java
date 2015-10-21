/**
 * @author Adam Pine, Benjamin Uleau
 * The environment class, used to hold and manage a 2-D array of cells.
 */
package environment;
import java.io.IOException;

import Exceptions.EnvironmentException;
import Exceptions.RecovRateIsNegative;
import GUI.GUI;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Weapon;
public class Environment {
	private static int maxRow;
	private static int maxCol;
	private static Cell[][] theCells;
	private static Environment theWorld = null;
	private char north='n';
	private char south='s';
	private char east='e';
	private char west='w';
	/**
	 * The private constructor, used with the singleton pattern.
	 * Isolates the construction of the Environment class.
	 * @param width
	 * @param height
	 */
	private Environment(int width, int height) {
		theCells = new Cell[width][height];
		maxRow = width;
		maxCol = height;
		ClearBoard();
	}
	/**
	 * Will initialize the world if the world is currently null.
	 * then it will return theWorld.
	 * @param width
	 * @param height
	 * @return theWorld
	 */
	public static synchronized Environment getWorldInstance(int width, int height) {
		if (theWorld == null) {
			theWorld = new Environment(width, height);
		}
		return theWorld;
	}
	/** 
	 * @warning This can be null!! Be very careful!
	 * Only returns theWorld, does not initialize it 
	 * This is just incase you KNOW the world is created, and don't have dimensions available to you.
	 * !!CAN BE NULL!!
	 */
	public static Environment getWorldInstance() {
		return theWorld;
	}
	/**
	 * Sets every item in the Environment to a blank cell. !Be careful!
	 */
	public void ClearBoard() {
		for (int i=0; i<maxRow; i++){
			for (int j=0; j<maxCol; j++){
				theCells[i][j] = new Cell();
			}
		}
	}	
	public static Cell getCellAt(int row, int col) throws CloneNotSupportedException{
		System.out.println("asd: "+theCells[row][col].clone().getWeapons().size());
		return (Cell) theCells[row][col].clone();
	}
	/**
	 * Will add a LifeForm to the 2-D array of Cells if it does not have a
	 * LifeForm in said location.
	 * 
	 * @param row
	 * @param col
	 * @param entity
	 * @return true if added successfully, and false if not added.
	 * @throws RecovRateIsNegative 
	 */
	public boolean addLifeForm(int row, int col, LifeForm entity) throws RecovRateIsNegative {
		if ((row >= maxRow) || (col >= maxCol)) {
			return false;
		}
		if (theCells[row][col] == null) {
			theCells[row][col] = new Cell();
			boolean rval = theCells[row][col].addLifeForm(entity);
			theCells[row][col].getLifeForm().setMyCol(col);
			theCells[row][col].getLifeForm().setMyRow(row);
			GUI.globalGUI.addLifeFormEvent(entity, row, col);
			entity.turnNorth();
			return rval;
		} else {
			boolean rval = theCells[row][col].addLifeForm(entity);
			theCells[row][col].getLifeForm().setMyCol(col);
			theCells[row][col].getLifeForm().setMyRow(row);
			GUI.globalGUI.addLifeFormEvent(entity, row, col);
			entity.turnNorth();
			return rval;
		}
	}
	/**
	 * Removes a lifeform from the environment if it exists
	 * 
	 * @param row
	 * @param col
	 * @return the removed lifeform if successful, null if unsuccessful.
	 */
	public LifeForm removeLifeForm(int row, int col) {
		if ((row >= maxRow) || (col >= maxCol)) {
			return null;
		}
		if (theCells[row][col] != null) {
			LifeForm rCell = theCells[row][col].getLifeForm();
			theCells[row][col].removeLifeForm();
			GUI.globalGUI.removeLifeFormEvent(row, col);
			return rCell;
		} else {
			return null;
		}
	}
	/**
	 * Get's the lifeform in theCells[row][col]
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public LifeForm getLifeForm(int row, int col) {
		if ((row >= maxRow) || (col >= maxCol)) {
			return null;
		}
		if (theCells[row][col] == null) {
			return null;
		} else {
			return theCells[row][col].getLifeForm();
		}
	}
	/**
	 * If it added the weapon it will return true, otherwise it will return
	 * false.
	 * 
	 * @param row
	 * @param col
	 * @param w
	 * @return
	 */
	public boolean addWeapon(int row, int col, Weapon w) {
		if ((row >= maxRow) || (col >= maxCol)) {
			return false;
		}
		if (theCells[row][col] == null) {
			theCells[row][col] = new Cell();
			GUI.globalGUI.weaponAddedEvent(w, row, col);
			return theCells[row][col].addWeapon(w);
		} else {
			GUI.globalGUI.weaponAddedEvent(w, row, col);
			return theCells[row][col].addWeapon(w);
		}
	}
	/**
	 * Remove the weapon at row, col, that is equivalent to w.
	 * @param row
	 * @param col
	 * @param w
	 * @return
	 */
	public boolean removeWeapon(int row, int col, Weapon w) {
		if ((row >= maxRow) || (col >= maxCol)) {
			return false;
		}
		if (theCells[row][col] == null) {
			return false;
		} else {
			GUI.globalGUI.weaponRemovedEvent(w, row, col);
			return theCells[row][col].removeWeapon(w);
		}
	}
	/**
	 * Will find the distance between lifeform a, and lifeform b.
	 * @param a
	 * @param b
	 * @return int the distance between the two objects.
	 * @throws EnvironmentException
	 */
	public int getDistance(LifeForm a, LifeForm b) throws EnvironmentException {
		System.out.println(a.getMyRow());
		System.out.println(a.getMyCol());
		System.out.println(b.getMyRow());
		System.out.println(b.getMyCol());
		return getDistanceBetweenCoords(a.getMyRow(), a.getMyCol(), b.getMyRow(), b.getMyCol());
	}	
	/**
	 * Get the distance between 2 sets of coords, (row1, col1) and (row2, col2)
	 * does not care about order of the sets entered as long as they are entered with their respective coord partner. (row1, with col1)
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @return distance between the two coords.
	 * @throws EnvironmentException
	 */
	public int getDistanceBetweenCoords(int row1, int col1, int row2, int col2) throws EnvironmentException {
		//make sure it isnt out of bounds.
		if ((row1 >= maxRow || row2 >= maxRow) || (col1 >= maxCol || col2 >= maxCol)) {
			throw new EnvironmentException("Row or Col was out of bounds.");
		}
		//If columns are the same,
		if (col1 == col2) {
			//find the larger row, add one and then subtract the smaller row+1 from it.
			if (row1 < row2) {
				System.out.println(5 * ((row2+1)-(row1+1)));
				return 5 * ((row2+1)-(row1+1));
			}
			if (row2 < row1) {
				System.out.println(5 * ((row1+1)-(row2+1)));
				return 5 * ((row1+1)-(row2+1));
			}
		//if the rows are the same,
		} else if (row1 == row2) {
			//find the larger col, add one, and then subtract the smaller row+1 from it.
			if (col1 < col2) {
				System.out.println(5 * ((col2+1)-(col1+1)));
				return 5 * ((col2+1)-(col1+1));
			}
			if (col2 < col1) {
				System.out.println(5 * ((col1+1)-(col2+1)));
				return 5 * ((col1+1)-(col2+1));
			}
		} else {
			//use the same formula down here to get the A and B of the triangle.
			int colDist = 0;
			int rowDist = 0;
			double colDistSquared = 0;
			double rowDistSquared = 0;

			if (row1 < row2) {
				colDist = 5 * ((row2+1)-(row1+1));
			}
			if (row2 < row1) {
				colDist = 5 * ((row1+1)-(row2+1));
			}
			if (col1 < col2) {
				rowDist = 5 * ((col2+1)-(col1+1));
			}
			if (col2 < col1) {
				rowDist = 5 * ((col1+1)-(col2+1));
			}
			System.out.println(colDist);
			System.out.println(rowDist);
			//square each side.
			colDistSquared = Math.pow(colDist, 2);
			rowDistSquared = Math.pow(rowDist, 2);
			//add them together to make a^2 + b^2
			double ASquarePlusBSquare = colDistSquared + rowDistSquared;
			//take the sqrt of them, to get c!
			int c = (int) Math.sqrt(ASquarePlusBSquare);
			System.out.println(c);
			//return c!
			return c;
		}
		return 0;
	}
	private void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public int getMaxCol() {
		return maxCol;
	}
	private void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}
	public int getMaxRow() {
		return maxRow;
	}
	
	/**
	 * step into the cell the lifeform is facing
	 * @param row
	 * @param col
	 * @return whether or not the lifeform stepped
	 */
	public boolean step(int row, int col){
		//Make sure there is a lifeform in the cell, that the direction is north, that moving north won't take the lifeform out of the world,
		//and that the space the lifeform wants to move to is currently unoccupied
		if(theCells[row][col].getLifeForm().getCurrentMoves()<=theCells[row][col].getLifeForm().getMaxMoves()){	
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==north && row>0 && theCells[row-1][col].getLifeForm()==null){
				theCells[row-1][col].addLifeForm(theCells[row][col].getLifeForm());
				theCells[row][col].getLifeForm().moved();
				theCells[row][col].removeLifeForm();
				return true;
			}
			//Make sure there is a lifeform in the cell, that the direction is south, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==south && row<maxRow && theCells[row+1][col].getLifeForm()==null){
				theCells[row+1][col].addLifeForm(theCells[row][col].getLifeForm());
				theCells[row][col].getLifeForm().moved();
				theCells[row][col].removeLifeForm();
				return true;
			}
			//Make sure there is a lifeform in the cell, that the direction is east, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==east && col<maxCol && theCells[row][col+1].getLifeForm()==null){
				theCells[row][col+1].addLifeForm(theCells[row][col].getLifeForm());
				theCells[row][col].getLifeForm().moved();
				theCells[row][col].removeLifeForm();
				return true;
			}
			//Make sure there is a lifeform in the cell, that the direction is west, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==west && col>0 && theCells[row][col-1].getLifeForm()==null){
				theCells[row][col-1].addLifeForm(theCells[row][col].getLifeForm());
				theCells[row][col].getLifeForm().moved();
				theCells[row][col].removeLifeForm();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param row
	 * @param col
	 * @param n number of cells to move
	 * @return whether or not the lifeform was allowed to move to the specified space
	 * @throws RecovRateIsNegative 
	 */
	public boolean stepNSpaces(int row, int col, int n) throws RecovRateIsNegative{
		//Make sure the lifeform has enough moves remaining to do the specified command
		if(theCells[row][col].getLifeForm().getCurrentMoves()+n<=theCells[row][col].getLifeForm().getMaxMoves()){	
			//Make sure there is a lifeform in the cell, that the direction is north, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==north && row-n>=0 && theCells[row-n][col].getLifeForm()==null){
				theCells[row-n][col].addLifeForm(theCells[row][col].getLifeForm());
				GUI.globalGUI.removeLifeFormEvent(row, col);
				GUI.globalGUI.addLifeFormEvent(theCells[row][col].getLifeForm(), row-n, col);
				callMoved(theCells[row][col].getLifeForm(), n);
				theCells[row][col].removeLifeForm();
				return true;
			}
			//Make sure there is a lifeform in the cell, that the direction is south, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==south && row+n<=maxRow && theCells[row+n][col].getLifeForm()==null){
				theCells[row+n][col].addLifeForm(theCells[row][col].getLifeForm());
				GUI.globalGUI.removeLifeFormEvent(row, col);
				GUI.globalGUI.addLifeFormEvent(theCells[row][col].getLifeForm(), row+n, col);
				callMoved(theCells[row][col].getLifeForm(), n);
				theCells[row][col].removeLifeForm();
				return true;
			}
			//Make sure there is a lifeform in the cell, that the direction is east, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==east && col+n<=maxCol && theCells[row][col+n].getLifeForm()==null){
				theCells[row][col+n].addLifeForm(theCells[row][col].getLifeForm());
				GUI.globalGUI.removeLifeFormEvent(row, col);
				GUI.globalGUI.addLifeFormEvent(theCells[row][col].getLifeForm(), row, col+n);
				callMoved(theCells[row][col].getLifeForm(), n);
				theCells[row][col].removeLifeForm();
				return true;
			}
			//Make sure there is a lifeform in the cell, that the direction is west, that moving north won't take the lifeform out of the world,
			//and that the space the lifeform wants to move to is currently unoccupied
			if(theCells[row][col].getLifeForm()!=null && theCells[row][col].getLifeForm().getDirection()==west && col-n>=0 && theCells[row][col-n].getLifeForm()==null){
				theCells[row][col-n].addLifeForm(theCells[row][col].getLifeForm());
				GUI.globalGUI.removeLifeFormEvent(row, col);
				GUI.globalGUI.addLifeFormEvent(theCells[row][col].getLifeForm(), row, col-n);
				callMoved(theCells[row][col].getLifeForm(), n);
				theCells[row][col].removeLifeForm();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param l the lifeform to increment
	 * @param n how many moves the lifeform used
	 */
	public void callMoved(LifeForm l, int n){
		for(int i=0; i<n; i++){
			l.moved();
		}
	}
	
	public Cell[][] getCellsArray(){
		return theCells;
	}
}