package main;

import javax.swing.UIManager;
import model.Model;
import model.SquareBumper;
import model.TriangleBumper;
import model.CircleBumper;
import model.HorizontalLine;
import view.RunGui;

/**
 * part of the code is from Murray's Demonstration of MVC and MIT Physics Collisions 2014
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
		model.addLine(new HorizontalLine(100, 100, 200));
		model.addLine(new HorizontalLine(100, 200, 200));
		model.addLine(new HorizontalLine(100, 300, 200));
		model.addSquareBumper(new SquareBumper(20,50,20));
		model.addSquareBumper(new SquareBumper(50,50,20));
		model.addSquareBumper(new SquareBumper(80,50,20));
		model.addSquareBumper(new SquareBumper(250,50,20));
		model.addSquareBumper(new SquareBumper(290,50,20));
		model.addSquareBumper(new SquareBumper(330,50,20));
		//model.addSquareBumper(new SquareBumper(20,150,20));
		//model.addSquareBumper(new SquareBumper(50,150,20));
		//model.addSquareBumper(new SquareBumper(80,150,20));
		model.addTriangleBumper(new TriangleBumper(140,40,20));
		model.addTriangleBumper(new TriangleBumper(180,40,20));
		model.addTriangleBumper(new TriangleBumper(220,40,20));
		model.addCircleBumper(new CircleBumper(150,10,10));
		model.addCircleBumper(new CircleBumper(230,10,10));
		model.addCircleBumper(new CircleBumper(190,10,10));


		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}
}
