package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import model.IMainEngine;
import view.IGameWindow;

public class LoadFileListener implements ActionListener {
	private IGameWindow window;
	private IMainEngine model;
	private String lastLocation;

	public LoadFileListener(IGameWindow w, IMainEngine m) {
		window = w;
		model = m;
		lastLocation = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("load")) {
			readLastLoadLocation();


			String path = window.getFile("Open", lastLocation);

			if (path != null) {
				model.loadFile(path);


				updateLastLoadLocation(path);
			}
		}
	}

	private void readLastLoadLocation() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./.memory/loadLoc")));

			while (br.ready()) {
				String line = br.readLine();

				lastLocation = line;
			}

			br.close();
		} catch (Exception e) {
		}
	}

	private void updateLastLoadLocation(String selectedPath) {
		// Now we keep a record of the last loaded File Path in backing store
		try {
			File lastLoadedConfig = new File("./.memory/loadLoc");
			lastLoadedConfig.getParentFile().mkdirs(); // make the directory if it doesn't exist already

			// make the folder hidden if not already
			Path configFolderPath = FileSystems.getDefault().getPath("./.memory"); // retrieve the folder's path as a Path object
			Files.setAttribute(configFolderPath, "dos:hidden", true);

			// the actual writing process
			BufferedWriter bw = new BufferedWriter(new FileWriter(lastLoadedConfig));
			File selectPathAsFile = new File(selectedPath); // uses File's method to get the actual directory instead of pointing to the
															// file
			bw.write(selectPathAsFile.getParent());
			bw.close();
		} catch (Exception e) {
		}
	}
}