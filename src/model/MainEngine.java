package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import model.gizmos.Absorber;
import model.gizmos.Ball;
import model.gizmos.Flipper;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class MainEngine extends Observable implements IMainEngine {
	/* Constants */
	public final static int L = 20;

	/* Game Component */
	private Map<String, AGizmoComponent> gizmos;

	// Sub-set of All Gizmos
	private Ball ball;
	private Set<AStationaryGizmo> stationaryGizmos;
	private Set<AMovingGizmo> movingGizmos;

	private Walls gws;

	/* Game Mechanic */
	private PhysicsConfig physicsSettings;
	private Connections customConnections;


	/* Run time values */

	/**
	 * How long each tick of the ball is. While this value on its doesn't determine how many frames are drawn per second, it provides the
	 * Physics code the required time variable to be as realistic as possible
	 **/
	private double moveTime = 1.0 / 60.0; // 60 fps

	public MainEngine() {
		gizmos = new HashMap<String, AGizmoComponent>();

		stationaryGizmos = new HashSet<AStationaryGizmo>();
		movingGizmos = new HashSet<AMovingGizmo>();

		physicsSettings = new PhysicsConfig();
		customConnections = new Connections();

		// Default height\width of the Walls
		gws = new Walls(0, 0, 20 * L, 20 * L);
	}

	
	@Override
	public boolean moveBalls() {
		if (ball == null || ball.stopped()) {
			return false;
		}

		// Friction - from 6.170 Final Project Gizmoball
		double mu1 = physicsSettings.getFrictionCoef1();
		double mu2 = physicsSettings.getFrictionCoef2();

		// Gravity - from 6.170 Final Project Gizmoball
		double gravity = physicsSettings.getGravity();

		double frictionScale;

		// Need to apply the Physics to all the balls before the collision prediction happen
		if (!ball.stopped()) { // if ball stopped, then no calculation will be needed

			// Apply friction to Ball
			frictionScale = 1 - mu1 * moveTime - ball.getVelo().length() * mu2 * moveTime;
			ball.setVelo(ball.getVelo().times(frictionScale));

			// Apply gravity to Ball
			ball.setVelo(ball.getVelo().plus(new Vect(Angle.DEG_90, gravity * moveTime)));
		}


		// called to get collision details
		CollisionDetails cd = calcTimeUntilCollision();

		// Find the time until the nearest collision
		double tuc = cd.getTuc();
		double time = Math.min(moveTime, tuc);

		moveBallAtCurrentVelo(time);

		if (time == tuc) {
			handleCollision(cd);
		}

		// Update other gizmos
		for (AGizmoComponent g : gizmos.values()) {
			g.update(time);
		}

		// Notify observers and redraw updated view
		update();

		return true;
	}

	public void moveBallAtCurrentVelo(double time) {
		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getMovingX() + (xVel * time);
		newY = ball.getMovingY() + (yVel * time);
		ball.setMovingX(newX);
		ball.setMovingY(newY);
	}


	/**
	 * HELPER METHOD
	 */
	private CollisionDetails calcTimeUntilCollision() {
		// Find Time Until Collision and also, if there is a collision, the new speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0, 0);

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;

		String colliderID = null; // the Gizmo component that the ball will collide with in a collision prediction

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

			if (gizmo instanceof Flipper) {

				Flipper flipper = (Flipper) gizmo;
				lsSet = flipper.getLineSeg();
				circleSet = flipper.getCircles();

				double angVel = Math.toRadians(1080);
				Vect rotPoint = flipper.getRotationPoint();

				if (flipper.isStationary()) {
					angVel = 0;
				}

				if (flipper.getOrientation() == Flipper.LEFT) {
					angVel = -angVel;
				}

				for (Circle circle : circleSet) {
					time = Geometry.timeUntilRotatingCircleCollision(circle, rotPoint, angVel, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectRotatingCircle(circle, rotPoint, angVel, ballCircle, ballVelocity, 0.95);
						colliderID = flipper.getGizmoID();
					}
				}
				for (LineSegment line : lsSet) {
					time = Geometry.timeUntilRotatingWallCollision(line, rotPoint, angVel, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectRotatingWall(line, rotPoint, angVel, ballCircle, ballVelocity, 0.95);
						colliderID = flipper.getGizmoID();
					}
				}



			} else {
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
							colliderID = gizmo.getGizmoID();
						}
					}
				}
			}

		}


		CollisionDetails cd = new CollisionDetails(shortestTime, newVelo, ball, colliderID, this);

		return cd;
	}


	/**
	 * HELPER METHOD
	 */
	private void handleCollision(CollisionDetails cd) {
		Ball ball = cd.getBall();
		AGizmoComponent collider = getGizmo(cd.getColliderName());

		// gizmo == null means its the wall
		if (collider != null) {

			switch (collider.getClass().getSimpleName()) {
				case "Absorber":
					// Ball should NEVER bounce away from Absorber, so Ball speed is not changed automatically
					break;


				default: // procedures for a normal collision (that is not wall collision)
					ball.setVelo(cd.getVelo()); // Post collision velocity ...
					break;
			}

			// call the Gizmo's Ball trigger
			collider.ballTriggered(cd);
			// call the Gizmo's connections, that will activate the action of connected Gizmos
			Set<AGizmoComponent> actionToDo = customConnections.getGizmoTriggerConnections(collider);

			if (actionToDo != null) {
				for (AGizmoComponent g : actionToDo) {
					g.action();
				}
			}
		} else {
			// procedures for a wall collision
			ball.setVelo(cd.getVelo()); // Post collision velocity ...
		}

	}

	@Override
	public double getMoveTime() {
		return moveTime;
	}

	@Override
	public IPhysicsConfig getPhysicsConfig() {
		return physicsSettings;
	}

	@Override
	public void reset() {
		for (AGizmoComponent gizmo : getAllGizmos()) {
			gizmo.reset();
		}
		if(ball != null){
			ball.reset();
		}
		
		// Update view
		update();
	}

	@Override
	public boolean addGizmo(AGizmoComponent gizmo) {

		boolean spaceOccupied = false, outsideWall = false;

		// ... really to check if pointed location contains a Absorber
		AGizmoComponent gizmoAtPointedLocation = getStationaryGizmoAt(gizmo.getX() / L, gizmo.getY() / L);

		if (gizmo instanceof Ball && gizmoAtPointedLocation instanceof Absorber) {
			setupBallInAbsorber((Ball) gizmo, (Absorber) gizmoAtPointedLocation);

		} else if (gizmo instanceof AStationaryGizmo) {
			AStationaryGizmo sGizmo = (AStationaryGizmo) gizmo;
			int grid_tile_x = sGizmo.getX() / L;
			int grid_tile_y = sGizmo.getY() / L;

			// Check for any overlapping Gizmos
			spaceOccupied = checkGizmoOverlap(sGizmo, grid_tile_x, grid_tile_y);

			// Check for the walls
			outsideWall = checkForWalls(sGizmo, grid_tile_x, grid_tile_y);

			if (!spaceOccupied && !outsideWall) {
				// Add stationary gizmo to Stationary Gizmo Set
				stationaryGizmos.add(sGizmo);
			}

		} else if (gizmo instanceof AMovingGizmo) {
			AMovingGizmo mGizmo = (AMovingGizmo) gizmo;
			int x = mGizmo.getX();
			int y = mGizmo.getY();

			// Check for any overlapping Gizmos
			spaceOccupied = checkGizmoOverlap(mGizmo, x, y);

			// Check for the walls
			outsideWall = checkForWalls(mGizmo, x, y);

			if (!spaceOccupied && !outsideWall) {
				// Add moving gizmo to Moving Gizmo Set
//				movingGizmos.add(mGizmo);

				// if moving Gizmo is a Ball, then we add it to a special subset
				if (mGizmo instanceof Ball) {
					ball = ((Ball) mGizmo);
					movingGizmos.add(ball);
					update();
					return true;
				}
			}

		}

		if (!spaceOccupied && !outsideWall) {
			update();

			if(gizmo instanceof Ball){	
			}
			// Add new gizmo to the map of ALL Gizmos
			else {
				if(gizmos.put(gizmo.getGizmoID(), gizmo) == null) {
					return true;
				}
			}
		}

		return false;

	}

	private void setupBallInAbsorber(Ball ball, Absorber absorber) {
		// register the new Ball
		this.ball = ball;
		movingGizmos.add(ball);

		// procedures for registering the ball into the Absorber
		int absorberX = absorber.getX();
		int absorberY = absorber.getY();
		int absorberWidthInPixels = absorber.getBMWidth() * L;
		int absorberHeighInPixels = absorber.getBMHeight() * L;

		ball.stop();
		ball.setX((int) (absorberX + absorberWidthInPixels - (0.25 * MainEngine.L)));
		ball.setY((int) (absorberY + absorberHeighInPixels - (0.25 * MainEngine.L)));
		ball.setMovingX(absorberX + absorberWidthInPixels - (0.25 * MainEngine.L));
		ball.setMovingY(absorberY + absorberHeighInPixels - (0.25 * MainEngine.L));
		absorber.addCapturedBall(ball);

		ball.setInitialVelo(new Vect(Angle.DEG_270, 0)); // make the initial velocity 0
		absorber.addInitialCapturedBall(ball); // so Absorber retains the balls even if reseted
		ball.setStartInAbsorber(absorber); // so Ball have a reference to the Absorber & doesn't fall out of the Absorber when the game reset
	}

	@Override
	public boolean removeGizmo(AGizmoComponent gizmo) {
		gizmos.remove(gizmo.getGizmoID());

		if (gizmo instanceof AStationaryGizmo) {
			stationaryGizmos.remove(gizmo);
		} else if (gizmo instanceof AMovingGizmo) {
			movingGizmos.remove(gizmo);
		}
		
		if (gizmo instanceof Ball) {
			ball = null;
		} 
		if(gizmo instanceof Absorber){
			Absorber abs = (Absorber) gizmo;
			
			abs.removeALLCapturedBalls();
		}

		update();

		return !(gizmos.containsValue(gizmo));
	}

	@Override
	public boolean rotateGizmo(AGizmoComponent gizmo, int degree) {
		gizmo.rotate(degree);

		// Check for any overlapping Moving Gizmos
		for (AMovingGizmo mGizmo : movingGizmos) {
			Rectangle gizmoBounds = gizmo.getDrawingShape().getBounds();

			Rectangle mGizmoBounds = mGizmo.getDrawingShape().getBounds();

			if (gizmoBounds.intersects(mGizmoBounds)) {
				gizmo.rotate(degree);
				gizmo.rotate(degree);
				gizmo.rotate(degree); // rotate 3 times back to original position
				return false;
			}
		}

		update();
		return true;
	}

	/* (non-Javadoc)
	 * @see model.IMainEngine#moveGizmo(model.AGizmoComponent, int, int)
	 */
	@Override
	public boolean moveGizmoByL(AGizmoComponent gizmo, int grid_tile_x, int grid_tile_y) {
		boolean spaceOccupied = false, outsideWall = false;
		
		if (gizmo instanceof AStationaryGizmo) {
			AStationaryGizmo sGizmo = (AStationaryGizmo) gizmo;

			// Check for any overlapping Gizmos
			spaceOccupied = checkGizmoOverlap(sGizmo, grid_tile_x, grid_tile_y);

			// Check for the walls
			outsideWall = checkForWalls(sGizmo, grid_tile_x, grid_tile_y);
		}

		if (!spaceOccupied && !outsideWall) {
			gizmo.move(grid_tile_x, grid_tile_y);
		}

		update();

		return (!spaceOccupied && !outsideWall);
	}

	public boolean moveGizmoByPixels(AMovingGizmo gizmo, int x, int y) {
		boolean spaceOccupied = false, outsideWall = false;

		// ... really to check if pointed location contains a Absorber
		AGizmoComponent gizmoAtPointedLocation = getStationaryGizmoAt(x / L, y / L);

		if (gizmo instanceof Ball && gizmoAtPointedLocation instanceof Absorber) {
			setupBallInAbsorber((Ball) gizmo, (Absorber) gizmoAtPointedLocation);
		} else {
			// Check for any overlapping Gizmos
			spaceOccupied = checkGizmoOverlap(gizmo, x, y);

			// Check for the walls
			outsideWall = checkForWalls(gizmo, x, y);
			if (!spaceOccupied && !outsideWall) {
				gizmo.move(x, y);
				
				// special action need to be done if the Ball is not in Absorber
				if(gizmo instanceof Ball){
					Ball ball = (Ball) gizmo;
					
					if(ball.getStartInAbsorber() != null){
						// use Absorber's remove method to set the attributes for Ball to be out-of-Absorber
						ball.getStartInAbsorber().removeCapturedBall(ball);
					}
				}
			}
		}
		update();

		return (!spaceOccupied && !outsideWall);



	}

	/**
	 * Helper Method
	 **/
	private boolean checkGizmoOverlap(AStationaryGizmo sGizmo, int new_grid_tile_x, int new_grid_tile_y) {
		boolean spaceOccupied = false;

		// Check for any overlapping Stationary Gizmos
		for (int i = 0; i < sGizmo.getBMWidth(); i++) {
			for (int j = 0; j < sGizmo.getBMHeight(); j++) {
				AGizmoComponent g = getStationaryGizmoAt(new_grid_tile_x + i, new_grid_tile_y + j);

				if (g != null &&
						g != sGizmo) { // no need to get against the same
										// Gizmo! If this happens, Flipper
										// will be not able to move 1 square
										// left/right
					spaceOccupied = true;
				}
			}
		}

		// Check for any overlapping Moving Gizmos
		for (AMovingGizmo mGizmo : movingGizmos) {
			/* The bounding box is a rectangular object that encloses around the Shape object
			 * of a gizmo, and it can be used to check whether a moving Gizmo overlap a 
			 * Stationary Gizmo, required as moving Gizmos such as ball can get placed in the 
			 * middle of grid squares. In a way, this box can be thought of like the concept 
			 * of "Hitbox" in video games.
			 */
			Rectangle sGizmoBounds = sGizmo.getDrawingShape().getBounds();

			// Divide by L = grid_tile_squares, Multiple by L = pixels
			if (sGizmo.getX() / L != new_grid_tile_x || sGizmo.getY() / L != new_grid_tile_y) { // i.e. Is it Move Gizmo?
				int diffInGridTileX = new_grid_tile_x - (sGizmo.getX() / L);
				int diffInGridTileY = new_grid_tile_y - (sGizmo.getY() / L);
				sGizmoBounds.setLocation(sGizmo.getX() + diffInGridTileX * L, sGizmo.getY() + diffInGridTileY * L);
			}

			Rectangle mGizmoBounds = mGizmo.getDrawingShape().getBounds();

			if (sGizmoBounds.intersects(mGizmoBounds)) {
				spaceOccupied = true;
			}
		}

		return spaceOccupied;
	}

	/**
	 * Helper Method
	 **/
	private boolean checkForWalls(AStationaryGizmo sGizmo, int new_grid_tile_x, int new_grid_tile_y) {
		boolean outsideWall = false;

		// only need to check for RHS overlap due to all Stationary Gizmo starting from Top-Left corner of a square
		if ((new_grid_tile_x + sGizmo.getBMWidth()) > gws.getWidthInL() ||
				(new_grid_tile_y + sGizmo.getBMHeight()) > gws.getHeightInL()) {
			outsideWall = true;
		}

		return outsideWall;
	}

	/**
	 * Helper Method
	 **/
	private boolean checkGizmoOverlap(AMovingGizmo mGizmo, int new_x, int new_y) {
		boolean spaceOccupied = false;

		Rectangle mGizmoBounds = mGizmo.getDrawingShape().getBounds();
		int mGizmoX = mGizmo.getX();
		int mGizmoY = mGizmo.getY();

		// Divide by L = grid_tile_squares, Multiple by L = pixels
		if (mGizmoX != new_x || mGizmoY != new_y) { // i.e. Is it Move Gizmo?
			int diffInX = new_x - mGizmoX;
			int diffInY = new_y - mGizmoY;
			mGizmoBounds.setLocation(mGizmoBounds.x + diffInX, mGizmoBounds.y + diffInY);
		}

		// Check for any overlapping Stationary Gizmos
		for (AStationaryGizmo sGizmo : stationaryGizmos) {
			if (mGizmoBounds.intersects(sGizmo.getDrawingShape().getBounds())) {
				spaceOccupied = true;
			}
		}

		// Check for any overlapping Moving Gizmos
		for (AMovingGizmo g : movingGizmos) {
			Rectangle gBounds = g.getDrawingShape().getBounds();

			if (mGizmoBounds.intersects(gBounds)) {
				spaceOccupied = true;
			}
		}

		return spaceOccupied;
	}

	/**
	 * Helper Method
	 **/
	private boolean checkForWalls(AMovingGizmo mGizmo, int new_x, int new_y) {
		boolean outsideWall = false;

		Rectangle mGizmoBounds = mGizmo.getDrawingShape().getBounds();


		// only need to check for RHS overlap due to all Stationary Gizmo starting from Top-Left corner of a square
		if ((new_x + mGizmoBounds.getWidth()) > gws.getWidthInL() * L ||
				(new_y + mGizmoBounds.getHeight()) > gws.getHeightInL() * L) {
			outsideWall = true;
		}

		return outsideWall;
	}


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

	@Override
	public AMovingGizmo getMovingGizmoAt(int x, int y) {
		Point mouseClick = new Point(x, y);

		for (AMovingGizmo mGizmo : movingGizmos) {
			Rectangle mGizmoBounds = mGizmo.getDrawingShape().getBounds();

			if (mGizmoBounds.contains(mouseClick)) {
				return mGizmo;
			}
		}

		return null;
	}
	

	public AGizmoComponent getGizmo(String name) {
		return gizmos.get(name);
	}

	@Override
	public Collection<AGizmoComponent> getAllGizmos() {
		return gizmos.values();
	}

	public Connections getConnections() {
		return customConnections;
	}

	@Override
	public void clearAllGizmos() {
		gizmos.clear();
		stationaryGizmos.clear();
		movingGizmos.clear();
		ball = null;

		update();
	}

	@Override
	public void loadFile(String filePath) {
		// get rid of all existing Gizmos
		clearAllGizmos();

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
	public void setWallDimensions(int width, int height) {
		gws = new Walls(0, 0, width, height);
	}

	@Override
	public void trigger(int key, int type) {
		Set<AGizmoComponent> s = customConnections.getKeyConnections(key, type);

		for (AGizmoComponent g : s) {
			g.action();
		}
	}

	@Override
	public void bindKey(AGizmoComponent gizmo, int key, int type) {
		if (key == -1) {
			customConnections.removeAllKeyBindings(gizmo);

		} else if (type == -1) {
			customConnections.clearKeyConnection(key, gizmo, KeyEvent.KEY_PRESSED);
			customConnections.clearKeyConnection(key, gizmo, KeyEvent.KEY_RELEASED);

		} else {
			customConnections.addKeyConnection(key, type, gizmo);
		}
	}

	@Override
	public void addConnection(AGizmoComponent g1, AGizmoComponent g2) {
		if (g2 != null) {
			customConnections.addGizmoTriggerConnection(g1, g2);
		} else {
			customConnections.removeAllGizmoConnections(g1);
		}
	}

	@Override
	public void removeConnection(AGizmoComponent g1, AGizmoComponent g2) {
		customConnections.removeGizmoTriggerConnection(g1, g2);
	}

	@Override
	public AMovingGizmo getBall() {
		return ball;
	}

	@Override
	public void update() {
		setChanged();
		notifyObservers();
	}
}