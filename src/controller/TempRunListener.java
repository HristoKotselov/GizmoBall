package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import model.IMainEngine;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class TempRunListener implements ActionListener {

	private Timer timer;
	private IMainEngine model;

	public TempRunListener(IMainEngine m) {
		model = m;
		timer = new Timer(16, this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBalls();
		} else
			switch (e.getActionCommand()) {
			case "playMode":
				timer.start();
				break;
			case "Stop":
				model.stop();
				timer.stop();
				break;
			case "Tick":
				model.start();
				//model.moveBall();
				model.stop();
				break;
			case "Quit":
				System.exit(0);
				break;
			}
	}
}
