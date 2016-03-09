package view;

import java.awt.Color;

import controller.AMainListener;
import controller.AddGizmoListener;
import controller.BuildModeMainListener;
import controller.ChangeBallConfigListener;
import controller.LoadFileListener;
import controller.PhysicsConfigListener;
import controller.SaveFileListener;
import controller.UndoRedoListener;
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

	/* GUI */
	private JPanel buttonMenu;
	ButtonGroup bg;
	/* Model */
	private IMainEngine model;

	/* Controller */
	private AMainListener buildModeAL;
	private ChangeBallConfigListener changeBallConfigAL;
	private PhysicsConfigListener physicsConfigAL;
	private UndoRedoListener undoRedoAL;

	public BuildMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		buildModeAL = new BuildModeMainListener(model);
		physicsConfigAL = new PhysicsConfigListener(model);
		undoRedoAL = new UndoRedoListener(model);

		buttonMenu = new JPanel();
		buttonMenu.setPreferredSize(new Dimension(170, 500));
		buttonMenu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
		buttonMenu.setLayout(new GridLayout(0, 1));

		JButton addBall = new JButton("Add Ball");
		addBall.setActionCommand("addBall");
		addBall.addActionListener(buildModeAL);

		JButton setBallSpeed = new JButton("Set Ball speed");
		setBallSpeed.setActionCommand("setBallSpeed");
		setBallSpeed.addActionListener(changeBallConfigAL);

		JButton setFriction = new JButton("Set Friction");
		setFriction.setActionCommand("setFriction");
		setFriction.addActionListener(physicsConfigAL);

		JButton setGravity = new JButton("Set Gravity");
		setGravity.setActionCommand("setGravity");
		setGravity.addActionListener(physicsConfigAL);

		JButton connect = new JButton("Connect gizmos");
		connect.setActionCommand("connect");
		connect.addActionListener(buildModeAL);

		JButton disconnect = new JButton("Disconnect gismos");
		disconnect.setActionCommand("disconnect");
		disconnect.addActionListener(buildModeAL);

		JButton keyConnect = new JButton("Connect key");
		keyConnect.setActionCommand("keyConnect");
		keyConnect.addActionListener(buildModeAL);

		JButton keyDisconnect = new JButton("Disconnect key");
		keyDisconnect.setActionCommand("keyDisconnect");
		keyDisconnect.addActionListener(buildModeAL);

		JButton delete = new JButton("Delete gizmo");
		delete.setActionCommand("delete");
		delete.addActionListener(buildModeAL);

		JButton rotate = new JButton("Rotate gizmo");
		rotate.setActionCommand("rotate");
		rotate.addActionListener(buildModeAL);

		JButton undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(undoRedoAL);

		JButton redo = new JButton("Redo");
		redo.setActionCommand("redo");
		redo.addActionListener(undoRedoAL);

		JButton reload = new JButton("Reload Board");
		reload.setActionCommand("reloadBoard");
		reload.addActionListener(buildModeAL);

		JButton clear = new JButton("Clear Board");
		clear.setActionCommand("clear");
		clear.addActionListener(buildModeAL);

		JButton playMode = new JButton("Play!");
		playMode.setActionCommand("playMode");
		playMode.addActionListener(buildModeAL);

		JLabel label = new JLabel("  Choose a gizmo to add:");
		label.setFont(new Font("Arial", 1, 13));
		buttonMenu.add(label);

		JRadioButton leftFlipper = new JRadioButton("left flipper");
		JRadioButton rightFlipper = new JRadioButton("right flipper");
		JRadioButton square = new JRadioButton("square");
		JRadioButton triangle = new JRadioButton("circle");
		JRadioButton circle = new JRadioButton("triangle");
		JRadioButton absorber = new JRadioButton("absorber");
	
		
		bg = new ButtonGroup();
		bg.add(leftFlipper);
		bg.add(rightFlipper);
		bg.add(square);
		bg.add(triangle);
		bg.add(circle);
		bg.add(absorber);
		square.setSelected(true);
		buttonMenu.add(leftFlipper);
		buttonMenu.add(rightFlipper);
		buttonMenu.add(square);
		buttonMenu.add(triangle);
		buttonMenu.add(circle);
		buttonMenu.add(absorber);
		JButton addGizmo = new JButton("Add");
		addGizmo.setActionCommand("addGizmo");
		addGizmo.addActionListener(buildModeAL);
		buttonMenu.add(addGizmo);

		//JTextArea textarea = new JTextArea(1,10);
		//textarea.setBackground(Color.LIGHT_GRAY);
		//textarea.setEditable(false);
		//buttonMenu.add(textarea);

		JLabel separator = new JLabel("  Additional functionality:");
		separator.setFont(new Font("Arial", 1, 13));
		buttonMenu.add(separator);

		buttonMenu.add(addBall);
		buttonMenu.add(setBallSpeed);
		buttonMenu.add(setFriction);
		buttonMenu.add(setGravity);
		buttonMenu.add(connect);
		buttonMenu.add(disconnect);
		buttonMenu.add(keyConnect);
		buttonMenu.add(keyDisconnect);
		buttonMenu.add(delete);
		buttonMenu.add(rotate);
		buttonMenu.add(undo);
		buttonMenu.add(redo);
		buttonMenu.add(reload);
		buttonMenu.add(clear);
		buttonMenu.add(playMode);

	}
public ButtonGroup getGizmoSelector(){
	return bg;
}
	
	public JPanel getMenu() {
		return buttonMenu;
	}
}
