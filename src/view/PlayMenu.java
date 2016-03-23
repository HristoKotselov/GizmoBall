package view;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import controller.PlayModeButtonListener;
import model.IMainEngine;

public class PlayMenu implements IPlayMenu {

	/* GUI */
	private JPanel buttonMenu;

	/** Required as a reference for Controllers **/
	private IGameWindow gameWindow;

	/* Model */
	private IMainEngine model;

	/* Controller */
	private PlayModeButtonListener playModeAL;
	// private StartGameListener startGameAL;
	// private StopGameListener stopGameAL;
	
	private JCheckBox dynamicEdit;

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
		// startGameAL = new StartGameListener(model);
		// stopGameAL = new StopGameListener(model);

		buttonMenu = new JPanel();
		buttonMenu.setLayout(new GridLayout(0, 1, 0, 10));

		JButton start = new JButton("Start");
		start.setActionCommand("start");
		start.setFocusable(false);
		start.addActionListener(playModeAL);
		start.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton tick = new JButton("Tick");
		tick.setActionCommand("tick");
		tick.setFocusable(false);
		tick.addActionListener(playModeAL);
		tick.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton stop = new JButton("Stop");
		stop.setActionCommand("stop");
		stop.setFocusable(false);
		stop.addActionListener(playModeAL);
		stop.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton reload = new JButton("Reload");
		reload.setActionCommand("reload");
		reload.setFocusable(false);
		reload.addActionListener(playModeAL);
		reload.setFont(new Font("Arial", Font.PLAIN, 20));

		// JButton shoot = new JButton("<html>Shoot ball<br />(idea)</html>");
		// shoot.setFont(new Font("Arial", Font.PLAIN, 30));

		JButton buildMode = new JButton("Build Mode");
		buildMode.setActionCommand("buildMode");
		buildMode.setFocusable(false);
		buildMode.addActionListener(playModeAL);
		buildMode.setFont(new Font("Arial", Font.PLAIN, 20));

		dynamicEdit = new JCheckBox(" Enable Dynamic Editing");
		dynamicEdit.setActionCommand("dynamicedit");
		dynamicEdit.setFocusable(false);
		dynamicEdit.setFont(new Font("Arial", Font.PLAIN, 20));
		dynamicEdit.addActionListener(playModeAL);
		dynamicEdit.setSelected(true);

		buttonMenu.add(start);
		buttonMenu.add(stop);
		buttonMenu.add(tick);
		buttonMenu.add(reload);
		// buttonMenu.add(shoot);
//		buttonMenu.add(buildMode);
		buttonMenu.add(dynamicEdit);

	}

	public JPanel getMenu() {
		return buttonMenu;
	}

	@Override
	public boolean isDynamicEditEnabled() {
		return dynamicEdit.isSelected();
	}

}
