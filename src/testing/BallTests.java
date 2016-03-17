package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import model.*;
import physics.Angle;
public class BallTests {
	Angle test1 = new Angle(25);
	Angle test2 = new Angle(75);
	
	
	// TODO Ball constructor updated, so need to change those 2.0s & 2.5s into int
	/*
	Ball a = new Ball("A1", Color.red, 2.0, 2.0, test1, 3.0);
	Ball b = new Ball("B1", Color.blue, 2.5, 2.5, test2, 3.5);
	Ball c = new Ball("C1", Color.red, 2.0, 2.0, test1, 3.0);
	*/
	@Test
	
	/*public void rotateTest() {
		assertFalse(a.rotate(50));
		assertFalse(b.rotate(100));
		assertFalse(c.rotate(0));
	}*/
	
	public void moveTest(){
		//assertTrue(a.move((int)2.0, (int) 3.0));
		//assertTrue(b.move((int)2.5, (int) 2.0));
		//assertTrue(c.move((int)1.0, (int) 1.0));
		
		//assertFalse(a.move((int)2.0, (int) 2.0));
		//assertFalse(b.move((int)2.5, (int) 2.5));
		//assertFalse(c.move((int)2.0, (int) 2.0));
	}

}
