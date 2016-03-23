package view;

import java.awt.Color;

public interface IGameWindow {

	public String getFile(String buttonText, String lastLocation);

	/**
<<<<<<< HEAD
	 * TODO Set the message displayed by the ActionTips. Note that calling this method will reset the colour of the text back to black.
	 * 
	 * @param message
=======
	 * Set the message displayed by the ActionTips. Note that calling this method will reset the colour of the text back to black.
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
	 */
	public void setActionTipsTextArea(String message);

	public void setActionTipsTextAreaColour(Color colour);

	public boolean isBuildMode();
}
