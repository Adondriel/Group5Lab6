package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRecoveryFractional {

	@Test
	public void testRecoveryFract() {
		RecoveryBehavior rb = new RecoveryFractional(1.5);
		// Full hp
		assertEquals(20, rb.calculateRecovery(20, 20));
		// Recover to max, without going over;
		assertEquals(30, rb.calculateRecovery(20, 30));
		// hurt a little, recover full step.
		assertEquals((int) (5 * 1.5), rb.calculateRecovery(5, 30), 0);
		// they are very dead, should not heal them.
		assertEquals(0, rb.calculateRecovery(-5, 30));
		// Test bordercase of 0.
		assertEquals(0, rb.calculateRecovery(0, 20));
	}

}
