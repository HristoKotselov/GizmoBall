package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class MainEngine extends Observable implements IMainEngine, ISaveDataEngine {
	/* Constants */
	final static int L = 20;

	/* Game Component */
	private Set<Ball> ballSet;
	public Ball ball;// Using one ball to test, this is the only ball for now
	// private Set<CircularBumper> circleSet;
	// private Set<SquareBumper> squareSet;
	// private Set<TriangularBumper> triangleSet;
	// private Set<Flipper> flipperSet;
	// private Set<Absorber> absorberSet;

	private Map<String, AGizmoComponent> gizmos;

	private Walls gws;

	/* Game Mechanic */
	private PhysicsConfig physicsSettings;
	private CollisionHandler collisionHandler;
	private Connections customConnections;
	private SaveDataEngine fileHandler;

	/* Run time values */
	private boolean isPlaying; // used to tell Keyboard ActionListeners when
								// they should be active (only when the game is
								// running)

	public MainEngine() {
		gizmos = new HashMap<String, AGizmoComponent>();
		fileHandler = new SaveDataEngine();
		ball = new Ball("Ball", Color.RED, 50, 50, new Angle(45), 50);
	}

	@Override
	public void moveBall() {

		double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

		/*
		 * for (Ball ball : ballSet) { if (ball == null || ball.stopped()) {
		 * System.out.println(
		 * "ball null or stopped, main engine, top of move ball"); return; } }
		 */

		CollisionDetails cd = timeUntilCollision();
		double tuc = cd.getTuc();
		if (tuc > moveTime) {
			// No collision ...
			ball = movelBallForTime(ball, moveTime);
		} else {
			// We've got a collision in tuc
			ball = movelBallForTime(ball, tuc);
			// Post collision velocity ...
			ball.setVelo(cd.getVelo());
		}

		// Notify observers ... redraw updated view
		this.setChanged();
		this.notifyObservers();

	}

	private Ball moveBallAtCurrentVelo(Ball ball, double time) {
		// TODO Auto-generated method stub
		return ball;
	}

	private CollisionDetails timeUntilCollision() {
		return timeUntilCollisionOneBall(ball);

	}

	private CollisionDetails timeUntilCollisionOneBall(Ball ball) {

		// Find Time Until Collision and also, if there is a collision, the new
		// speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0, 0);
		AGizmoComponent collidedWith = null;

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;



		// Time to collide with any vertical lines
		for (Entry<String, AGizmoComponent> entry : gizmos.entrySet()) {
			String name = entry.getKey();
			AGizmoComponent gizmo = entry.getValue();
			if (gizmo instanceof ILineSegmentCollider) {
				for (LineSegment line : ((ILineSegmentCollider) gizmo).getLineSeg()) {
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
						collidedWith = gizmo;
					}
				}
			}
		}
		return new CollisionDetails(shortestTime, newVelo, ball, collidedWith);

	}

	private Ball movelBallForTime(Ball ball, double time) {

		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getPreciseX() + (xVel * time);
		newY = ball.getPreciseY() + (yVel * time);
		ball.setPreciseX(newX);
		ball.setPreciseY(newY);
		return ball;
	}

	@Override
	public boolean addGizmo(AGizmoComponent gizmo) {
		AGizmoComponent g = getGizmoAt(gizmo.getX(), gizmo.getY());

		if (g != null) {
			removeGizmo(g);
		}
		gizmos.put(gizmo.getGizmoID(), gizmo);

		setChanged();
		notifyObservers();

		// TODO Validation
		return false;
	}

	public AGizmoComponent getGizmoAt(int x, int y) {
		for (AGizmoComponent g : gizmos.values()) {
			if (g.getX() == x && g.getY() == y) {
				return g;
			}
		}
		return null;
	}

	@Override
	public boolean removeGizmo(AGizmoComponent gizmo) {
		gizmos.remove(gizmo.getGizmoID());

		return !(gizmos.containsValue(gizmo));
	}

	@Override
	public Set<AGizmoComponent> getGizmoSet(Class<?> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, AGizmoComponent> getGizmos() {
		return gizmos;
	}

	@Override
	public void setBallSpeed(Ball b, Vect velo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loadFile(String filePath) {
		gizmos = new HashMap<String, AGizmoComponent>();
		fileHandler.loadFile(filePath, this);
	}

	@Override
	public void saveFile(String filePath) {
		fileHandler.saveFile(filePath);
	}

	public Ball getBall() {
		return ball;
	}
}
