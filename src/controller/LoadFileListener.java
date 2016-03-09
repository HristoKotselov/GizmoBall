package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ISaveDataEngine;
import view.IGameWindow;

public class LoadFileListener implements ActionListener {
	private IGameWindow window;
	private ISaveDataEngine dataEngine;

	public LoadFileListener(IGameWindow w, ISaveDataEngine s) {
		window = w;
		dataEngine = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("load")) {
			String path = window.getFile();

			if (path != null) {
				dataEngine.loadFile(path);
			}
		}
	}
}