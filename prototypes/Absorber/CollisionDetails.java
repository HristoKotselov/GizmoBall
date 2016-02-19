package Absorber;

import physics.Vect;

public class CollisionDetails {
	
	public enum CollisionType {
	    REGULAR, ABSORBER
	}

	private double tuc; //time until collision
	private Vect velo;
	private CollisionType ct;
	private Ball b;
	private AGizmoComponent clder;

	public CollisionDetails(double t, Vect v, CollisionType collisionType, Ball ball, AGizmoComponent collider) {
		tuc = t;
		velo = v;
		ct = collisionType;
		clder = collider;
		b = ball;
	}

	public double getTuc() {
		return tuc;
	}

	public Vect getVelo() {
		return velo;
	}
	
	public CollisionType getCollisionType(){
		return ct;
	}
	
	public AGizmoComponent getCollider(){
		return clder;
	}
	
	public Ball getBall(){
		return b;
	}

}
