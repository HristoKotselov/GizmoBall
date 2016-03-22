package run;

import javax.swing.SwingUtilities;
import model.IMainEngine;
import model.MainEngine;
import view.GameWindow;

public class Main {
	public static void main(String[] args) {
		final MainEngine m = new MainEngine();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GameWindow(m);
			}
		});

	}
}