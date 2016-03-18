package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IMainEngine;
import view.IBoard;
import view.IGameWindow;
import view.IMenu;

public class PlayModeButtonListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;

	public PlayModeButtonListener(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/** Check out AMainListener's method! **/

		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("start")) {
			/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
			System.out.println("start clicked");
		} else if (actionCmd.equals("tick")) {

		} else if (actionCmd.equals("stop")) {

		} else if (actionCmd.equals("reload")) {

		} else if (actionCmd.equals("buildMode")) {
			gameWindow.setMode("Build Mode");
		}
	}

}
