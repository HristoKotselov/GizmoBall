package model;

import java.util.Collection;
import java.util.Map;
import java.util.Observer;
import physics.Vect;

public interface IMainEngine {

	public void moveBall();

	public boolean addGizmo(AGizmoComponent gizmo);

	public boolean removeGizmo(AGizmoComponent gizmo);

	public void rotateGizmo(AGizmoComponent gizmo, int degree);

	public AGizmoComponent getGizmoAt(int x, int y);

	public Collection<AGizmoComponent> getAllGizmos();

	public Map<String, AGizmoComponent> getGizmosMap();

	public void setBallSpeed(Ball b, Vect velo);

	public void start();

	public void stop();

	public boolean isPlaying();

	public void loadFile(String filePath);

	public void saveFile(String filePath);

	public void addObserver(Observer o);

	/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
	public Ball getBall();
}