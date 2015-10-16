
/**
 * @author Adam Pine
 * Runs all of the tests in this project
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import environment.TestCell;
import environment.TestEnvironment;
import gameplay.TestSimpleTimer;
import lifeform.TestAlien;
import lifeform.TestHuman;
import lifeform.TestLifeForm;
import recovery.TestRecoveryFractional;
import recovery.TestRecoveryLinear;
import recovery.TestRecoveryNone;
import weapon.TestChainGun;
import weapon.TestGenericWeapon;
import weapon.TestPistol;
import weapon.TestPlasmaCannon;
import weapon.TestPowerBooster;
import weapon.TestScope;
import weapon.TestStabilizer;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestCell.class, TestEnvironment.class, TestLifeForm.class, TestHuman.class, TestAlien.class,
		TestRecoveryFractional.class, TestRecoveryLinear.class, TestRecoveryNone.class, TestSimpleTimer.class,
		TestGenericWeapon.class, TestChainGun.class, TestPistol.class, TestPlasmaCannon.class, TestStabilizer.class,
		TestScope.class, TestPowerBooster.class })
public class AllGameTests {
}
