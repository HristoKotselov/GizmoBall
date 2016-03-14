package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class MainEngine extends Observable implements IMainEngine {
	/* Constants */
	final static int L = 20;

	/* Game Component */
	private Set<Ball> ballSet;

	/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
	public Ball ball;// Using one ball to test, this is the only ball for now

	private Map<String, AGizmoComponent> gizmos;

	private Walls gws;

	/* Game Mechanic */
	private List<CollisionDetails> collisionList;

	private PhysicsConfig physicsSettings;
	// TODO might change SpecialCollisionHandler to be static-based too
	private SpecialCollisionHandler sCollisionHandler;
	private Connections customConnections;

	/* Run time values */
	private boolean isPlaying; // used to tell Keyboard ActionListeners when
								// they should be active (only when the game is
								// running)


	public MainEngine() {
		gizmos = new HashMap<String, AGizmoComponent>();
		collisionList = new ArrayList<CollisionDetails>();

		physicsSettings = new PhysicsConfig();
		customConnections = new Connections();

		/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
//		ball = new Ball("Ball", Color.RED, 50, 50, new Angle(45), 50);
	}

	@Override
	public void moveBall() {

		double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

		/*
		 * for (Ball ball : ballSet) { if (ball == null || ball.stopped()) {
		 * System.out.println(
		 * "ball null or stopped, main engine, top of move ball"); return; } }
		 */

		/**
		 * This method is single ball ONLY, why not develop for multBall
		 * instead? It will support single ball too.
		 **/
		/*
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
		*/

		// Notify observers ... redraw updated view
		update();

	}

	private Ball moveBallAtCurrentVelo(Ball ball, double time) {
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

	private void calcTimesUntilCollision() {

		for (Ball ball : ballSet) {
			// Find Time Until Collision and also, if there is a collision, the
			// new
			// speed vector.
			// Create a physics.Circle from Ball
			Circle ballCircle = ball.getCircle();
			Vect ballVelocity = ball.getVelo();
			Vect newVelo = new Vect(0, 0);

			// Now find shortest time to hit a vertical line or a wall line
			double shortestTime = Double.MAX_VALUE;
			double time = 0.0;

			String colliderID = null; // the Gizmo component that the ball will
										// collide with in a collision
										// prediction


			// Time to collide with 4 walls
			ArrayList<LineSegment> lss = gws.getLineSegments();
			for (LineSegment line : lss) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ballVelocity, 1.0);
					colliderID = "Wall";
				}
			}


			// Time to collide with all Gizmos
			Set<Circle> circleSet;
			Set<LineSegment> lsSet;

			Collection<AGizmoComponent> allGizmos = getAllGizmos();

			for (AGizmoComponent gizmo : allGizmos) {
				circleSet = gizmo.getCircles();

				// Checking collision with all the Circles
				for (Circle circle : circleSet) {
					time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1.0);
						colliderID = gizmo.getGizmoID();
					}
				}

				if (gizmo instanceof ILineSegmentCollider) {
					lsSet = ((ILineSegmentCollider) gizmo).getLineSeg();

					// Checking collision with all the Line Segments
					for (LineSegment line : lss) {
						time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectWall(line, ballVelocity, 1.0);
							colliderID = ((AGizmoComponent) gizmo).getGizmoID();
						}
					}
				}
			}

			CollisionDetails cd = new CollisionDetails(shortestTime, newVelo, ball, colliderID);
			collisionList.add(cd);
		}
	}

	@Override
	public boolean addGizmo(AGizmoComponent gizmo) {
		// TODO Validation

		AGizmoComponent g = getGizmoAt(gizmo.getX() / L, gizmo.getY() / L);

		if (g != null) {
			removeGizmo(g);
		}
		gizmos.put(gizmo.getGizmoID(), gizmo);

		update();

		return false;
	}

	public AGizmoComponent getGizmoAt(int x, int y) {
		for (AGizmoComponent g : gizmos.values()) {
			if (g.getX() / L == x && g.getY() / L == y) {
				return g;
			}

			if (g instanceof Flipper) {
				if (g.getX() / L >= x - 1 && g.getX() / L <= x && g.getY() / L >= y - 1 && g.getY() / L <= y) {
					return g;
				}
			}
		}
		return null;
	}

	public AGizmoComponent getGizmo(String name) {
		return gizmos.get(name);
	}

	public boolean rotateGizmo(int x, int y, int degree) {
		// TODO Validation
		return false;
	}

	@Override
	public boolean removeGizmo(AGizmoComponent gizmo) {
		// TODO Handle null
		// TODO remove connections

		gizmos.remove(gizmo.getGizmoID());
		update();

		return !(gizmos.containsValue(gizmo));
	}

	@Override
	public Collection<AGizmoComponent> getAllGizmos() {
		return gizmos.values();
	}

	@Override
	public Map<String, AGizmoComponent> getGizmosMap() {
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
		SaveDataEngine.loadFile(filePath, this);
	}

	@Override
	public void saveFile(String filePath) {
		SaveDataEngine.saveFile(filePath, this);
	}

	/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
	public Ball getBall() {
		return ball;
	}

	public void update() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void rotateGizmo(AGizmoComponent gizmo, int degree) {
		// TODO handle null
		gizmo.rotate(degree);
		update();
	}
}
