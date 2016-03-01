package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IMainEngine;
import model.ISaveDataEngine;

public class LoadFileListener implements ActionListener {
	private IMainEngine model;
	private ISaveDataEngine dataEngine;

	public LoadFileListener(IMainEngine m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("load")) {
			//TODO load file
		}

	}
}
