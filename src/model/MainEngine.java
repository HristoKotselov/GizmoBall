package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class MainEngine extends Observable implements IMainEngine, ISaveDataEngine {
	/* Constants */
	final static int L = 20;

	/* Game Component */
	private Set<Ball> ballSet;
//	private Set<CircularBumper> circleSet;
//	private Set<SquareBumper> squareSet;
//	private Set<TriangularBumper> triangleSet;
//	private Set<Flipper> flipperSet;
//	private Set<Absorber> absorberSet;

	private Map<String, AGizmoComponent> gizmos;
	
	// Collection for Collision detection
	private Set<ILineSegmentCollider> lineSegmentColliders;

	
	private Walls gws;

	/* Game Mechanic */
	private List<CollisionDetails> collisionList;
	
	private PhysicsConfig physicsSettings;
	private SpecialCollisionHandler sCollisionHandler;
	private Connections customConnections;
	private SaveDataEngine fileHandler;

	/* Run time values */
	private boolean isPlaying; // used to tell Keyboard ActionListeners when
								// they should be active (only when the game is
								// running)

	public MainEngine() {
		gizmos = new HashMap<String, AGizmoComponent>();
		lineSegmentColliders = new HashSet<ILineSegmentCollider>();

		collisionList = new ArrayList<CollisionDetails>();
		
		physicsSettings = new PhysicsConfig();
		customConnections = new Connections();
		fileHandler = new SaveDataEngine();
	}

	@Override
	public void moveBall() {
		// TODO Auto-generated method stub

	}

	private Ball moveBallAtCurrentVelo(Ball ball, double time) {
		// TODO Auto-generated method stub
		return ball;
	}

	private void timeUntilCollision() {
		
		for(Ball ball : ballSet){
			// Find Time Until Collision and also, if there is a collision, the new
			// speed vector.
			// Create a physics.Circle from Ball
			Circle ballCircle = ball.getCircle();
			Vect ballVelocity = ball.getVelo();
			Vect newVelo = new Vect(0, 0);

			// Now find shortest time to hit a vertical line or a wall line
			double shortestTime = Double.MAX_VALUE;
			double time = 0.0;
			
			String colliderID = null;		// the Gizmo component that the ball will collide with in a collision prediction

			
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
			

			// Time to collide with the Circles of all Gizmos
			Set<Circle> circleSet;
			
			Collection<AGizmoComponent> allGizmos = getAllGizmos();
			
			for(AGizmoComponent gizmo: allGizmos){
				circleSet = gizmo.getCircles();
				
				for(Circle circle : circleSet){
					time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1.0);
						colliderID = gizmo.getGizmoID();
					}
				}
			}
			
			
			// Time to collide with the Line Segment of all Gizmos
			Set<LineSegment> lsSet;
			
			for(ILineSegmentCollider gizmo : lineSegmentColliders){
				lsSet = gizmo.getLineSeg();
				
				for(LineSegment line : lss){
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(line, ballVelocity, 1.0);
						colliderID = ((AGizmoComponent) gizmo).getGizmoID();
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
		
		AGizmoComponent g = getGizmoAt(gizmo.getX(), gizmo.getY());

		if (g != null) {
			removeGizmo(g);
		}
		gizmos.put(gizmo.getGizmoID(), gizmo);

		// Update mapping based on the type of Gizmo, used in Collisions
		if(gizmo instanceof ILineSegmentCollider){
			lineSegmentColliders.add((ILineSegmentCollider) gizmo);
		}
		
		setChanged();
		notifyObservers();
		
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

		// Update mapping based on the type of Gizmo, used in Collisions	
		if(gizmo instanceof ILineSegmentCollider){
			lineSegmentColliders.remove(gizmo);
		}
		
		// TODO remove connection
		
		return !(gizmos.containsValue(gizmo));
	}

	@Override
	public Set<AGizmoComponent> getGizmoSet(Class<?> cls) {
		// TODO Auto-generated method stub
		return null;
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
		fileHandler.loadFile(filePath, this);
	}

	@Override
	public void saveFile(String filePath) {
		fileHandler.saveFile(filePath);
	}

}
