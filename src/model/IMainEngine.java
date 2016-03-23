package model;

import java.util.Collection;
import java.util.Observer;

//TODO split this up a bit if possible
public interface IMainEngine {

	public static final int L = 20;

	public boolean moveBalls();

	public double getMoveTime();

	public IPhysicsConfig getPhysicsConfig();

	public void reset();

	public boolean addGizmo(AGizmoComponent gizmo);

	public boolean removeGizmo(AGizmoComponent gizmo);

	public boolean rotateGizmo(AGizmoComponent gizmo, int degree);
	
	public AMovingGizmo getBall();

	/**
	 * Method called when the user decides to move a Gizmo.
	 * 
	 * @param gizmo
	 *            the Gizmo to move.
	 * @param grid_tile_x
	 *            new X coordinate (in L)
	 * @param grid_tile_y
	 *            new Y coordinate (in L)
	 * @return TRUE if the move operation succeeded (i.e. a Gizmo is moved into an empty space), FALSE if the move operation failed (i.e.
	 *         the destination is already occupied with another Gizmo)
	 */
	public boolean moveGizmoByL(AGizmoComponent gizmo, int grid_tile_x, int grid_tile_y);
	
	public boolean moveGizmoByPixels(AMovingGizmo moveG, int x, int y);

	public AStationaryGizmo getStationaryGizmoAt(int grid_tile_x, int grid_tile_y);
	
	public AMovingGizmo getMovingGizmoAt(int x, int y);

	public Collection<AGizmoComponent> getAllGizmos();

	public void clearAllGizmos();

	public void loadFile(String filePath);

	public void saveFile(String filePath);

	/**
	 * Set the preferred Wall width & height for the Gizmo Board (pixels). This method will change the Wall object inside the MainEngine to
	 * the appropriate height/width.
	 * 
	 * @param width
	 *            the new width for the walls of the board, in pixels.
	 * @param height
	 *            the new height for the walls of the board, in pixels.
	 */
	public void setWallDimensions(int width, int height);

	public void addObserver(Observer o);

	public void trigger(int key, int type);

	public void bindKey(AGizmoComponent gizmo, int key, int type);

	public void addConnection(AGizmoComponent moveG, AGizmoComponent g);

	public void update();
}