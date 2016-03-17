package model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * A simple class to hold a bunch of JLabels which can be used when the used
 * to display a certain "action tip" when the user clicks on a button
 * 
 * This keeps the MVC structure intact and keeps the action tips separated 
 * from the actual button press, meaning that the action tips could easily
 * be modified depending on what we used our model for.
 */

public class ActionTipDialogue {

	public static JLabel addGizmoActionTip() {

		JLabel jl = new JLabel("Click on a square to add a Gizmo");
		return jl;
	}

	public static JLabel setFrictionActionTip() {

		JLabel jl = new JLabel("Slide the friciton value to your desired level");
		return jl;
	}

	public static JLabel setGravityActionTip() {

		JLabel jl = new JLabel("Slide the gravity value to your desired level");
		return jl;
	}
	
	public static JLabel rotateGizmoActionTip() {

		JLabel jl = new JLabel("Click on the gizmo you wish to rotate");
		return jl;
	}
	
	public static JLabel deleteGizmoActionTip() {

		JLabel jl = new JLabel("Click on the gizmo you wish to delete");
		return jl;
	}

	public static JLabel loadFileActionTip() {

		JLabel jl = new JLabel("Select a file that you wish to load in to the editor");
		return jl;
	}
	
	public static JLabel saveFileActionTip() {

		JLabel jl = new JLabel("Choose a location where you wish to save the current board");
		return jl;
	}
	
	public static JLabel watchTickActionTip() {

		JLabel jl = new JLabel("Keep clicking 'tick' to watch the game step by step");
		return jl;
	}
	
	public static JLabel addAbsorberActionTip() {

		JLabel jl = new JLabel("Choose a location on the board to place the absorber");
		return jl;
	}
	
	public static JLabel connectGizmoActionTip() {

		JLabel jl = new JLabel("Click on two gizmos you wish to connect");
		return jl;
	}
	
	public static JLabel disconnectGizmoActionTip() {

		JLabel jl = new JLabel("Click on two gizmos you wish to disconnect from each other");
		return jl;
	}
	
	public static JLabel connectKeyToGizmoActionTip() {

		JLabel jl = new JLabel("Click on a gizmo you wish to assign a key to");
		return jl;
	}
	
	public static JLabel disconnectKeyFromGizmoActionTip() {

		JLabel jl = new JLabel("Click on a gizmo you wish to remove a key from");
		return jl;
	}
	
	
}
