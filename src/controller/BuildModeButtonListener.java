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
			gameWindow.switchMode();

			// In the final release, this command should change from BUILD MODE
			// to PLAY MODE

			/* TODO START of Temporarily Block of Code, REMOVE\CHANGE before final release
				System.out.println("Tick " + System.nanoTime());
				if (model == null) {
					System.out.println("ITS NULL");
				} else {
					model.moveBalls();
				}
			 TODO END of Temporarily Block of Code **/
		}
	}

}
