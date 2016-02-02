package model;

import java.util.Set;

public class MainEngine implements IMainEngine{
	/* Game Component */
	private Set<Ball> ballSet;
	private Set<Line> linesSet;
	private Set<CircularBumper> circleSet;
	private Set<SquareBumper> squareSet;
	private Set<TriangularBumper> triangleSet;
	private Set<Flipper> flipperSet;
	private Set<Absorber> absorberSet;
	private Walls gws;

	/* Game Mechanic */
	private PhysicsConfig physicsSettings;
	private Connections customConnections;

}
