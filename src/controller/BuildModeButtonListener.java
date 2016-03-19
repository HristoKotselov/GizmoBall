package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IMainEngine;
import view.IGameWindow;

public class BuildModeButtonListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;

	public BuildModeButtonListener(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("resetBoard")) {

		} else if (actionCmd.equals("playMode")) {
			gameWindow.setMode("Play Mode");
		}
	}

}
