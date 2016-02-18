package Absorber;

import physics.Vect;

public class CollisionDetails {
	
	public enum CollisionType {
	    REGULAR, ABSORBER
	}

	private double tuc; //time until collision
	private Vect velo;
	private CollisionType ct;

	public CollisionDetails(double t, Vect v, CollisionType collisionType) {
		tuc = t;
		velo = v;
		ct = collisionType;
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

}
