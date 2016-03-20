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
	private Map<String, AGizmoComponent> gizmos;

	// Sub-set of All Gizmos
	private Set<Ball> ballSet;
	private Set<AStationaryGizmo> stationaryGizmos;
	private Set<AMovingGizmo> movingGizmos;

	/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
	public Ball ball;// Using one ball to test, this is the only ball for now
	
	private Walls gws;

/* Game Mechanic */
	private PhysicsConfig physicsSettings;
	private CollisionHandler collisionHandler;
	private Connections customConnections;


/* Run time values */
	
	/** How long each tick of the ball is. While this value on its doesn't determine how many frames are
	 * drawn per second, it provides the Physics code the required time variable to be as realistic as 
	 * possible **/
	private double moveTime = 1.0/60.0;			// 60 fps
	
	private boolean isPlaying; // used to tell Keyboard ActionListeners when
								// they should be active (only when the game is
								// running)


	public MainEngine() {
		gizmos = new HashMap<String, AGizmoComponent>();
		ballSet = new HashSet<Ball>();
		
		stationaryGizmos = new HashSet<AStationaryGizmo>();
		movingGizmos = new HashSet<AMovingGizmo>();

		physicsSettings = new PhysicsConfig();
		collisionHandler = new CollisionHandler(this);
		customConnections = new Connections();
		
		// Default height\width of the Walls
		gws = new Walls(0, 0, 20 * L, 20 * L);

		/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
//		ball = new Ball("Ball", Color.RED, 50, 50, new Angle(45), 50);
	}

	@Override
	public void moveBalls() {
		// Friction - from 6.170 Final Project Gizmoball
		double mu1 = physicsSettings.getFrictionCoef1();
		double mu2 = physicsSettings.getFrictionCoef2();

		// Gravity - from 6.170 Final Project Gizmoball
		double gravity = physicsSettings.getGravity();

		double frictionScale;
		
		// Need to apply the Physics to all the balls before the collision prediction happen
		for(Ball ball : ballSet){
			if(ball.stopped()){		// if ball stopped, then no calculation will be needed
				continue;
			}
			
			// Apply friction to Ball
			frictionScale = 1 - mu1 * moveTime - ball.getVelo().length() * mu2 * moveTime;
			ball.setVelo(ball.getVelo().times(frictionScale));
	
			// Apply gravity to Ball
			ball.setVelo(ball.getVelo().plus(new Vect(Angle.DEG_90, gravity * moveTime)));
		}
		
		
		// called to get a list of collisions
		List<CollisionDetails> collisionList = calcTimesUntilCollision(); 
		

		// Temp variables setup
		Ball ball;
		double tuc;
		AGizmoComponent collider;


		for (CollisionDetails cd : collisionList) {
			ball = cd.getBall();

			tuc = cd.getTuc(); // i.e. what is the time to the nearest future
								// collision...?

			if (tuc > moveTime) {
				// No collision ...
				moveBallAtCurrentVelo(ball, moveTime);
			} else {

				// We've got a collision in tuc, so move the ball until it directly touches the collider
				moveBallAtCurrentVelo(ball, tuc);	

				collider = getGizmo(cd.getColliderName());
				// Now handle the collision
				collisionHandler.handleCollision(cd, collider);
			}

		}

		// Notify observers ... redraw updated view
		update();

	}

	public void moveBallAtCurrentVelo(Ball ball, double time) {
		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getMovingX() + (xVel * time);
		newY = ball.getMovingY() + (yVel * time);
		ball.setMovingX(newX);
		ball.setMovingY(newY);
	}

	private List<CollisionDetails> calcTimesUntilCollision() {

		List<CollisionDetails> collisionList = new ArrayList<CollisionDetails>();

		for (Ball ball : ballSet) {
			
			if(ball.stopped()){		// if ball stopped, then no calculation will be needed
				continue;
			}
			
			
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
			Set<LineSegment> lss = gws.getLineSegments();
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
					for (LineSegment line : lsSet) {
						time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectWall(line, ballVelocity, 1.0);
							colliderID = ((AGizmoComponent) gizmo).getGizmoID();
						}
					}
				}
				
				if (gizmo instanceof Flipper) {
					Flipper flipper = (Flipper) gizmo;
					lsSet = flipper.getLineSeg();
					circleSet = flipper.getCircles();
					
					// Checking collision with left flipper
					if (flipper.getOrientation() == Flipper.LEFT){
						for (Circle circle : circleSet) {
							time = Geometry.timeUntilRotatingCircleCollision(circle, flipper.getRotationPoint(), 12.5664, ballCircle, ballVelocity);
							if (time < shortestTime) {
								shortestTime = time;
								newVelo = Geometry.reflectRotatingCircle(circle, flipper.getRotationPoint(), 12.5664, ballCircle, ballVelocity, 2.0);
								colliderID = flipper.getGizmoID();
							}
						}
						for (LineSegment line : lsSet) {
							time = Geometry.timeUntilRotatingWallCollision(line, flipper.getRotationPoint(), 12.5664, ballCircle, ballVelocity);
							if (time < shortestTime) {
								shortestTime = time;
								newVelo = Geometry.reflectRotatingWall(line, flipper.getRotationPoint(), 12.5664, ballCircle, ballVelocity, 2.0);
								colliderID = flipper.getGizmoID();
							}
						}
					}
					else { // collision with right flipper
						for (Circle circle : circleSet) {
							time = Geometry.timeUntilRotatingCircleCollision(circle, flipper.getRotationPoint(), -12.5664, ballCircle, ballVelocity);
							if (time < shortestTime) {
								shortestTime = time;
								newVelo = Geometry.reflectRotatingCircle(circle, flipper.getRotationPoint(), -12.5664, ballCircle, ballVelocity, 2.0);
								colliderID = flipper.getGizmoID();
							}
						}
						for (LineSegment line : lsSet) {
							time = Geometry.timeUntilRotatingWallCollision(line, flipper.getRotationPoint(), -12.5664, ballCircle, ballVelocity);
							if (time < shortestTime) {
								shortestTime = time;
								newVelo = Geometry.reflectRotatingWall(line, flipper.getRotationPoint(), -12.5664, ballCircle, ballVelocity, 2.0);
								colliderID = flipper.getGizmoID();
							}
						}
					}
					
					
				}
				
			}

			CollisionDetails cd = new CollisionDetails(shortestTime, newVelo, ball, colliderID);
			collisionList.add(cd);
		}

		return collisionList;
	}

	@Override
	public double getMoveTime() {
		return moveTime;
	}


	@Override
	public void setBallSpeed(Ball b, Vect velo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		isPlaying = true;
	}

	@Override
	public void stop() {
		isPlaying = false;
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return isPlaying;
	}
	
	@Override
	public void reset(){
		for(AGizmoComponent gizmo : getAllGizmos()){
			gizmo.reset();
		}
		
		// Update view
		update();
	}
	
	@Override
	public boolean addGizmo(AGizmoComponent gizmo) {
		// TODO Validation

		if(gizmo instanceof AStationaryGizmo){
			AStationaryGizmo sGizmo = (AStationaryGizmo) gizmo;
			int grid_tile_x = sGizmo.getX() / L;
			int grid_tile_y = sGizmo.getY() / L;
			
			// Remove any overlapping gizmos
			for (int i = 0; i < sGizmo.getBMWidth(); i++) {
				for (int j = 0; j < sGizmo.getBMHeight(); j++) {
					AGizmoComponent g = getStationaryGizmoAt(grid_tile_x + i, grid_tile_y + j);
	
					if (g != null) {
						removeGizmo(g);
					}
				}
			}	
			
			// Add stationary gizmo to Stationary Gizmo Set
			stationaryGizmos.add(sGizmo);
		}
		else if(gizmo instanceof AMovingGizmo){
			AMovingGizmo mGizmo = (AMovingGizmo) gizmo;
			
			// TODO implementation for movable Gizmos 
			// (that can technically be placed at any pixel, as look as it don't overlap with existing ones)
			
			
			// Add stationary gizmo to Stationary Gizmo Set
			movingGizmos.add(mGizmo);
			
			// if moving Gizmo is a Ball, then we add it to a special subset
			if(mGizmo instanceof Ball){
				ballSet.add((Ball) mGizmo);
			}
			
		}

		// Add new gizmo to the map of ALL Gizmos
		gizmos.put(gizmo.getGizmoID(), gizmo);

		// Update view
		update();

		return false;
	}

	@Override
	public boolean removeGizmo(AGizmoComponent gizmo) {
		// TODO Handle null
		// TODO remove connections

		gizmos.remove(gizmo.getGizmoID());
		if(gizmo instanceof AStationaryGizmo){
			stationaryGizmos.remove(gizmo);
		}
		else if(gizmo instanceof AMovingGizmo){
			movingGizmos.remove(gizmo);
		}

		update();

		return !(gizmos.containsValue(gizmo));
	}

	@Override
	public void rotateGizmo(AGizmoComponent gizmo, int degree) {
		// TODO handle null
		gizmo.rotate(degree);
		update();
	}
	
	/* (non-Javadoc)
	 * @see model.IMainEngine#moveGizmo(model.AGizmoComponent, int, int)
	 */
	@Override
	public boolean moveGizmoByL(AGizmoComponent gizmo, int grid_tile_x, int grid_tile_y) {
		// TODO handle null
		// TODO handle absorber off screen?
		// TODO Validation
		boolean spaceOccupied = false;
		
		if(gizmo instanceof AStationaryGizmo){
			AStationaryGizmo sGizmo = (AStationaryGizmo) gizmo;
			
			// Check for any overlapping Stationary Gizmos
			for (int i = 0; i < sGizmo.getBMWidth(); i++) {
				for (int j = 0; j < sGizmo.getBMHeight(); j++) {
					AGizmoComponent g = getStationaryGizmoAt(grid_tile_x + i, grid_tile_y + j);
				
					if (g != null && 
						g != sGizmo) {		// no need to get against the same Gizmo! If this happens, Flipper will be not able to move 1 square left/right
						spaceOccupied = true;
					}
				}
			}
			
			// TODO Check for any overlapping Moving Gizmos (i.e. Ball)
		}
		
		if(!spaceOccupied){
			gizmo.move(grid_tile_x, grid_tile_y);
		}

		update();

		return (!spaceOccupied);
	}
	
	 // TODO make moveGizmoByPixel()
	
	@Override
	public AStationaryGizmo getStationaryGizmoAt(int grid_tile_x, int grid_tile_y) {
		int gizmosX_in_L;
		int gizmosY_in_L;
		
		for (AStationaryGizmo sGizmo : stationaryGizmos) {
			gizmosX_in_L = sGizmo.getX() / L;
			gizmosY_in_L = sGizmo.getY() / L;
			
			if (gizmosX_in_L <= grid_tile_x && gizmosX_in_L + sGizmo.bmWidth > grid_tile_x && 
				gizmosY_in_L <= grid_tile_y && gizmosY_in_L + sGizmo.bmHeight > grid_tile_y) {
				return sGizmo;
			}
		}
		
		return null;
	}
	

	// TODO make alternate version of getGizmoAt (double), that one will get called first in the BuildModeMouseListener

	public AGizmoComponent getGizmo(String name) {
		return gizmos.get(name);
	}
	
	@Override
	public Map<String, AGizmoComponent> getGizmosMap() {
		return gizmos;
	}
	
	@Override
	public Collection<AGizmoComponent> getAllGizmos() {
		return gizmos.values();
	}
	
	@Override
	public Collection<AStationaryGizmo> getAllStationaryGizmos() {
		return stationaryGizmos;
	}
	
	@Override
	public Collection<AMovingGizmo> getAllMovingGizmos() {
		return movingGizmos;
	}

	@Override
	public void loadFile(String filePath) {
		// get rid of all existing Gizmos
		gizmos = new HashMap<String, AGizmoComponent>(); 
		stationaryGizmos = new HashSet<AStationaryGizmo>();
		movingGizmos = new HashSet<AMovingGizmo>();
		SaveDataEngine.loadFile(filePath, this);
	}

	@Override
	public void saveFile(String filePath) {
		SaveDataEngine.saveFile(filePath, this);
	}
	
	/* (non-Javadoc)
	 * @see model.IMainEngine#setWallDimensions(int, int)
	 */
	@Override
	public void setWallDimensions(int width, int height){
		gws = new Walls(0, 0, width, height);
	}
	
	@Override
	public int getLInPixels(){
		return L;
	}

	/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
	public Ball getBall() {
		return ball;
	}

	public void update() {
		setChanged();
		notifyObservers();
	}
	
}
