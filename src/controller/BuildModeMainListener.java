package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import model.IMainEngine;

public class BuildModeMainListener extends AMainListener {
	private IMainEngine model;

	public BuildModeMainListener(IMainEngine m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// check out AMainListener's method!

		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("addGizmo")) {

		} else if (actionCmd.equals("addBall")) {

		} else if (actionCmd.equals("connect")) {

		} else if (actionCmd.equals("disconnect")) {

		} else if (actionCmd.equals("keyConnect")) {

		} else if (actionCmd.equals("keyDisconnect")) {

		} else if (actionCmd.equals("delete")) {

		} else if (actionCmd.equals("rotate")) {

		} else if (actionCmd.equals("undo")) {

		} else if (actionCmd.equals("redo")) {

		} else if (actionCmd.equals("reloadBoard")) {

		} else if (actionCmd.equals("clear")) {

		} else if (actionCmd.equals("playMode")) {

		}
	}

}
