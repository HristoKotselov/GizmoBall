package model;

import java.util.Set;

import physics.Vect;

public interface IMainEngine {

	public void moveBall();

	public boolean addGizmo(AGizmoComponent gizmo);

	public boolean removeGizmo(AGizmoComponent gizmo);

	public Set<AGizmoComponent> getGizmoSet(Class<?> cls);

	public void setBallSpeed(Ball b, Vect velo);

	public void start();

	public void stop();

	public boolean isPlaying();
}
