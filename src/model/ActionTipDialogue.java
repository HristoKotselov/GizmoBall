package model;

/*
 * A simple class to hold a bunch of Messages which can be used when the used
 * to display a certain "action tip" when the user clicks on a button
 * 
 * This keeps the MVC structure intact and keeps the action tips separated 
 * from the actual button press, meaning that the action tips could easily
 * be modified depending on what we used our model for.
 */

public class ActionTipDialogue {

	public static String addGizmoActionTip() {

		return "Click on a square to add a Gizmo";
	}

	public static String setFrictionActionTip() {

		return "Slide the friciton value to your desired level";
	}

	public static String setGravityActionTip() {

		return "Slide the gravity value to your desired level";
	}
	
	public static String rotateGizmoActionTip() {

		return "Click on the gizmo you wish to rotate";
	}
	
	public static String deleteGizmoActionTip() {

		return "Click on the gizmo you wish to delete";
	}

	public static String loadFileActionTip() {

		return "Select a file that you wish to load in to the editor";
	}
	
	public static String saveFileActionTip() {

		return "Choose a location where you wish to save the current board";
	}
	
	public static String watchTickActionTip() {

		return "Keep clicking 'tick' to watch the game step by step";
	}
	
	public static String addAbsorberActionTip() {

		return "Choose a location on the board to place the absorber";
	}
	
	public static String connectGizmoActionTip() {

		return "Click on two gizmos you wish to connect";
	}
	
	public static String disconnectGizmoActionTip() {

		return "Click on two gizmos you wish to disconnect from each other";
	}
	
	public static String connectKeyToGizmoActionTip() {

		return "Click on a gizmo you wish to assign a key to";
	}
	
	public static String disconnectKeyFromGizmoActionTip() {

		return "Click on a gizmo you wish to remove a key from";
	}
	
	public static String moveGizmoActionTip() {

		return "Click on a gizmo you wish to move";
	}
	
	public static String addBallActionTip() {

		return "Click where you would like to add a ball";
	}
	
	public static String physicsActionTip() {

		return "Select the physics values of your choice";
	}
	
	public static String playModeActionTip() {

		return "Have fun!";
	}
	
	
}
