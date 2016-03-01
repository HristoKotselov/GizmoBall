package view;

import java.awt.Color;
import controller.WindowListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import model.IMainEngine;

public class BuildMenu implements IMenu {

	private JPanel buttonMenu;

	private IMainEngine model;
	private WindowListener wdolis;

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		wdolis = new WindowListener(model);
		buttonMenu = new JPanel();
		buttonMenu.setPreferredSize(new Dimension(170, 500));
		buttonMenu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
		buttonMenu.setLayout(new GridLayout(0, 1));

		//JButton leftFlipper = new JButton("Add left flipper");
		//JButton rightFlipper = new JButton("Add right flipper");
		//JButton square = new JButton("Add a square");
		//JButton triangle = new JButton("Add a trangle");
		//JButton circle = new JButton("Add a circle");
		JButton addBall = new JButton("Add Ball");
		addBall.setActionCommand("addBall");
		addBall.addActionListener(wdolis);
		
		JButton setBallSpeed = new JButton("Set Ball speed");
		setBallSpeed.setActionCommand("setBallSpeed");
		setBallSpeed.addActionListener(wdolis);
		
		JButton setFriction = new JButton("Set Friction");
		setFriction.setActionCommand("setFriction");
		setFriction.addActionListener(wdolis);
		
		JButton setGravity = new JButton("Set Gravity");
		setGravity.setActionCommand("setGravity");
		setGravity.addActionListener(wdolis);
		
		JButton absorber = new JButton("Add absorber");
		absorber.setActionCommand("absorber");
		absorber.addActionListener(wdolis);
		
		JButton connect = new JButton("Connect gizmos");
		connect.setActionCommand("connect");
		connect.addActionListener(wdolis);
		
		JButton disconnect = new JButton("Disconnect gismos");
		disconnect.setActionCommand("disconnect");
		disconnect.addActionListener(wdolis);
		
		JButton keyPress = new JButton("Connect key");
		keyPress.setActionCommand("keyPress");
		keyPress.addActionListener(wdolis);
		
		JButton keyDisconnect = new JButton("Disconnect key");
		keyDisconnect.setActionCommand("keyDisconnect");
		keyDisconnect.addActionListener(wdolis);
		
		JButton delete = new JButton("Delete gizmo");
		delete.setActionCommand("delete");
		delete.addActionListener(wdolis);
		
		JButton rotate = new JButton("Rotate gizmo");
		rotate.setActionCommand("rotate");
		rotate.addActionListener(wdolis);
		
		JButton undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(wdolis);
		
		JButton redo = new JButton("Redo");
		redo.setActionCommand("redo");
		redo.addActionListener(wdolis);
		
		JButton reload = new JButton("Reload Board");
		reload.setActionCommand("reloadBoard");
		reload.addActionListener(wdolis);
		
		JButton clear = new JButton("Clear Board");
		clear.setActionCommand("clear");
		clear.addActionListener(wdolis);
		
		JButton play = new JButton("Play");
		play.setActionCommand("play");
		play.addActionListener(wdolis);

		JLabel label = new JLabel("  Choose a gizmo to add:");
		label.setFont(new Font("Arial", 1, 13));
		buttonMenu.add(label);

		JRadioButton leftFlipper = new JRadioButton("left flipper");
		JRadioButton rightFlipper = new JRadioButton("right flipper");
		JRadioButton square = new JRadioButton("square");
		JRadioButton triangle = new JRadioButton("circle");
		JRadioButton circle = new JRadioButton("triangle");
		ButtonGroup bg = new ButtonGroup();
		bg.add(leftFlipper);
		bg.add(rightFlipper);
		bg.add(square);
		bg.add(triangle);
		bg.add(circle);
		buttonMenu.add(leftFlipper);
		buttonMenu.add(rightFlipper);
		buttonMenu.add(square);
		buttonMenu.add(triangle);
		buttonMenu.add(circle);
		JButton add = new JButton("Add");
		buttonMenu.add(add);

		//JTextArea textarea = new JTextArea(1,10);
		//textarea.setBackground(Color.LIGHT_GRAY);
		//textarea.setEditable(false);
		//buttonMenu.add(textarea);

		JLabel separator = new JLabel("  Additional functionality:");
		separator.setFont(new Font("Arial", 1, 13));
		buttonMenu.add(separator);

		//buttonMenu.add(leftFlipper);
		//buttonMenu.add(rightFlipper);
		//buttonMenu.add(square);
		//buttonMenu.add(triangle);
		//buttonMenu.add(circle);
		buttonMenu.add(addBall);
		buttonMenu.add(setBallSpeed);
		buttonMenu.add(setFriction);
		buttonMenu.add(setGravity);
		buttonMenu.add(absorber);
		buttonMenu.add(connect);
		buttonMenu.add(disconnect);
		buttonMenu.add(keyPress);
		buttonMenu.add(keyDisconnect);
		buttonMenu.add(delete);
		buttonMenu.add(rotate);
		buttonMenu.add(undo);
		buttonMenu.add(redo);
		buttonMenu.add(reload);
		buttonMenu.add(clear);
		buttonMenu.add(play);

	}

	public JPanel getMenu() {
		return buttonMenu;
	}
}
