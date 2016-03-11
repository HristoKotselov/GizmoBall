package controller;

import java.awt.event.ActionEvent;
import model.IMainEngine;
import view.IBoard;
import view.IMenu;

public class BuildModeMainListener extends AMainListener {

	// TODO Basically need to list all possible Controller associated with Build Mode here
	private AddGizmoListener addGizmoAL;

	public BuildModeMainListener(IMainEngine model, IBoard board, IMenu menu) {
		super(model, board, menu);

		addGizmoAL = new AddGizmoListener(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// check out AMainListener's method!

		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("addGizmo")) {
			setBoardMouseListener(addGizmoAL);
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

		}
	}

}
