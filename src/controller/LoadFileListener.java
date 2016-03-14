package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.IMainEngine;
import view.IGameWindow;

public class LoadFileListener implements ActionListener {
	private IGameWindow window;
	private IMainEngine model;

	public LoadFileListener(IGameWindow w, IMainEngine m) {
		window = w;
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("load")) {
			String path = window.getFile();

			if (path != null) {
				model.loadFile(path);
			}
		}
	}
}