package Absorber;

import java.awt.Color;

import javax.swing.UIManager;

import FileLoader.src.Const;
import PhysicsDemo.src.model.VerticalLine;

public class AbsorberPrototype {
	
	public static void main(String[] args) {

		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		Model model = new Model();
		
		model.addAbsorber(new Absorber("abs1", 0, 19, 20, 1, Color.MAGENTA));

		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}

}
