package view;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import controller.PlayModeButtonListener;
import model.IMainEngine;

public class PlayMenu {

	/* GUI */
	private JPanel buttonMenu;

	/** Required as a reference for Controllers **/
	private IGameWindow gameWindow;

	/* Model */
	private IMainEngine model;

	/* Controller */
	private PlayModeButtonListener playModeAL;
	//private StartGameListener startGameAL;
	//private StopGameListener stopGameAL;

	public PlayMenu(IMainEngine model, IGameWindow gameWindow) {
		this.model = model;
		this.gameWindow = gameWindow;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO the listeners should have been passed through parameter
		playModeAL = new PlayModeButtonListener(model, gameWindow);
		//startGameAL = new StartGameListener(model);
		//stopGameAL = new StopGameListener(model);

		buttonMenu = new JPanel();
		buttonMenu.setLayout(new GridLayout(0, 1, 0, 30));

		JButton start = new JButton("Start");
		start.setActionCommand("start");
		start.setFocusable(false);
		start.addActionListener(playModeAL);
		start.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton tick = new JButton("Tick");
		tick.setActionCommand("tick");
		tick.setFocusable(false);
		tick.addActionListener(playModeAL);
		tick.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton stop = new JButton("Stop");
		stop.setActionCommand("stop");
		stop.setFocusable(false);
		stop.addActionListener(playModeAL);
		stop.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton reload = new JButton("Reload");
		reload.setActionCommand("reload");
		reload.setFocusable(false);
		reload.addActionListener(playModeAL);
		reload.setFont(new Font("Arial", Font.PLAIN, 40));

		// JButton shoot = new JButton("<html>Shoot ball<br />(idea)</html>");
		// shoot.setFont(new Font("Arial", Font.PLAIN, 30));

		JButton buildMode = new JButton("Build Mode");
		buildMode.setActionCommand("buildMode");
		buildMode.setFocusable(false);
		buildMode.addActionListener(playModeAL);
		buildMode.setFont(new Font("Arial", Font.PLAIN, 30));

		buttonMenu.add(start);
		buttonMenu.add(stop);
		buttonMenu.add(tick);
		buttonMenu.add(reload);
		// buttonMenu.add(shoot);
		buttonMenu.add(buildMode);

	}

	public JPanel getMenu() {
		return buttonMenu;
	}

}
