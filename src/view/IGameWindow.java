package view;

import java.awt.Color;
import java.awt.Point;

public interface IGameWindow {

	public void setMode(String mode);

	public Point getCoords();

	public String getFile(String buttonText, String lastLocation);
	
	/**
	 * TODO
	 * Set the message displayed by the ActionTips. Note that calling this method
	 * will reset the colour of the text back to black.
	 * @param message
	 */
	public void setActionTipsTextArea(String message);
	
	public void setActionTipsTextAreaColour(Color colour);
}
