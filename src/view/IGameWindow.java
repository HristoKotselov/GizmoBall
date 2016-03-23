package view;

import java.awt.Color;

public interface IGameWindow {

	public String getFile(String buttonText, String lastLocation);

	/**
	 * Set the message displayed by the ActionTips. Note that calling this method will reset the colour of the text back to black.
	 */
	public void setActionTipsTextArea(String message);

	public void setActionTipsTextAreaColour(Color colour);

	public boolean isBuildMode();
}
