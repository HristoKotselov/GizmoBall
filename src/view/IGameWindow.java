package view;

import java.awt.Point;

public interface IGameWindow {

	public void setMode(String mode);

	public Point getCoords();

	public String getFile(String buttonText, String lastLocation);
	
	public void setActionTipsTextArea(String message);
}
