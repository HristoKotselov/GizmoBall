package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.ActionTipDialogue;
import model.IMainEngine;
import view.IGameWindow;

public class BuildModeButtonListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;
	
	/* All the pop-up dialogue boxes */
	private final JOptionPane resetDialogue;

	public BuildModeButtonListener(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
		
		/* Set-up of the pop-up dialogue boxes */
		Object[] jOptionPane_Options = {"Yes", "No"};			// Default options for JOptionPane
		
		resetDialogue = new JOptionPane(
			    "Are you sure you want to clear the board?",
			    JOptionPane.QUESTION_MESSAGE,
			    JOptionPane.YES_NO_OPTION,
			    null,			// uses default Icon for Yes_No_Option
			    jOptionPane_Options,
			    jOptionPane_Options[1]);			// default selected option = "No"
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		
		switch(actionCmd){
			case "resetBoard":
				int response = JOptionPane.showOptionDialog(resetDialogue, resetDialogue.getMessage(), 
						"Clear Board", 		// Option Dialogue's Title
								resetDialogue.getOptionType(), resetDialogue.getMessageType(), resetDialogue.getIcon(), resetDialogue.getOptions(), resetDialogue.getInitialValue());
				
				if(response == JOptionPane.OK_OPTION){
					model.clearAllGizmos();
				}
					
				break;
				
			case "playMode":
				gameWindow.setMode("Play Mode");
				gameWindow.setActionTipsTextArea(ActionTipDialogue.playModeActionTip());
				break;
				
			case "undo":
				//TODO
				System.out.println("undo pressed");
				break;
				
			case "redo":
				//TODO
				System.out.println("redo pressed");
				break;
		}
		
	}

}
