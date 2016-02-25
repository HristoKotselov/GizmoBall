package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IMainEngine;
import model.ISaveDataEngine;

public class SaveFileListener implements ActionListener {
	private IMainEngine m;
	private ISaveDataEngine dataEngine;

	
	public 	SaveFileListener(IMainEngine m) {
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("save")) {
			//TODO save file
	}

}}
