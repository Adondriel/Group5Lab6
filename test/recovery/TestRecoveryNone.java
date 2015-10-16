package recovery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRecoveryNone {

	@Test
	public void testRecovNone() {
		RecoveryBehavior rb = new RecoveryNone();
		// it should not heal, ever. This confirms all of that.
		assertEquals(15, rb.calculateRecovery(15, 20));
		// No healing when at full hp.
		assertEquals(20, rb.calculateRecovery(20, 20));
	}

}
