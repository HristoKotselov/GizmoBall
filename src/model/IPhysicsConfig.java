package model;

import java.util.Observer;

public interface IPhysicsConfig {

	public boolean setGravity(double newGravity);

	public double getGravity();

	public boolean setFrictionCoef1(double mu);

	public double getFrictionCoef1();

	public boolean setFrictionCoef2(double mu2);

	public double getFrictionCoef2();
	
	public void addObserver(Observer o);
}
