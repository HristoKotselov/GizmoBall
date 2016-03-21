package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.awt.Color;
import java.awt.Shape;
/*
 * A test case to test the Circular, Triangular and Square bumper classes
 */
import org.junit.Test;
import model.gizmos.CircularBumper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangularBumper;

public class BumperTests {

	/*
	 * Circular Bumper class tests
	 */
	CircularBumper circle1 = new CircularBumper("circle", 1, 2, Color.blue);
	SquareBumper square1 = new SquareBumper("square", 1, 2, Color.blue);
	TriangularBumper triangle1 = new TriangularBumper("triangle", 1, 2, Color.blue);

	@Test
	public void testtoString() {
		String circ = "Circle circle 1 2";
		String sq = "Square square 1 2";
		String tri = "Triangle triangle 1 2";
		assertEquals(circ, circle1.toString());
		assertEquals(sq, square1.toString());
		assertEquals(tri, triangle1.toString());
	}

	@Test
	public void rotateTest() {
		assertTrue(circle1.rotate(12));
		assertTrue(square1.rotate(13));
		assertTrue(triangle1.rotate(14));
	}

	@Test
	public void drawingShapeTests() {
		Shape c1 = circle1.getDrawingShape();
		Shape c2 = square1.getDrawingShape();
		Shape c3 = triangle1.getDrawingShape();
		assertNotNull(c1);
		assertNotNull(c2);
		assertNotNull(c3);
	}

}
