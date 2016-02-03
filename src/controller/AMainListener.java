package controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

import model.IMainEngine;

abstract public class AMainListener implements ActionListener {
	private IMainEngine model;
	private MouseInputListener mouseHandler;
	private KeyListener keyHandler;

	public void setKeyBoardListener(KeyListener kl) {
		keyHandler = kl;
	}

	public void setMouseListener(MouseInputListener ml) {
		mouseHandler = ml;
	}

}
