package testing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import model.*;

public class AbsorberTests {
	Absorber a = new Absorber("A1", 1, 1, 1, 1, Color.red);
	Absorber b = new Absorber("B1", 2, 2, 2, 2, Color.blue);
	Absorber c = new Absorber("A1", 1, 1, 1, 1, Color.red);

	@Test
	public void testAbsorberCreation() {

		assertFalse(a == b);
		assertTrue(a != b);
		
	}

	@Test
	public void testAbsorberSetGetHeight() {
		a.setHeight(10);
		b.setHeight(20);
		c.setHeight(10);
		
		int aHeight = a.getHeight();
		int bHeight = b.getHeight();
		int cHeight = c.getHeight();
		
		assertTrue(aHeight == cHeight);
		assertTrue(cHeight == aHeight);
		assertTrue(aHeight == aHeight);
		assertTrue(cHeight == cHeight);
		assertFalse(aHeight == bHeight);
		assertFalse(bHeight == aHeight);
		assertFalse(cHeight == bHeight);
		assertFalse(bHeight == cHeight);
		}

	@Test
	public void testAbsorberSetGetWidth() {
		a.setWidth(10);
		b.setWidth(20);
		c.setWidth(10);
		
		int aWidth = a.getWidth();
		int bWidth = b.getWidth();
		int cWidth = c.getWidth();
		
		assertTrue(aWidth == cWidth);
		assertTrue(cWidth == aWidth);
		assertTrue(aWidth == aWidth);
		assertTrue(cWidth == cWidth);
		assertFalse(aWidth == bWidth);
		assertFalse(bWidth == aWidth);
		assertFalse(cWidth == bWidth);
		assertFalse(bWidth == cWidth);
	}

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
		assertTrue(aX == aX);
		assertTrue(cX == cX);
		assertFalse(aX == bX);
		assertFalse(bX == aX);
		assertFalse(cX == bX);
		assertFalse(bX == cX);
	}

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
		assertTrue(aY == aY);
		assertTrue(cY == cY);
		assertFalse(aY == bY);
		assertFalse(bY == aY);
		assertFalse(cY == bY);
		assertFalse(bY == cY);
	}

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
		assertTrue(aColour == aColour);
		assertTrue(cColour == cColour);
		assertFalse(aColour == bColour);
		assertFalse(bColour == aColour);
		assertFalse(cColour == bColour);
		assertFalse(bColour == cColour);
	}
}
