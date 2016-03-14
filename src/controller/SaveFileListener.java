package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ISaveDataEngine;
import view.IGameWindow;

public class SaveFileListener implements ActionListener {
	private IGameWindow window;
	private ISaveDataEngine dataEngine;

	public SaveFileListener(IGameWindow w, ISaveDataEngine s) {
		window = w;
		dataEngine = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("save")) {
			String path = window.getFile();

			if (path != null) {
				dataEngine.saveFile(path);
			}
		}
	}
}