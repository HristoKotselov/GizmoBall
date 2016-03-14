package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import model.IMainEngine;
import view.IBoard;
import view.IGameWindow;
import view.IMenu;

abstract public class AMainListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;
	private IBoard board;
	private IMenu menu;

	public AMainListener(IMainEngine mod, IBoard b, IMenu men) {
		model = mod;
		board = b;
		menu = men;
	}

	protected void setBoardKeyListener(KeyListener kl) {
		JPanel jpanel = board.getBoard();
		setKeyListener(jpanel, kl);
	}

	protected void setBoardMouseListener(MouseInputListener ml) {
		JPanel jpanel = board.getBoard();
		setMouseListener(jpanel, ml);
	}

	private void setKeyListener(JComponent component, KeyListener kl) {
		KeyListener[] keyListeners = component.getKeyListeners();
		for (KeyListener curKL : keyListeners) {
			component.removeKeyListener(curKL);
		}

		component.addKeyListener(kl);
	}

	private void setMouseListener(JComponent component, MouseInputListener ml) {
		MouseListener[] mouseListeners = component.getMouseListeners();
		for (MouseListener curML : mouseListeners) {
			component.removeMouseListener(curML);
		}

		component.addMouseListener(ml);
	}
	
	/** TODO Temporarily Line, REMOVE\CHANGE before final release **/
	protected IMainEngine getModel(){
		return model;
	}
}
