package view;

import java.awt.Point;

import javax.swing.JTextArea;

public interface IGameWindow {

	public void setMode(String mode);

	public Point getCoords();

	public String getFile(String buttonText);
	
	public void setActionTipsTextArea(String message);
}
