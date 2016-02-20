package Absorber;

import physics.Vect;

public class CollisionDetails {

	private double tuc; //time until collision
	private Vect velo;
	private Ball b;		// identify which ball is involved in a collision & provide access to the object
	private AGizmoComponent clder;		// identify which Gizmo Component is involved in a collision & provide access to the object

	public CollisionDetails(double t, Vect v, Ball ball, AGizmoComponent collider) {
		tuc = t;
		velo = v;
		clder = collider;
		b = ball;
	}

	public double getTuc() {
		return tuc;
	}

	public Vect getVelo() {
		return velo;
	}
	
	public AGizmoComponent getCollider(){
		return clder;
	}
	
	public Ball getBall(){
		return b;
	}

}
