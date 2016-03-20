package testing;

import static org.junit.Assert.*;
/*
 * A test case to test the Circular, Triangular and Square bumper classes
 */
import org.junit.Test;
import java.awt.Shape;
import java.awt.Color;
import java.util.Set;
import physics.Vect;
import model.*;
import physics.Angle;
import physics.Circle;
import model.CircularBumper;

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
	public void rotateTest(){
		assertTrue(circle1.rotate(12));
		assertTrue(square1.rotate(13));
		assertTrue(triangle1.rotate(14));
	}
	
	@Test
	public void drawingShapeTests(){
		Shape c1 = circle1.getDrawingShape();
		Shape c2 = square1.getDrawingShape();
		Shape c3 = triangle1.getDrawingShape();
		assertNotNull(c1);
		assertNotNull(c2);
		assertNotNull(c3);	
	}

}
