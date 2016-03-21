package testing;

/*
 * A test case to test the PhysicsConfig class
 */
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import model.PhysicsConfig;

public class BasicPhysicsConfigTests {
//setup physics configs with the default values 
	
	PhysicsConfig pc = new PhysicsConfig(2.0, 2.0, 2.0);
	PhysicsConfig pcn = new PhysicsConfig();
	
	@Test
	public void testDefaultSetting() {
		assertTrue(pc != pcn);
	}
	
	@Test
	public void testGravityChange(){
		pc.setGravity(3.0);
		assertFalse(pc.getGravity() == 2.0);
		assertTrue(pc.getGravity() == 3.0);

	}
	
	@Test
	public void testFric1Change(){
		pc.setFrictionCoef1(3.0);
		assertFalse(pc.getFrictionCoef1() == 2.0);
		assertTrue(pc.getFrictionCoef1() == 3.0);
	}
	
	@Test
	public void testFric2Change(){
		pc.setFrictionCoef2(3.0);
		assertFalse(pc.getFrictionCoef2() == 2.0);
		assertTrue(pc.getFrictionCoef2() == 3.0);
	}
	

}
