package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BuildBoard implements IBoard{

	private JPanel gameBoard;
	
	public void initialize(){
	//start of right panel (the game panel)
	gameBoard = new JPanel();
	gameBoard.setPreferredSize(new Dimension(500, 500));
	gameBoard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
	gameBoard.setBackground(Color.WHITE);
	//end of the right panel
	}
	
	public JPanel getBoard(){
		return gameBoard;
	}
}
