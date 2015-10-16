package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * mock TO class for use with the tests.
 * 
 * @author Adam
 */
class MockTimeObserver implements TimeObserver {
	public int myTime = 0;

	public void updateTime(int time) {
		myTime = time;
	}

	// Get method for myTime.
	@Override
	public int getMyTime() {
		return myTime;
	}
}

/**
 * The tests for SimpleTimer.
 * 
 * @author Adam
 */
public class TestSimpleTimer {

	@Test
	public void testSimpleTimerInitAndTimeUpdates() throws InterruptedException {
		SimpleTimer st = new SimpleTimer(1000);
		st.start();
		Thread.sleep(250); // 1/4 a second diff from the simpletimer sleep.
		for (int x = 0; x < 5; x++) {
			assertEquals(x, st.round);
			Thread.sleep(1000);
		}
	}

	/**
	 * Tests adding and removing of Observers Also tests if the timeObserver
	 * receives time updates. The thread stops after 50 ticks. Uses a mockTO for
	 * testing.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testAddandRemoveObservers() throws InterruptedException {
		SimpleTimer st = new SimpleTimer(1000);
		TimeObserver to = new MockTimeObserver();
		st.addTimeObserver(to);
		st.start();
		Thread.sleep(250);
		assertEquals(st.round, to.getMyTime());
		Thread.sleep(1000);
		int myTime = to.getMyTime();
		assertEquals(st.round, to.getMyTime());
		st.removeTimeObserver(to);
		Thread.sleep(1000);
		assertEquals(myTime, to.getMyTime());
	}

}
