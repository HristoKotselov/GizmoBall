package testing;

import static org.junit.Assert.*;
import java.awt.Shape;
import java.awt.Color;
import java.util.Set;
import physics.Vect;
import org.junit.Test;
import model.*;
import physics.Angle;
import physics.Circle;
public class BallTests {
	Angle test1 = new Angle(25);
	Angle test2 = new Angle(75);
	
	
	
	
	Ball a = new Ball("A1", Color.red, 2, 2, test1, 3.0);
	Ball b = new Ball("B1", Color.blue, 3, 3, test2, 3.5);
	Ball c = new Ball("C1", Color.red, 2, 2, test1, 3.0);
	
	@Test
	public void testRotation() {
		assertTrue(a.rotate(11));
		assertTrue(b.rotate(12));
		assertTrue(c.rotate(13));
	}
	
	@Test
	public void testGetRadius(){
		assertTrue(a.getRadius() == 5);
		assertTrue(b.getRadius() == 5);
		assertTrue(c.getRadius() == 5);
	}
	
	@Test
	public void circleSetTest(){
		
		Set<Circle> testCirc = a.getCircles();
		assertNotNull(testCirc);
	}
	
	@Test
	public void testGetShape(){
		Shape s;
		s = a.getDrawingShape();
		assertNotNull(s);
	}
	
	@Test
	public void resetTest(){
		Vect testing = new Vect(Angle.DEG_270, 50);
		a.setVelo(testing);
		a.move(1,2);
		a.reset();
		assertFalse(a.getVelo() == testing);
	}
	
	@Test
	public void circleNotNull(){
		Circle c = a.getCircle();
		Circle d = a.getCircle();
		assertEquals(c,d);
		assertNotNull(c);
		assertNotNull(d);
		
		}

}
