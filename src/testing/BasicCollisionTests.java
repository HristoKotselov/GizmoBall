package testing;

import static org.junit.Assert.*;
import model.CollisionDetails;
import model.MainEngine;
import physics.*;
import org.junit.Test;
import model.gizmos.*;
import java.awt.Color;

public class BasicCollisionTests {
	MainEngine mdl = new MainEngine();
	Angle a = new Angle(50);
	Vect v = new Vect(a);
	Ball b = new Ball("ball", Color.red, 1, 1, a, 2.0);
	CollisionDetails cd = new CollisionDetails(2.0, v, b, "test", mdl);

	/*
	 * Test GetTuc
	 */
	@Test
	public void testGetTuc() {

		assertTrue(cd.getTuc() == 2.0);
	}

	/*
	 * Test getVelo
	 */
	@Test
	public void testGetVelo() {

		assertTrue(cd.getVelo() == v);
	}

	/*
	 * Test getBall
	 */
	@Test
	public void testGetBall() {

		assertTrue(cd.getBall() == b);
	}

	/*
	 * Test getColliderName
	 */
	@Test
	public void testGeColliderName() {

		assertTrue(cd.getColliderName() == "test");
	}

	/*
	 * Test getMainEngine
	 */
	@Test
	public void testGetMainEngine() {

		assertTrue(cd.getMainEngine() == mdl);
	}

}
