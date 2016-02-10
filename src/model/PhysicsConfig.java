package model;

public class PhysicsConfig {

	//!!!!!!not sure if this is correct or changed by Scott for his demo
	private double moveTime = 1.0/60.0; // 0.05 = 20 times per second as per
								// Gizmoball
	
	// Friction
	private double mu1 = 0.025;
	private double mu2 = 0.025;
	
	// Gravity
	private int gravity = 25;
	
	public PhysicsConfig(){
		
	}
	
	public double getMoveTime(){
		return moveTime;
	}
	
	public int getGravity(){
		return gravity;
	}
	
	public double getFrictionCoef1(){
		return mu1;
	}
	
	public double getFrictionCoef2(){
		return mu2;
	}
}
