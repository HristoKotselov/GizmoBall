package PhysicsDemo.src.main;

import javax.swing.UIManager;
import PhysicsDemo.src.model.Model;
import PhysicsDemo.src.model.VerticalLine;
import PhysicsDemo.src.view.RunGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {

	public static void main(String[] args) {		
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		Model model = new Model();

//		model.setBallSpeed(200, 200);

		// Vertical line at (100,100), width 300
		model.addLine(new VerticalLine(100, 100, 200));
		model.addLine(new VerticalLine(100, 200, 200));
		model.addLine(new VerticalLine(100, 300, 200));

		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}
}
