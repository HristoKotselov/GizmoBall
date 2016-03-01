package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.IMainEngine;

public class BuildBoard implements IBoard, Observer {

	private JPanel gameBoard;

	private IMainEngine model;

	public BuildBoard() {
		initialize();
	}

	private void initialize() {
		//start of right panel (the game panel)
		gameBoard = new JPanel();
		gameBoard.setPreferredSize(new Dimension(500, 500));
		gameBoard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
		gameBoard.setBackground(Color.WHITE);
		//end of the right panel
	}

	public void paintComponent(Graphics g) {
		// TODO
	}

	public JPanel getBoard() {
		return gameBoard;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
