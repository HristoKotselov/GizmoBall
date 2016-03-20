package model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * A simple class to hold a bunch of JTextAreas which can be used when the used
 * to display a certain "action tip" when the user clicks on a button
 * 
 * This keeps the MVC structure intact and keeps the action tips separated 
 * from the actual button press, meaning that the action tips could easily
 * be modified depending on what we used our model for.
 */

public class ActionTipDialogue {

	public static JTextArea addGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on a square to add a Gizmo");
		return jl;
	}

	public static JTextArea setFrictionActionTip() {

		JTextArea jl = new JTextArea("Slide the friciton value to your desired level");
		return jl;
	}

	public static JTextArea setGravityActionTip() {

		JTextArea jl = new JTextArea("Slide the gravity value to your desired level");
		return jl;
	}
	
	public static JTextArea rotateGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on the gizmo you wish to rotate");
		return jl;
	}
	
	public static JTextArea deleteGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on the gizmo you wish to delete");
		return jl;
	}

	public static JTextArea loadFileActionTip() {

		JTextArea jl = new JTextArea("Select a file that you wish to load in to the editor");
		return jl;
	}
	
	public static JTextArea saveFileActionTip() {

		JTextArea jl = new JTextArea("Choose a location where you wish to save the current board");
		return jl;
	}
	
	public static JTextArea watchTickActionTip() {

		JTextArea jl = new JTextArea("Keep clicking 'tick' to watch the game step by step");
		return jl;
	}
	
	public static JTextArea addAbsorberActionTip() {

		JTextArea jl = new JTextArea("Choose a location on the board to place the absorber");
		return jl;
	}
	
	public static JTextArea connectGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on two gizmos you wish to connect");
		return jl;
	}
	
	public static JTextArea disconnectGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on two gizmos you wish to disconnect from each other");
		return jl;
	}
	
	public static JTextArea connectKeyToGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on a gizmo you wish to assign a key to");
		return jl;
	}
	
	public static JTextArea disconnectKeyFromGizmoActionTip() {

		JTextArea jl = new JTextArea("Click on a gizmo you wish to remove a key from");
		return jl;
	}
	
	
}
