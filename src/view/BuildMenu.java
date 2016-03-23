package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import controller.BuildModeButtonListener;
import controller.BuildModeFunctionChangeListener;
import model.IMainEngine;
import model.IPhysicsConfig;

public class BuildMenu implements IBuildMenu, Observer {

	/* GUI */
	private JPanel menuPanel;
	private ButtonGroup gizmoRButton, keyeventRButton, connRButton;
	private JComboBox<String> functionCB;
	private JPanel cards;

	// GUI component that need to be accessed/changed
	private JTextField ballDirection;
	private JTextField ballSpeed;
	private JTextField mu1Field;
	private JTextField mu2Field;
	private JTextField gravityField;
	private JLabel mu1StoredLabel;
	private JLabel mu2StoredLabel;
	private JLabel gravityStoredLabel;

	/** Required as a reference for Controllers **/
	private IGameWindow gameWindow;

	/* Model */
	private IMainEngine model;

	/* Controller */
	private BuildModeButtonListener buildModeAL;

	public BuildMenu(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
		model.getPhysicsConfig().addObserver(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		buildModeAL = new BuildModeButtonListener(model, gameWindow, this);

		menuPanel = new JPanel();
		menuPanel.setPreferredSize(new Dimension(300, 375));
		menuPanel.setLayout(new BorderLayout());

		// Function Selection
		String[] functions = { "Add Gizmo", "Remove Gizmo", "Rotate Gizmo", "Move Gizmo", "Add Ball", "Connect Gizmos", "Bind Key", "Set Physics Constants" };
		functionCB = new JComboBox<String>(functions);
		functionCB.setFocusable(false);
		functionCB.addItemListener(new BuildModeFunctionChangeListener(this, gameWindow));
		menuPanel.add(functionCB, BorderLayout.NORTH);


		// Add Gizmo panel
		JPanel addGizmo = new JPanel(new GridLayout(0, 2));

		JLabel label = new JLabel(" Choose a gizmo to add:");
		label.setFont(new Font("Arial", 1, 13));
		menuPanel.add(label);

		gizmoRButton = new ButtonGroup();

		JRadioButton square = new JRadioButton("Square Bumper");
		square.setActionCommand("Square");
		square.setFocusable(false);
		gizmoRButton.add(square);

		JRadioButton circle = new JRadioButton("Circle Bumper");
		circle.setActionCommand("Circle");
		circle.setFocusable(false);
		gizmoRButton.add(circle);

		JRadioButton triangle = new JRadioButton("Triangle Bumper");
		triangle.setActionCommand("Triangle");
		triangle.setFocusable(false);
		gizmoRButton.add(triangle);

		JRadioButton absorber = new JRadioButton("Absorber");
		absorber.setActionCommand("Absorber");
		absorber.setFocusable(false);
		gizmoRButton.add(absorber);

		JRadioButton leftFlipper = new JRadioButton("Left Flipper");
		leftFlipper.setActionCommand("Left Flipper");
		leftFlipper.setFocusable(false);
		gizmoRButton.add(leftFlipper);

		JRadioButton rightFlipper = new JRadioButton("Right Flipper");
		rightFlipper.setActionCommand("Right Flipper");
		rightFlipper.setFocusable(false);
		gizmoRButton.add(rightFlipper);

		square.setSelected(true);
		addGizmo.add(square);
		addGizmo.add(circle);
		addGizmo.add(triangle);
		addGizmo.add(absorber);
		addGizmo.add(leftFlipper);
		addGizmo.add(rightFlipper);


		// Add Ball panel
<<<<<<< HEAD
<<<<<<< HEAD
		// TODO Low priority: be fancy and change font colour to red if numbers not properly formatted
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
		JPanel addBall = new JPanel();

		JPanel controls = new JPanel(new GridLayout(0, 2, 5, 10));

		JLabel directionLabel = new JLabel("Initial Direction: ", JLabel.RIGHT);
		directionLabel.setFont(new Font("Arial", 1, 15));

		ballDirection = new JTextField("0.0", 4);
		ballDirection.setFont(new Font("Arial", 1, 15));

		JLabel speedLabel = new JLabel("Initial Speed: ", JLabel.RIGHT);
		speedLabel.setFont(new Font("Arial", 1, 15));

		ballSpeed = new JTextField("50.0", 4);
		ballSpeed.setFont(new Font("Arial", 1, 15));

		controls.add(directionLabel);
		controls.add(ballDirection);
		controls.add(speedLabel);
		controls.add(ballSpeed);

		JButton applyBallSettings = new JButton("Apply Settings and Restart");
		applyBallSettings.setFont(new Font("Arial", 1, 15));
		applyBallSettings.setFocusable(false);
		applyBallSettings.setActionCommand("setBall");
		applyBallSettings.addActionListener(buildModeAL);

		addBall.add(controls);
		addBall.add(applyBallSettings);


		// Add Key Binding panel
		JPanel keyBind = new JPanel(new GridLayout(0, 1));

		keyeventRButton = new ButtonGroup();

		JRadioButton keypress = new JRadioButton("Key Pressed");
		keypress.setActionCommand("keypress");
		keypress.setFocusable(false);
		keyeventRButton.add(keypress);

		JRadioButton keyrelease = new JRadioButton("Key Released");
		keyrelease.setActionCommand("keyrelease");
		keyrelease.setFocusable(false);
		keyeventRButton.add(keyrelease);

		JRadioButton keyremove = new JRadioButton("Remove Binding");
		keyremove.setActionCommand("keyremove");
		keyremove.setFocusable(false);
		keyeventRButton.add(keyremove);

		JRadioButton keyremoveall = new JRadioButton("Remove All Bindings");
		keyremoveall.setActionCommand("keyremoveall");
		keyremoveall.setFocusable(false);
		keyeventRButton.add(keyremoveall);

		keypress.setSelected(true);
		keyBind.add(keypress);
		keyBind.add(keyrelease);
		keyBind.add(keyremove);
		keyBind.add(keyremoveall);

		// Add Key Binding panel
		JPanel connect = new JPanel(new GridLayout(0, 1));

		connRButton = new ButtonGroup();

		JRadioButton addConn = new JRadioButton("Add Connection");
		addConn.setActionCommand("addconn");
		addConn.setFocusable(false);
		connRButton.add(addConn);

		JRadioButton remConn = new JRadioButton("Remove Connection");
		remConn.setActionCommand("remconn");
		remConn.setFocusable(false);
		connRButton.add(remConn);

		JRadioButton remAllConn = new JRadioButton("Remove All Connections");
		remAllConn.setActionCommand("remallconn");
		remAllConn.setFocusable(false);
		connRButton.add(remAllConn);

		addConn.setSelected(true);
		connect.add(addConn);
		connect.add(remConn);
		connect.add(remAllConn);


		// Physics Constants panel
		JPanel setPhysics = new JPanel();

		JPanel physicsSetting = new JPanel(new GridLayout(0, 2, 5, 10));

		JLabel mu1StoredLabelTitle = new JLabel("    Friction mu1:");
		mu1StoredLabelTitle.setFont(new Font("Arial", 1, 15));
		mu1StoredLabel = new JLabel();
		// FONT.BOLD bitwise 'or' FONT.ITALIC works due to them being bitmasks
		mu1StoredLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));

		JLabel mu2StoredLabelTitle = new JLabel("    Friction mu2:");
		mu2StoredLabelTitle.setFont(new Font("Arial", 1, 15));
		mu2StoredLabel = new JLabel();
		mu2StoredLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));

		JLabel gravityStoredLabelTitle = new JLabel("    Gravity:");
		gravityStoredLabelTitle.setFont(new Font("Arial", 1, 15));
		gravityStoredLabel = new JLabel();
		gravityStoredLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));

		JLabel mu1label = new JLabel("New Friction mu1:");
		mu1label.setFont(new Font("Arial", 1, 15));
		mu1Field = new JTextField(4);
		mu1Field.setFont(new Font("Arial", 1, 15));

		JLabel mu2label = new JLabel("New Friction mu2:");
		mu2label.setFont(new Font("Arial", 1, 15));
		mu2Field = new JTextField(4);
		mu2Field.setFont(new Font("Arial", 1, 15));

		JLabel gravitylabel = new JLabel("New Gravity:");
		gravitylabel.setFont(new Font("Arial", 1, 15));
		gravityField = new JTextField(4);
		gravityField.setFont(new Font("Arial", 1, 15));

		// get the current Physics Settings display up!
		updateStoredPhysicsDisplay();

		physicsSetting.add(mu1StoredLabelTitle);
		physicsSetting.add(mu1StoredLabel);
		physicsSetting.add(mu2StoredLabelTitle);
		physicsSetting.add(mu2StoredLabel);
		physicsSetting.add(gravityStoredLabelTitle);
		physicsSetting.add(gravityStoredLabel);
		physicsSetting.add(mu1label);
		physicsSetting.add(mu1Field);
		physicsSetting.add(mu2label);
		physicsSetting.add(mu2Field);
		physicsSetting.add(gravitylabel);
		physicsSetting.add(gravityField);

		JButton applyPhysicsSettings = new JButton("Apply Settings");
		applyPhysicsSettings.setFont(new Font("Arial", 1, 15));
		applyPhysicsSettings.setFocusable(false);
		applyPhysicsSettings.setActionCommand("setPhysics");
		applyPhysicsSettings.addActionListener(buildModeAL);

		setPhysics.add(physicsSetting);
		setPhysics.add(applyPhysicsSettings);


		// Card panel
		cards = new JPanel(new CardLayout());
		cards.add(addGizmo, functions[0]);
		cards.add(new JPanel(), functions[1]);
		cards.add(new JPanel(), functions[2]);
		cards.add(new JPanel(), functions[3]);
		cards.add(addBall, functions[4]);
		cards.add(connect, functions[5]);
		cards.add(keyBind, functions[6]);
		cards.add(setPhysics, functions[7]);

		menuPanel.add(cards, BorderLayout.CENTER);
	}

	@Override
	public String getSelectedFunction() {
		return functionCB.getSelectedItem().toString();
	}

	@Override
	public double getBallDirectionFromGUI() {
		return Double.parseDouble(ballDirection.getText());
	}

	@Override
	public double getBallSpeedFromGUI() {
		return Double.parseDouble(ballSpeed.getText());
	}

	@Override
	public double getFrictionCoef1FromGUI() {
		return Double.parseDouble(mu1Field.getText());
	}

	@Override
	public double getFrictionCoef2FromGUI() {
		return Double.parseDouble(mu2Field.getText());
	}

	@Override
	public double getGravityFromGUI() {
		return Double.parseDouble(gravityField.getText());
	}

	@Override
	public String getSelectedGizmo() {
		return gizmoRButton.getSelection().getActionCommand();
	}


	@Override
	public String getKeyEventType() {
		return keyeventRButton.getSelection().getActionCommand();
	}


	@Override
	public String getConnectFunction() {
		return connRButton.getSelection().getActionCommand();
	}

	@Override
	public void setCard() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, getSelectedFunction());
	}

	public JPanel getMenu() {
		return menuPanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateStoredPhysicsDisplay();
	}

	private void updateStoredPhysicsDisplay() {
		IPhysicsConfig physicsConfig = model.getPhysicsConfig();

		String mu1 = Double.toString(physicsConfig.getFrictionCoef1());
		String mu2 = Double.toString(physicsConfig.getFrictionCoef2() * IMainEngine.L);
		String g = Double.toString(physicsConfig.getGravity() / IMainEngine.L);

		mu1StoredLabel.setText(mu1 + " per second");
		mu2StoredLabel.setText(mu2 + " per L");
		gravityStoredLabel.setText(g + " L/sec\u00B2");
<<<<<<< HEAD
<<<<<<< HEAD

		mu1Field.setText(mu1);
		mu2Field.setText(mu2);
		gravityField.setText(g);
=======
>>>>>>> 84931fe3ceaa7b9f66afcccdfc818bd402232cbf
=======
>>>>>>> 24d1d8980855bc42d310646b4924367d62afb755
	}

}
