package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

import model.IMainEngine;

public class PlayModeMainListener extends AMainListener {

	private IMainEngine model;

	public PlayModeMainListener(IMainEngine m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// check out AMainListener's method!

		String actionCmd = e.getActionCommand();
	
		if (actionCmd.equals("start")) {
System.out.println("start clicked");
		} else if (actionCmd.equals("tick")) {

		} else if (actionCmd.equals("stop")) {

		} else if (actionCmd.equals("reload")) {

		} else if (actionCmd.equals("buildMode")) {

		}
	}

}
