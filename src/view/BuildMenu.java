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
import javax.swing.JSlider;
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
		menuPanel.setPreferredSize(new Dimension(300, 400));
		menuPanel.setLayout(new BorderLayout());

		// Function Selection
		String[] functions = { "Add Gizmo", "Remove Gizmo", "Rotate Gizmo", "Move Gizmo", "Add Ball", "Connect Gizmos", "Bind Key", "Set Physics Constants" };
		functionCB = new JComboBox<String>(functions);
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
		gizmoRButton.add(square);

		JRadioButton circle = new JRadioButton("Circle Bumper");
		circle.setActionCommand("Circle");
		gizmoRButton.add(circle);

		JRadioButton triangle = new JRadioButton("Triangle Bumper");
		triangle.setActionCommand("Triangle");
		gizmoRButton.add(triangle);

		JRadioButton absorber = new JRadioButton("Absorber");
		absorber.setActionCommand("Absorber");
		gizmoRButton.add(absorber);

		JRadioButton leftFlipper = new JRadioButton("Left Flipper");
		leftFlipper.setActionCommand("Left Flipper");
		gizmoRButton.add(leftFlipper);

		JRadioButton rightFlipper = new JRadioButton("Right Flipper");
		rightFlipper.setActionCommand("Right Flipper");
		gizmoRButton.add(rightFlipper);

		square.setSelected(true);
		addGizmo.add(square);
		addGizmo.add(circle);
		addGizmo.add(triangle);
		addGizmo.add(absorber);
		addGizmo.add(leftFlipper);
		addGizmo.add(rightFlipper);


		// Add Ball panel
		// TODO Low priority: be fancy and change font colour to red if numbers not properly formatted
		JPanel addBall = new JPanel();

		JPanel controls = new JPanel(new GridLayout(0, 2, 5, 10));

		JLabel selectBallLabel = new JLabel("Ball: ", JLabel.RIGHT);
		selectBallLabel.setFont(new Font("Arial", 1, 15));

		String[] ballsString = { "<New Ball>" };
		JComboBox<String> balls = new JComboBox<String>(ballsString);

		controls.add(selectBallLabel);
		controls.add(balls);

		JLabel xcoordLabel = new JLabel("X: ", JLabel.RIGHT);
		JTextField xcoord = new JTextField(4);
		xcoordLabel.setFont(new Font("Arial", 1, 15));
		xcoord.setFont(new Font("Arial", 1, 15));

		JLabel ycoordLabel = new JLabel("Y: ", JLabel.RIGHT);
		JTextField ycoord = new JTextField(4);
		ycoordLabel.setFont(new Font("Arial", 1, 15));
		ycoord.setFont(new Font("Arial", 1, 15));

		JLabel directionLabel = new JLabel("Initial Direction: ", JLabel.RIGHT);
		ballDirection = new JTextField("0.0", 4);
		directionLabel.setFont(new Font("Arial", 1, 15));
		ballDirection.setFont(new Font("Arial", 1, 15));

		JLabel speedLabel = new JLabel("Initial Speed: ", JLabel.RIGHT);
		ballSpeed = new JTextField("50.0", 4);
		speedLabel.setFont(new Font("Arial", 1, 15));
		ballSpeed.setFont(new Font("Arial", 1, 15));

		controls.add(xcoordLabel);
		controls.add(xcoord);
		controls.add(ycoordLabel);
		controls.add(ycoord);
		controls.add(directionLabel);
		controls.add(ballDirection);
		controls.add(speedLabel);
		controls.add(ballSpeed);

		JButton applyBallSettings = new JButton("Apply Settings");
		applyBallSettings.setFont(new Font("Arial", 1, 15));

		addBall.add(controls);
		addBall.add(applyBallSettings);


		// Add Key Binding panel
		JPanel keyBind = new JPanel(new GridLayout(0, 1));

		keyeventRButton = new ButtonGroup();

		JRadioButton keypress = new JRadioButton("Key Pressed");
		keypress.setActionCommand("keypress");
		keyeventRButton.add(keypress);

		JRadioButton keyrelease = new JRadioButton("Key Released");
		keyrelease.setActionCommand("keyrelease");
		keyeventRButton.add(keyrelease);

		JRadioButton keyremove = new JRadioButton("Remove Bindings");
		keyremove.setActionCommand("keyremove");
		keyeventRButton.add(keyremove);

		keypress.setSelected(true);
		keyBind.add(keypress);
		keyBind.add(keyrelease);
		keyBind.add(keyremove);

		// Add Key Binding panel
		JPanel connect = new JPanel(new GridLayout(0, 1));

		connRButton = new ButtonGroup();

		JRadioButton addConn = new JRadioButton("Add Connection");
		addConn.setActionCommand("addconn");
		connRButton.add(addConn);

		JRadioButton remConn = new JRadioButton("Remove Connection");
		remConn.setActionCommand("remconn");
		connRButton.add(remConn);

		addConn.setSelected(true);
		connect.add(addConn);
		connect.add(remConn);


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

		// get the current Physics Settings display up!
		updateStoredPhysicsDisplay();

		JLabel mu1label = new JLabel("New Friction mu1:");
		mu1label.setFont(new Font("Arial", 1, 15));
		mu1Field = new JTextField(4);
		mu1Field.setFont(new Font("Arial", 1, 15));
		/*JSlider mu1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		mu1.setMinorTickSpacing(1);
		mu1.setMajorTickSpacing(5);
		mu1.setPaintTicks(true);
		mu1.setPaintLabels(true);
		mu1.setLabelTable(mu1.createStandardLabels(5));*/

		JLabel mu2label = new JLabel("New Friction mu2:");
		mu2label.setFont(new Font("Arial", 1, 15));
		mu2Field = new JTextField(4);
		mu2Field.setFont(new Font("Arial", 1, 15));
		/*JSlider mu2 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		mu2.setMinorTickSpacing(1);
		mu2.setMajorTickSpacing(5);
		mu2.setPaintTicks(true);
		mu2.setPaintLabels(true);
		mu2.setLabelTable(mu2.createStandardLabels(5));
		*/

		JLabel gravitylabel = new JLabel("New Gravity:");
		gravitylabel.setFont(new Font("Arial", 1, 15));
		gravityField = new JTextField(4);
		gravityField.setFont(new Font("Arial", 1, 15));
		/*JSlider gravity = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		gravity.setMinorTickSpacing(5);
		gravity.setMajorTickSpacing(10);
		gravity.setPaintTicks(true);
		gravity.setPaintLabels(true);
		gravity.setLabelTable(gravity.createStandardLabels(10));*/

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


		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(260, 70));
		buttonPanel.setLayout(new GridLayout(2, 2));



		JButton playMode = new JButton("Play!");
		playMode.setActionCommand("playMode");
		playMode.addActionListener(buildModeAL);
		buttonPanel.add(playMode);

		menuPanel.add(buttonPanel, BorderLayout.SOUTH);
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

		mu1StoredLabel.setText(
				Double.toString(physicsConfig.getFrictionCoef1()) +
						" per second");
		mu2StoredLabel.setText(
				Double.toString(physicsConfig.getFrictionCoef2() * model.getLInPixels()) +
						" per L");
		gravityStoredLabel.setText(
				Double.toString(physicsConfig.getGravity() / model.getLInPixels()) +
						" L/sec\u00B2");
	}

}
