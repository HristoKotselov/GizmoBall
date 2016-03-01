package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IMainEngine;

/**
 * TODO
 * Also handle Tick
 */
public class StartGameListener implements ActionListener {
	private IMainEngine model;

	public StartGameListener(IMainEngine m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
