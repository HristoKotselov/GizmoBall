package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import model.*;
import physics.Angle;
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

}
