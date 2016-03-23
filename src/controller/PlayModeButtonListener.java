package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.IMainEngine;
import view.IGameWindow;

public class PlayModeButtonListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;

	/**
	 * The timer handle the implementation need to get a new tick occur in Gizmo Ball periodically. Much like MainEngine's moveTime; the
	 * lower this value, the smoother the animation will be, but also more computationally expensive. Essentially, this can thought of as
	 * frames per second.
	 **/
	private Timer timer;

	public PlayModeButtonListener(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;

		// convert to milliseconds
		int moveTimeInMS = (int) (model.getMoveTime() * 1000);
		timer = new Timer(moveTimeInMS, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// is ActionEvent the automatic run timer?
		if (e.getSource() == timer) {
			model.moveBalls();
		} else { // ... then it is a command selected by the user
			switch (e.getActionCommand()) {
				case "start":
					model.start();
					timer.start();
					break;

				case "stop":
					model.stop();
					timer.stop();
					break;

				case "tick":
					model.moveBalls();
					break;

				case "reload":
					model.reset();
					break;

				case "buildMode":
					model.reset();
					model.stop();
					timer.stop();
					gameWindow.setMode("Build Mode");
					break;
					
				case "dynamicedit":
					model.update();
					break;
			}
		}
	}

}
