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

public class SaveFileListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow window;
	private String lastLocation;

	public SaveFileListener(IGameWindow w, IMainEngine m) {
		model = m;
		window = w;
		lastLocation = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("save")) {
			readLastSaveLocation();


			String path = window.getFile("Save", lastLocation);

			if (path != null) {
				model.saveFile(path);


				updateLastSaveLocation(path);
			}
		}
	}

	private void readLastSaveLocation() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./.memory/saveLoc")));

			while (br.ready()) {
				String line = br.readLine();

				lastLocation = line;
			}

			br.close();
		} catch (Exception e) {
		}
	}

	private void updateLastSaveLocation(String selectedPath) {
		// Now we keep a record of the last saved File Path in backing store
		try {
			File lastLoadedConfig = new File("./.memory/saveLoc");
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
