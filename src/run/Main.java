package run;

import model.IMainEngine;
import model.MainEngine;
import view.GameWindow;

public class Main {
	public static void main(String[] args) {
		MainEngine m = new MainEngine();
		new GameWindow(m, m);
	}
}