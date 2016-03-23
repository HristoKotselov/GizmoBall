package testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import model.PhysicsConfig;

public class BasicPhysicsConfigTests {
	// setup physics configs with the default values

	PhysicsConfig pc = new PhysicsConfig(2.0, 2.0, 2.0);
	PhysicsConfig pcn = new PhysicsConfig();

	/*
	 * Testing that the default physics settings are applied correctly, by
	 * comparing a custom setting against a default one
	 */
	@Test
	public void testDefaultSetting() {
		assertTrue(pc != pcn);
	}

	/*
	 * Testing that the gravity value can be changed successfully
	 */
	@Test
	public void testGravityChange() {
		pc.setGravity(3.0);
		assertFalse(pc.getGravity() == 2.0);
		assertTrue(pc.getGravity() == 3.0);

	}

	/*
	 * Testing that the 1st coefficient value of friction can be changed
	 * successfully
	 */
	@Test
	public void testFric1Change() {
		pc.setFrictionCoef1(3.0);
		assertFalse(pc.getFrictionCoef1() == 2.0);
		assertTrue(pc.getFrictionCoef1() == 3.0);
	}

	/*
	 * Testing that the 2nd coefficient value of friction can be changed
	 * successfully
	 */
	@Test
	public void testFric2Change() {
		pc.setFrictionCoef2(3.0);
		assertFalse(pc.getFrictionCoef2() == 2.0);
		assertTrue(pc.getFrictionCoef2() == 3.0);
	}

}
