package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.IMainEngine;

public class PlayModeKeyListener implements KeyListener {
	private IMainEngine model;

	public PlayModeKeyListener(IMainEngine m) {
		model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		model.trigger(e.getKeyCode(), KeyEvent.KEY_PRESSED);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.trigger(e.getKeyCode(), KeyEvent.KEY_RELEASED);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}