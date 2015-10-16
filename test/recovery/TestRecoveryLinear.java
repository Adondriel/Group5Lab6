package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRecoveryLinear {

	@Test
	public void testRecovLinear() {
		RecoveryBehavior rb = new RecoveryLinear(3);
		// Test when full hp. currentLP = maxLP
		assertEquals(20, rb.calculateRecovery(20, 20));
		// Test when dmged.
		assertEquals(18, rb.calculateRecovery(15, 20));
		// test when very dead.
		assertEquals(0, rb.calculateRecovery(-5, 20));
		// test border case of 0 hp.
		assertEquals(0, rb.calculateRecovery(0, 20));

	}

}
