package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

import model.IMainEngine;

public class PlayModeMainListener implements IMainListener {
	private IMainEngine model;
	private MouseInputListener mouseHandler;
	private KeyListener keyHandler;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void setKeyBoardListener(KeyListener kl) {
		keyHandler = kl;
	}

	public void setMouseListener(MouseInputListener ml) {
		mouseHandler = ml;
	}
}
