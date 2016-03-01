package controller;

/**
 * This class will listen out for GUI events in regard to the actual Window itself (e.g. Minimise window)
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.IMainEngine;

public class WindowListener implements ActionListener {
	private IMainEngine model;

	public WindowListener(IMainEngine m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		
	}

}
