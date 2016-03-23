package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.AGizmoComponent;
import model.IMainEngine;
import view.KeyBindPopup;

public class KeyBindPopupListener implements KeyListener {
	private IMainEngine m;
	private AGizmoComponent g;
	private int type;
	private KeyBindPopup p;

	public KeyBindPopupListener(IMainEngine m, AGizmoComponent g, int type) {
		this.m = m;
		this.g = g;
		this.type = type;

		p = new KeyBindPopup(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		m.bindKey(g, e.getKeyCode(), type);
		p.dispose();
	}
}