package testing;

import static org.junit.Assert.*;

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
	CircularBumper cb = new CircularBumper("cb", 1, 2, Color.blue);
	CircularBumper cb2 = new CircularBumper("cb2", 1, 2, Color.blue);
	CircularBumper cb3 = new CircularBumper("cb3", 1, 2, Color.blue);
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
