package Absorber;

import javax.swing.UIManager;

public class AbsorberPrototype {
	
	public static void main(String[] args) {

		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		Model model = new Model();

		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}

}
