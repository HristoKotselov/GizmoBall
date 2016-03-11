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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
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
		// TODO the listeners should have been passed through parameter
		//buildModeAL = new BuildModeMainListener(model);
		physicsConfigAL = new PhysicsConfigListener(model);
		undoRedoAL = new UndoRedoListener(model);

		buttonMenu = new JPanel();
		buttonMenu.setPreferredSize(new Dimension(300, 400));
		buttonMenu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
		buttonMenu.setLayout(new GridLayout(0, 1));

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

		JButton disconnect = new JButton("Disconnect gizmos");
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
		
		JButton move = new JButton("Move Gizmo");
		move.setActionCommand("moveGizmo");
		move.addActionListener(buildModeAL);

		JButton playMode = new JButton("Play!");
		playMode.setActionCommand("playMode");
		playMode.addActionListener(buildModeAL);
		
//		JPanel addGizmo = new JPanel();
//		addGizmo.setPreferredSize(new Dimension(170, 20));
//		addGizmo.setLayout(new GridLayout(0, 2));
//		
//		JLabel label = new JLabel("  Choose a gizmo to add:");
//		label.setFont(new Font("Arial", 1, 13));
//		buttonMenu.add(label);
//
//		JRadioButton leftFlipper = new JRadioButton("left flipper");
//		JRadioButton rightFlipper = new JRadioButton("right flipper");
//		JRadioButton square = new JRadioButton("square");
//		JRadioButton triangle = new JRadioButton("circle");
//		JRadioButton circle = new JRadioButton("triangle");
//		JRadioButton absorber = new JRadioButton("absorber");
//		JRadioButton ball = new JRadioButton("ball");
//	
//		
//		bg = new ButtonGroup();
//		bg.add(leftFlipper);
//		bg.add(rightFlipper);
//		bg.add(square);
//		bg.add(triangle);
//		bg.add(circle);
//		bg.add(absorber);
//		bg.add(ball);
//		square.setSelected(true);
//		addGizmo.add(leftFlipper);
//		addGizmo.add(rightFlipper);
//		addGizmo.add(square);
//		addGizmo.add(triangle);
//		addGizmo.add(circle);
//		addGizmo.add(absorber);
//		addGizmo.add(ball);
//		
//		buttonMenu.add(addGizmo);

		//JTextArea textarea = new JTextArea(1,10);
		//textarea.setBackground(Color.LIGHT_GRAY);
		//textarea.setEditable(false);
		//buttonMenu.add(textarea);
		
		JPanel sliders = new JPanel();
		sliders.setPreferredSize(new Dimension(270, 400));
		sliders.setLayout(new GridLayout(0, 2));
		
		JLabel ballspeedlabel = new JLabel("  Ball speed:");
		ballspeedlabel.setFont(new Font("Arial", 1, 15));
		JSlider ballspeed = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		ballspeed.setMinorTickSpacing(5);
		ballspeed.setMajorTickSpacing(10);
		ballspeed.setPaintTicks(true);
		ballspeed.setPaintLabels(true);
		ballspeed.setLabelTable(ballspeed.createStandardLabels(10));

		
		JLabel mu1label = new JLabel("  Friciton mu1:");
		mu1label.setFont(new Font("Arial", 1, 15));
		JSlider mu1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		mu1.setMinorTickSpacing(1);
		mu1.setMajorTickSpacing(5);
		mu1.setPaintTicks(true);
		mu1.setPaintLabels(true);
		mu1.setLabelTable(ballspeed.createStandardLabels(5));
		
		JLabel mu2label = new JLabel("  Friciton mu2:");
		mu2label.setFont(new Font("Arial", 1, 15));
		JSlider mu2 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		mu2.setMinorTickSpacing(1);
		mu2.setMajorTickSpacing(5);
		mu2.setPaintTicks(true);
		mu2.setPaintLabels(true);
		mu2.setLabelTable(ballspeed.createStandardLabels(5));
		
		JLabel gravitylabel = new JLabel("  Gravity:");
		gravitylabel.setFont(new Font("Arial", 1, 15));
		JSlider gravity = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		gravity.setMinorTickSpacing(5);
		gravity.setMajorTickSpacing(10);
		gravity.setPaintTicks(true);
		gravity.setPaintLabels(true);
		gravity.setLabelTable(ballspeed.createStandardLabels(10));
		
		JLabel addgizmolabel = new JLabel("  Add Gizmo:");
		addgizmolabel.setFont(new Font("Arial", 1, 15));
		String[] gizmos = { "Left Flipper", "Right Flipper", "Square", "Triangle", "Circle", "Absorber", "Ball" };
		JComboBox<String> addgizmo = new JComboBox<String>(gizmos);
		addgizmo.setSelectedIndex(6);

		sliders.add(ballspeedlabel);
		sliders.add(ballspeed);
		sliders.add(mu1label);
		sliders.add(mu1);
		sliders.add(mu2label);
		sliders.add(mu2);
		sliders.add(gravitylabel);
		sliders.add(gravity);
		sliders.add(addgizmolabel);
		sliders.add(addgizmo);
		buttonMenu.add(sliders);


		
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(170, 400));
		buttons.setLayout(new GridLayout(0, 2));

		//buttons.add(setBallSpeed);
		//buttons.add(setFriction);
		//buttons.add(setGravity);
		buttons.add(connect);
		buttons.add(disconnect);
		buttons.add(keyConnect);
		buttons.add(keyDisconnect);
		buttons.add(delete);
		buttons.add(rotate);
		buttons.add(undo);
		buttons.add(redo);
		buttons.add(reload);
		buttons.add(clear);
		buttons.add(move);
		buttons.add(playMode);
	
		buttonMenu.add(buttons);
	}
public ButtonGroup getGizmoSelector(){
	return bg;
}
	
	public JPanel getMenu() {
		return buttonMenu;
	}
}
