package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IMainEngine;
import view.IBoard;
import view.IGameWindow;
import view.IMenu;

public class BuildModeButtonListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;

	public BuildModeButtonListener(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("addGizmo")) {
			
		} else if (actionCmd.equals("addBall")) {

		} else if (actionCmd.equals("connect")) {

		} else if (actionCmd.equals("disconnect")) {

		} else if (actionCmd.equals("keyConnect")) {

		} else if (actionCmd.equals("keyDisconnect")) {

		} else if (actionCmd.equals("delete")) {

		} else if (actionCmd.equals("rotate")) {

		} else if (actionCmd.equals("undo")) {

		} else if (actionCmd.equals("redo")) {

		} else if (actionCmd.equals("reloadBoard")) {

		} else if (actionCmd.equals("clear")) {

		} else if (actionCmd.equals("playMode")) {
	// In the final release, this command should change from BUILD MODE to PLAY MODE
			
		/** TODO START of Temporarily Block of Code, REMOVE\CHANGE before final release **/
			System.out.println("Tick " + System.nanoTime());
			if (model == null) {
				System.out.println("ITS NULL");
			} else {
				model.moveBalls();
			}
		/** TODO END of Temporarily Block of Code **/
		}
	}

}
