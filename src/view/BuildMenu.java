package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import controller.AMainListener;
import controller.BuildModeFunctionChangeListener;
import controller.ChangeBallConfigListener;
import controller.PhysicsConfigListener;
import controller.UndoRedoListener;
import model.IMainEngine;

//TODO Make model/controller use interface rather than implementation
public class BuildMenu implements IMenu {

	/* GUI */
	private JPanel buttonMenu;
	private ButtonGroup bg;
	private JComboBox<String> functionCB;
	private JPanel cards;

	/* Model */
	private IMainEngine model;

	/* Controller */
	private AMainListener buildModeAL;
	private ChangeBallConfigListener changeBallConfigAL;
	private PhysicsConfigListener physicsConfigAL;
	private UndoRedoListener undoRedoAL;

	public BuildMenu(IMainEngine model) {
		this.model = model;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO the listeners should have been passed through parameter
		// buildModeAL = new BuildModeMainListener(model);
		physicsConfigAL = new PhysicsConfigListener(model);
		undoRedoAL = new UndoRedoListener(model);

		buttonMenu = new JPanel();
		buttonMenu.setPreferredSize(new Dimension(300, 400));
		buttonMenu.setLayout(new BorderLayout());

		/* <Scott's New Version> */

		String[] functions = { "Add Gizmo", "Remove Gizmo", "Rotate Gizmo", "Move Gizmo", "Connect Gizmos", "Bind Key", "Set Physics Constants" };
		functionCB = new JComboBox<String>(functions);
		functionCB.addItemListener(new BuildModeFunctionChangeListener(this));
		buttonMenu.add(functionCB, BorderLayout.NORTH);


		// Add Gizmo panel

		JPanel addGizmo = new JPanel(new GridLayout(0, 2));
		addGizmo.setPreferredSize(new Dimension(170, 20));

		JLabel label = new JLabel(" Choose a gizmo to add:");
		label.setFont(new Font("Arial", 1, 13));
		buttonMenu.add(label);

		bg = new ButtonGroup();

		JRadioButton leftFlipper = new JRadioButton("Left Flipper");
		leftFlipper.setActionCommand("Left Flipper");
		bg.add(leftFlipper);

		JRadioButton rightFlipper = new JRadioButton("Right Flipper");
		rightFlipper.setActionCommand("Right Flipper");
		bg.add(rightFlipper);

		JRadioButton square = new JRadioButton("Square Bumper");
		square.setActionCommand("Square");
		bg.add(square);

		JRadioButton triangle = new JRadioButton("Triangle Bumper");
		triangle.setActionCommand("Triangle");
		bg.add(triangle);

		JRadioButton circle = new JRadioButton("Circle Bumper");
		circle.setActionCommand("Circle");
		bg.add(circle);

		JRadioButton absorber = new JRadioButton("Absorber");
		absorber.setActionCommand("Absorber");
		bg.add(absorber);

		JRadioButton ball = new JRadioButton("Ball");
		ball.setActionCommand("Ball");
		bg.add(ball);

		square.setSelected(true);
		addGizmo.add(leftFlipper);
		addGizmo.add(rightFlipper);
		addGizmo.add(square);
		addGizmo.add(triangle);
		addGizmo.add(circle);
		addGizmo.add(absorber);
		addGizmo.add(ball);


		// Physics Constants panel
		JPanel physicsConstants = new JPanel(new GridLayout(4, 2));
		physicsConstants.setPreferredSize(new Dimension(280, 250));

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

		physicsConstants.add(ballspeedlabel);
		physicsConstants.add(ballspeed);
		physicsConstants.add(mu1label);
		physicsConstants.add(mu1);
		physicsConstants.add(mu2label);
		physicsConstants.add(mu2);
		physicsConstants.add(gravitylabel);
		physicsConstants.add(gravity);


		// Card panel
		cards = new JPanel(new CardLayout());
		cards.add(addGizmo, functions[0]);
		cards.add(new JPanel(), functions[1]);
		cards.add(new JPanel(), functions[2]);
		cards.add(new JPanel(), functions[3]);
		cards.add(new JPanel(), functions[4]);
		cards.add(new JPanel(), functions[5]);
		cards.add(physicsConstants, functions[6]);

		buttonMenu.add(cards, BorderLayout.CENTER);


		// Button panel
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(260, 70));
		p.setLayout(new GridLayout(2, 2));

		JButton undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(undoRedoAL);
		p.add(undo);

		JButton redo = new JButton("Redo");
		redo.setActionCommand("redo");
		redo.addActionListener(undoRedoAL);
		p.add(redo);

		JButton reload = new JButton("Reset Board");
		reload.setActionCommand("reloadBoard");
		reload.addActionListener(buildModeAL);
		p.add(reload);

		JButton playMode = new JButton("Play!");
		playMode.setActionCommand("playMode");
		playMode.addActionListener(buildModeAL);
		p.add(playMode);

		buttonMenu.add(p, BorderLayout.SOUTH);





		/* </Scott's New Version> */




//		JButton setBallSpeed = new JButton("Set Ball speed");
//		setBallSpeed.setActionCommand("setBallSpeed");
//		setBallSpeed.addActionListener(changeBallConfigAL);
//
//		JButton setFriction = new JButton("Set Friction");
//		setFriction.setActionCommand("setFriction");
//		setFriction.addActionListener(physicsConfigAL);
//
//		JButton setGravity = new JButton("Set Gravity");
//		setGravity.setActionCommand("setGravity");
//		setGravity.addActionListener(physicsConfigAL);
//
//		JButton connect = new JButton("Connect gizmos");
//		connect.setActionCommand("connect");
//		connect.addActionListener(buildModeAL);
//
//		JButton disconnect = new JButton("Disconnect gizmos");
//		disconnect.setActionCommand("disconnect");
//		disconnect.addActionListener(buildModeAL);
//
//		JButton keyConnect = new JButton("Connect key");
//		keyConnect.setActionCommand("keyConnect");
//		keyConnect.addActionListener(buildModeAL);
//
//		JButton keyDisconnect = new JButton("Disconnect key");
//		keyDisconnect.setActionCommand("keyDisconnect");
//		keyDisconnect.addActionListener(buildModeAL);
//
//		JButton delete = new JButton("Delete gizmo");
//		delete.setActionCommand("delete");
//		delete.addActionListener(buildModeAL);
//
//		JButton rotate = new JButton("Rotate gizmo");
//		rotate.setActionCommand("rotate");
//		rotate.addActionListener(buildModeAL);
//
//		JButton undo = new JButton("Undo");
//		undo.setActionCommand("undo");
//		undo.addActionListener(undoRedoAL);
//
//		JButton redo = new JButton("Redo");
//		redo.setActionCommand("redo");
//		redo.addActionListener(undoRedoAL);
//
//		JButton reload = new JButton("Reset Board");
//		reload.setActionCommand("reloadBoard");
//		reload.addActionListener(buildModeAL);
//
//		JButton clear = new JButton("Clear Board");
//		clear.setActionCommand("clear");
//		clear.addActionListener(buildModeAL);
//
//		JButton move = new JButton("Move Gizmo");
//		move.setActionCommand("moveGizmo");
//		move.addActionListener(buildModeAL);
//
//		JButton playMode = new JButton("Play!");
//		playMode.setActionCommand("playMode");
//		playMode.addActionListener(buildModeAL);

		// JPanel addGizmo = new JPanel();
		// addGizmo.setPreferredSize(new Dimension(170, 20));
		// addGizmo.setLayout(new GridLayout(0, 2));
		//
		// JLabel label = new JLabel(" Choose a gizmo to add:");
		// label.setFont(new Font("Arial", 1, 13));
		// buttonMenu.add(label);
		//
		// JRadioButton leftFlipper = new JRadioButton("left flipper");
		// JRadioButton rightFlipper = new JRadioButton("right flipper");
		// JRadioButton square = new JRadioButton("square");
		// JRadioButton triangle = new JRadioButton("circle");
		// JRadioButton circle = new JRadioButton("triangle");
		// JRadioButton absorber = new JRadioButton("absorber");
		// JRadioButton ball = new JRadioButton("ball");
		//
		// bg = new ButtonGroup();
		// bg.add(leftFlipper);
		// bg.add(rightFlipper);
		// bg.add(square);
		// bg.add(triangle);
		// bg.add(circle);
		// bg.add(absorber);
		// bg.add(ball);
		// square.setSelected(true);
		// addGizmo.add(leftFlipper);
		// addGizmo.add(rightFlipper);
		// addGizmo.add(square);
		// addGizmo.add(triangle);
		// addGizmo.add(circle);
		// addGizmo.add(absorber);
		// addGizmo.add(ball);
		//
		// buttonMenu.add(addGizmo);

		// JTextArea textarea = new JTextArea(1,10);
		// textarea.setBackground(Color.LIGHT_GRAY);
		// textarea.setEditable(false);
		// buttonMenu.add(textarea);

//		JPanel sliders = new JPanel();
//		sliders.setPreferredSize(new Dimension(270, 250));
//		sliders.setLayout(new GridLayout(0, 2));
//
//		JLabel ballspeedlabel = new JLabel("  Ball speed:");
//		ballspeedlabel.setFont(new Font("Arial", 1, 15));
//		JSlider ballspeed = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
//		ballspeed.setMinorTickSpacing(5);
//		ballspeed.setMajorTickSpacing(10);
//		ballspeed.setPaintTicks(true);
//		ballspeed.setPaintLabels(true);
//		ballspeed.setLabelTable(ballspeed.createStandardLabels(10));
//
//		JLabel mu1label = new JLabel("  Friciton mu1:");
//		mu1label.setFont(new Font("Arial", 1, 15));
//		JSlider mu1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
//		mu1.setMinorTickSpacing(1);
//		mu1.setMajorTickSpacing(5);
//		mu1.setPaintTicks(true);
//		mu1.setPaintLabels(true);
//		mu1.setLabelTable(ballspeed.createStandardLabels(5));
//
//		JLabel mu2label = new JLabel("  Friciton mu2:");
//		mu2label.setFont(new Font("Arial", 1, 15));
//		JSlider mu2 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
//		mu2.setMinorTickSpacing(1);
//		mu2.setMajorTickSpacing(5);
//		mu2.setPaintTicks(true);
//		mu2.setPaintLabels(true);
//		mu2.setLabelTable(ballspeed.createStandardLabels(5));
//
//		JLabel gravitylabel = new JLabel("  Gravity:");
//		gravitylabel.setFont(new Font("Arial", 1, 15));
//		JSlider gravity = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
//		gravity.setMinorTickSpacing(5);
//		gravity.setMajorTickSpacing(10);
//		gravity.setPaintTicks(true);
//		gravity.setPaintLabels(true);
//		gravity.setLabelTable(ballspeed.createStandardLabels(10));
//
//		JLabel addgizmolabel = new JLabel("  Add Gizmo:");
//		addgizmolabel.setFont(new Font("Arial", 1, 15));
//		String[] gizmos = { "Left Flipper", "Right Flipper", "Square", "Triangle", "Circle", "Absorber", "Ball", "Delete", "Rotate" };
//		addgizmo = new JComboBox<String>(gizmos);
//		addgizmo.setSelectedIndex(6);
//
//		sliders.add(ballspeedlabel);
//		sliders.add(ballspeed);
//		sliders.add(mu1label);
//		sliders.add(mu1);
//		sliders.add(mu2label);
//		sliders.add(mu2);
//		sliders.add(gravitylabel);
//		sliders.add(gravity);
//		sliders.add(addgizmolabel);
//		sliders.add(addgizmo);
//		buttonMenu.add(sliders);
//
//		JPanel buttons = new JPanel();
//		buttons.setPreferredSize(new Dimension(170, 400));
//		buttons.setLayout(new GridLayout(0, 2));
//
//		buttons.add(setBallSpeed);
//		buttons.add(setFriction);
//		buttons.add(setGravity);
//		buttons.add(connect);
//		buttons.add(disconnect);
//		buttons.add(keyConnect);
//		buttons.add(keyDisconnect);
//		buttons.add(delete);
//		buttons.add(rotate);
//		buttons.add(undo);
//		buttons.add(redo);
//		buttons.add(reload);
//		buttons.add(clear);
//		buttons.add(move);
//		buttons.add(playMode);
//
//		buttonMenu.add(buttons);
	}

	public ButtonGroup getGizmoSelector() {
		return bg;
	}

	public String getSelectedGizmo() {
		return bg.getSelection().getActionCommand();
	}

	public String getSelectedFunction() {
		return functionCB.getSelectedItem().toString();
	}

	public void setCard() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, getSelectedFunction());
	}

	public JPanel getMenu() {
		return buttonMenu;
	}
}
