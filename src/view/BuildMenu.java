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
import controller.BuildModeButtonListener;
import controller.BuildModeFunctionChangeListener;
import controller.ChangeBallConfigListener;
import controller.PhysicsConfigListener;
import controller.UndoRedoListener;
import model.IMainEngine;

//TODO Make model/controller use interface rather than implementation
public class BuildMenu implements IMenu {

	/* GUI */
	private JPanel menuPanel;
	private ButtonGroup bg;
	private JComboBox<String> functionCB;
	private JPanel cards;

	/** Required as a reference for Controllers **/
	private IGameWindow gameWindow;

	/* Model */
	private IMainEngine model;

	/* Controller */
	private BuildModeButtonListener buildModeAL;
	private ChangeBallConfigListener changeBallConfigAL;
	private PhysicsConfigListener physicsConfigAL;
	private UndoRedoListener undoRedoAL;

	public BuildMenu(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO the listeners should have been passed through parameter
		// buildModeAL = new BuildModeMainListener(model);
		physicsConfigAL = new PhysicsConfigListener(model);
		buildModeAL = new BuildModeButtonListener(model, gameWindow);
		undoRedoAL = new UndoRedoListener(model);

		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(300, 400));
		menuPanel.setLayout(new BorderLayout());


		// Function Selection
		String[] functions = { "Add Gizmo", "Remove Gizmo", "Rotate Gizmo", "Move Gizmo", "Connect Gizmos", "Bind Key", "Set Physics Constants" };
		functionCB = new JComboBox<String>(functions);
		functionCB.addItemListener(new BuildModeFunctionChangeListener(this));
		menuPanel.add(functionCB, BorderLayout.NORTH);


		// Add Gizmo panel
		JPanel addGizmo = new JPanel(new GridLayout(0, 2));
		addGizmo.setPreferredSize(new Dimension(170, 20));

		JLabel label = new JLabel(" Choose a gizmo to add:");
		label.setFont(new Font("Arial", 1, 13));
		menuPanel.add(label);

		bg = new ButtonGroup();

		JRadioButton square = new JRadioButton("Square Bumper");
		square.setActionCommand("Square");
		bg.add(square);

		JRadioButton circle = new JRadioButton("Circle Bumper");
		circle.setActionCommand("Circle");
		bg.add(circle);

		JRadioButton triangle = new JRadioButton("Triangle Bumper");
		triangle.setActionCommand("Triangle");
		bg.add(triangle);

		JRadioButton absorber = new JRadioButton("Absorber");
		absorber.setActionCommand("Absorber");
		bg.add(absorber);

		JRadioButton leftFlipper = new JRadioButton("Left Flipper");
		leftFlipper.setActionCommand("Left Flipper");
		bg.add(leftFlipper);

		JRadioButton rightFlipper = new JRadioButton("Right Flipper");
		rightFlipper.setActionCommand("Right Flipper");
		bg.add(rightFlipper);

		JRadioButton ball = new JRadioButton("Ball");
		ball.setActionCommand("Ball");
		bg.add(ball);

		square.setSelected(true);
		addGizmo.add(square);
		addGizmo.add(circle);
		addGizmo.add(triangle);
		addGizmo.add(absorber);
		addGizmo.add(leftFlipper);
		addGizmo.add(rightFlipper);
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

		menuPanel.add(cards, BorderLayout.CENTER);


		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(260, 70));
		buttonPanel.setLayout(new GridLayout(2, 2));

		JButton undo = new JButton("Undo");
		undo.setActionCommand("undo");
		undo.addActionListener(undoRedoAL);
		buttonPanel.add(undo);

		JButton redo = new JButton("Redo");
		redo.setActionCommand("redo");
		redo.addActionListener(undoRedoAL);
		buttonPanel.add(redo);

		JButton reload = new JButton("Reset Board");
		reload.setActionCommand("resetBoard");
		reload.addActionListener(buildModeAL);
		buttonPanel.add(reload);

		JButton playMode = new JButton("Play!");
		playMode.setActionCommand("playMode");
		playMode.addActionListener(buildModeAL);
		buttonPanel.add(playMode);

		menuPanel.add(buttonPanel, BorderLayout.SOUTH);
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
		return menuPanel;
	}
}
