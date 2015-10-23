/**
 * @author Adam Pine
 * A Cell that can hold a lifeform
 */
package environment;
import java.util.ArrayList;
import lifeform.LifeForm;
import weapon.Attachment;
import weapon.Weapon;
public class Cell implements Cloneable{
	private LifeForm entity = null;
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
	public Cell (){
	}
	
	/**
	 * Returns a cloned version of the weapons array, so that we are not
	 * returning the pointer to the array itself!
	 * 
	 * @return ArrayList<Weapon>
	 */
	public ArrayList<Weapon> getWeapons() {
		if (weapons.size() <= 0) {
			return new ArrayList<Weapon>();
		}
		// clone it to make sure that it returns a clone of the array,
		// not the pointer to the array itself!
		return (ArrayList<Weapon>) weapons.clone();
	}
	/**
	 * Gets the weapon at the specified index.
	 * 
	 * @param index
	 * @return Weapon at index
	 */
	public Weapon getWeaponAtIndex(int index) {
		if (index > 1) {
			return null;
		}
		if (weapons.isEmpty()) {
			return null;
		} else {
			return weapons.get(index);
		}
	}
	
	public Attachment getAttachmentAtIndex(int index){
		if (index > 1) {
			return null;
		}
		if (weapons.isEmpty()) {
			return null;
		} else {
			return (Attachment) weapons.get(index);
		}
	}
	/**
	 * Adds a weapon to the cell's weapons array. If the array is >= 2 it will
	 * not add it.
	 * 
	 * @param w
	 *            : Weapon
	 * @return true if it added the item to the arrayList
	 */
	public boolean addWeapon(Weapon w) {
		if (weapons.size() >= 2) {
			return false;
		} else {
			return weapons.add(w);
		}
	}
	/**
	 * Removes that weapon from the weapons array returns true if it was
	 * removed, false if it was not removed/not found.
	 * 
	 * @param w
	 *            Weapon
	 * @return true, if it was removed, false if it was not removed.
	 */
	public boolean removeWeapon(Weapon w) {
		return weapons.remove(w);
	}
	/**
	 * @return the LifeForm in this Cell.
	 */
	public LifeForm getLifeForm() {
		return entity;
	}
	/**
	 * Override the clone() method so that I can pass a clone of the cell, rather than the cell itself.
	 */
	@Override
	protected Cell clone() throws CloneNotSupportedException {
		return this;
	}
	/**
	 * Tries to add the lifeform to the Cell. Will not add if a LifeForm is
	 * already present
	 * 
	 * @param entity
	 * @return true if the LifeForm was added to the Cell, false otherwise.
	 */
	public boolean addLifeForm(LifeForm entity) {
		if (this.entity == null) {
			this.entity = entity;
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Removes the lifeform from the Cell.
	 * 
	 * @return the removed lifeform if successful, null if not.
	 */
	public LifeForm removeLifeForm() {
		if (entity != null) {
			LifeForm returnLF = entity;
			entity = null;
			return returnLF;
		} else {
			return null;
		}
	}
}