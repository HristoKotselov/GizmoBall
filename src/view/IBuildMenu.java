package view;

public interface IBuildMenu {

	public void setCard();

	public String getSelectedFunction();

	public double getBallDirectionFromGUI();

	public double getBallSpeedFromGUI();
	
	public double getFrictionCoef1FromGUI();
	
	public double getFrictionCoef2FromGUI();
	
	public double getGravityFromGUI();

	public String getSelectedGizmo();

	public String getKeyEventType();

	public String getConnectFunction();
}