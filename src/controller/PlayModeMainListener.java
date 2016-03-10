package controller;

import java.awt.event.ActionEvent;
import model.IMainEngine;
import view.IBoard;
import view.IMenu;

public class PlayModeMainListener extends AMainListener {

	public PlayModeMainListener(IMainEngine model, IBoard board, IMenu menu) {
		super(model, board, menu);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// check out AMainListener's method!

		String actionCmd = e.getActionCommand();
	
		if (actionCmd.equals("start")) {

		} else if (actionCmd.equals("tick")) {

		} else if (actionCmd.equals("stop")) {

		} else if (actionCmd.equals("reload")) {

		} else if (actionCmd.equals("buildMode")) {

		}
	}

}
