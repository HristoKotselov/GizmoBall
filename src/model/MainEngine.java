package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
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
	}

	@Override
	public void moveBall() {
		// TODO Auto-generated method stub

	}

	private Ball moveBallAtCurrentVelo(Ball ball, double time) {
		// TODO Auto-generated method stub
		return ball;
	}

	private CollisionDetails timeUntilCollision() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addGizmo(AGizmoComponent gizmo) {
		gizmos.put(gizmo.getGizmoID(), gizmo);
		setChanged();
		notifyObservers();
		return false;
	}

	@Override
	public boolean removeGizmo(AGizmoComponent gizmo) {
		// TODO Auto-generated method stub
		return false;
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
}
