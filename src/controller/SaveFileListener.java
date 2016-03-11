package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IMainEngine;

public class SaveFileListener implements ActionListener {
	private IMainEngine model;

	public SaveFileListener(IMainEngine m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("save")) {
			//TODO save file
		}

	}
}
