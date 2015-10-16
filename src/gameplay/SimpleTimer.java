/**
 * @author Adam Pine
 * Implementation of timer.
 */
package gameplay;

import java.util.ArrayList;

public class SimpleTimer extends Thread implements Timer {
	protected int round;
	private ArrayList<TimeObserver> observerList = new ArrayList<TimeObserver>();
	private int tickrate;

	/**
	 * Constructor, initializes the tickrate.
	 * 
	 * @param tickrate
	 *            The amount of time between each tick update.
	 */
	public SimpleTimer(int tickrate) {
		this.tickrate = tickrate;
	}

	/**
	 * Adds an observer to the arrayList of observers.
	 * 
	 * @param TimeObserver
	 *            the TimeObserver to be added.
	 */
	@Override
	public void addTimeObserver(TimeObserver observer) {
		observerList.add(observer);
	}

	/**
	 * @param TimeObserver
	 *            The TimeObserver to be removed.
	 */
	@Override
	public void removeTimeObserver(TimeObserver observer) {
		observerList.remove(observer);
	}

	/**
	 * When called will loop through the list of TimeObservers and then call
	 * each one's updateTime() method.
	 */
	@Override
	public void timeChanged() {
		if (observerList != null && !observerList.isEmpty()) {
			for (TimeObserver obs : observerList) {
				obs.updateTime(round);
			}
		}
	}

	/**
	 * The run method, will update the round amount, and then call the
	 * timeChanged() method.
	 */
	public void run() {
		for (int x = 0; x < 50; x++) {
			try {
				Thread.sleep(tickrate);
				round++;
				timeChanged();
			} catch (InterruptedException e) {
				System.out.println("Something went wrong, with the timer.");
			}
		}
	}

}
