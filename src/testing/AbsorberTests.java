package testing;

/*
 * A test case to test the Absorber class
 */
import static org.junit.Assert.*;
import physics.Circle;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import model.gizmos.Absorber;
import model.gizmos.Ball;
import physics.Angle;
import physics.Circle;
import physics.LineSegment;

public class AbsorberTests {
	Absorber a = new Absorber("A1", 1, 1, 1, 1, Color.red);
	Absorber b = new Absorber("B1", 2, 2, 2, 2, Color.blue);
	Absorber c = new Absorber("A1", 1, 1, 1, 1, Color.red);
	Angle test1 = new Angle(25);
	List<Ball> capturedBalls = new ArrayList<Ball>();
	Ball ball = new Ball("test", Color.RED, 1, 1, test1, 2.0);
	Ball ball2 = new Ball("test2", Color.RED, 1, 1, test1, 2.0);
	Ball ball3 = new Ball("test3", Color.RED, 1, 1, test1, 2.0);
	Ball ball4 = new Ball("test4", Color.RED, 1, 1, test1, 2.0);
	Ball ball5 = new Ball("test5", Color.RED, 1, 1, test1, 2.0);

	/*
	 * Standard test to ensure created absorbers with differing constructor
	 * values are not identical objects
	 */
	@Test
	public void testAbsorberCreation() {

		assertFalse(a == b);
		assertTrue(a != b);

	}

	/*
	 * returns true to ensure that absorber rotation has been successful Mainly
	 * to check integrity of the method implementation ,as absorbers will
	 * obviously not be rotated in gameplay
	 */
	@Test
	public void testRoation() {
		assertTrue(a.rotate(11));
		assertTrue(a.rotate(12));
		assertTrue(a.rotate(13));
	};

	/*
	 * A test to check whether or not the absorbers trigger method deals with
	 * the captured ball appropriately
	 */
	@Test
	public void testCapturedBall() {
		capturedBalls.add(ball);
		capturedBalls.add(ball2);
		capturedBalls.add(ball3);
		capturedBalls.add(ball4);
		capturedBalls.add(ball5);

		assertFalse(a.getCapturedBalls() == null);
		a.action();
		capturedBalls.clear();
		System.out.println("");
		System.out.println("Clearing using clear");
		for (int i = 0; i < capturedBalls.size(); i++) {
			System.out.println(capturedBalls.get(i));
		}
		assertTrue(capturedBalls.isEmpty());
	}

	/*
	 * Similar to above, but calls reset instead of explicitly calling clear
	 */
	@Test
	public void testReset() {
		a.addCapturedBall(ball);
		a.addCapturedBall(ball2);
		a.addCapturedBall(ball3);
		a.addCapturedBall(ball4);
		a.addCapturedBall(ball5);

		assertFalse(a.getCapturedBalls() == null);
		a.reset();
		System.out.println("");
		System.out.println("Clearing using reset");
		for (int i = 0; i < capturedBalls.size(); i++) {
			System.out.println(capturedBalls.get(i));
		}

		assertTrue(a.getCapturedBalls().isEmpty());
	}

	/*
	 * Making sure the setX and getX methods are working
	 */
	@Test
	public void testAbsorberSetGetX() {
		a.setX(5);
		b.setX(7);
		c.setX(5);

		int aX = a.getX();
		int bX = b.getX();
		int cX = c.getX();

		assertTrue(aX == cX);
		assertTrue(cX == aX);
		assertFalse(aX == bX);
		assertFalse(bX == aX);
		assertFalse(cX == bX);
		assertFalse(bX == cX);
	}

	/*
	 * Making sure the setY and getY methods are working
	 */
	@Test
	public void testAbsorberSetGetY() {
		a.setY(5);
		b.setY(7);
		c.setY(5);

		int aY = a.getY();
		int bY = b.getY();
		int cY = c.getY();

		assertTrue(aY == cY);
		assertTrue(cY == aY);
		assertFalse(aY == bY);
		assertFalse(bY == aY);
		assertFalse(cY == bY);
		assertFalse(bY == cY);
	}

	/*
	 * Testing the get colour method of the absorber
	 */
	@Test
	public void testAbsorberSetGetColor() {
		a.setColour(Color.green);
		b.setColour(Color.blue);
		c.setColour(Color.green);

		Color aColour = a.getColour();
		Color bColour = b.getColour();
		Color cColour = c.getColour();

		assertTrue(aColour == cColour);
		assertTrue(cColour == aColour);
		assertFalse(aColour == bColour);
		assertFalse(bColour == aColour);
		assertFalse(cColour == bColour);
		assertFalse(bColour == cColour);
	}

	/*
	 * testing that the move method correctly changes certain properties of the
	 * absorber
	 */
	@Test
	public void testAbsorberMove() {
		Absorber a1 = new Absorber("test", 1, 1, 1, 1, Color.red);
		Absorber b1 = new Absorber("test", 1, 1, 1, 1, Color.red);
		assertFalse(a1 == b1);

		a1.move(10, 10);

		assertTrue(a1 != b1);
	}

	/*
	 * Testing that the set of circles is not empty
	 */
	@Test
	public void circleSetTest() {

		Set<Circle> testCirc = a.getCircles();
		assertNotNull(testCirc);
	}

	/*
	 * testing the return of the drawing shape
	 */
	@Test
	public void returnDrawingShape() {
		Shape s = a.getDrawingShape();
		assertNotNull(s);
	}

	/*
	 * Testing that the set of LineSegs is not empty
	 */
	@Test
	public void lineSegSetTest() {

		Set<LineSegment> testSeg = a.getLineSeg();
		assertNotNull(testSeg);
	}

	/*
	 * A test to show that there is no captured ball beloning to the absorber
	 * once it has been triggered
	 */
	@Test
	public void triggerTest() {
		// imitating a ball being captured in the absorber
		a.addCapturedBall(ball);
		// triggering the absorber, which should mean capturedBall == null
		a.action();

		List<Ball> capturedBalls = a.getCapturedBalls();

		assertTrue(capturedBalls.size() == 0);
	}

	/*
	 * Testing that the toString method prints out correctly
	 */
	@Test
	public void testToString() {
		String test = "Absorber A1 1 1 2 2";
		System.out.println("");
		System.out.println(a.toString());
		System.out.println(test);
		assertEquals(test, a.toString());
	}
}
