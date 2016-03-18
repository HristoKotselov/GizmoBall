package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.IMainEngine;
import view.IGameWindow;

public class SaveFileListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow window;

	public SaveFileListener(IGameWindow w, IMainEngine m) {
		model = m;
		window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("save")) {
			String path = window.getFile("Save");

			if (path != null) {
				model.saveFile(path);
			}
		}
	}
}
