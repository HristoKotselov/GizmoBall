package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.IMainEngine;

public class WindowListener implements ActionListener {
	private IMainEngine m;

	public WindowListener(IMainEngine m) {
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String actionCmd = e.getActionCommand();
		if (actionCmd.equals("start")) {
		
		
		} else if (actionCmd.equals("tick")) {
			
		} else if (actionCmd.equals("stop")) {
			
		} else if (actionCmd.equals("reload")) {
			
		} else if (actionCmd.equals("buildMode")) {
		
		} else if (actionCmd.equals("addBall")) {
			
		} else if (actionCmd.equals("setBallSpeed")) {
			
		}else if (actionCmd.equals("setFriction")) {
			
		}else if (actionCmd.equals("setGravity")) {
			
		}else if (actionCmd.equals("absorber")) {
			
		}else if (actionCmd.equals("connect")) {
			
		}else if (actionCmd.equals("disconnect")) {
			
		}else if (actionCmd.equals("keyPress")) {
			
		}else if (actionCmd.equals("undo")) {
			
		}else if (actionCmd.equals("rotate")) {
			
		}else if (actionCmd.equals("keyDisconnect")) {
			
		}else if (actionCmd.equals("delete")) {
			
		}else if (actionCmd.equals("play")) {
			
		}else if (actionCmd.equals("clear")) {
			
		}else if (actionCmd.equals("redo")) {
			
		}else if (actionCmd.equals("reloadBoard")) {
			
		}
	}

}
