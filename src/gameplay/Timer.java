/**
 * @author Adam Pine
 * Timer interface for what a timer needs to implement.  
 */
package gameplay;

public interface Timer {
	public abstract void addTimeObserver(TimeObserver observer);

	public abstract void removeTimeObserver(TimeObserver observer);

	public abstract void timeChanged();
}
