/**
 * @author Adam Pine
 * Interface for TimeObservers.
 */
package gameplay;

public interface TimeObserver {
	public abstract void updateTime(int time);

	public abstract int getMyTime();
}
