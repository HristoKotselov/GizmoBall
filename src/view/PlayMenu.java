package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.AMainListener;
import controller.BuildModeMainListener;
import controller.ChangeBallConfigListener;
import controller.PhysicsConfigListener;
import controller.PlayModeMainListener;
import controller.StartGameListener;
import controller.StopGameListener;
import controller.UndoRedoListener;
import controller.WindowListener;
import model.IMainEngine;

public class PlayMenu implements IMenu {

	/* GUI */
	private JPanel buttonMenu;

	/* Model */
	private IMainEngine model;

	/* Controller */
	private AMainListener playModeAL;
	private StartGameListener startGameAL;
	private StopGameListener stopGameAL;

	public PlayMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// TODO the listeners should have been passed through parameter
		//playModeAL = new PlayModeMainListener(model);
		startGameAL = new StartGameListener(model);
		stopGameAL = new StopGameListener(model);

		buttonMenu = new JPanel();
		buttonMenu.setPreferredSize(new Dimension(170, 400));
		buttonMenu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
		buttonMenu.setLayout(new GridLayout(0, 1));

		JButton start = new JButton("Start");
		start.setActionCommand("start");
		start.addActionListener(startGameAL);
		start.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton tick = new JButton("Tick");
		tick.setActionCommand("tick");
		tick.addActionListener(startGameAL);
		tick.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton stop = new JButton("Stop");
		stop.setActionCommand("stop");
		stop.addActionListener(stopGameAL);
		stop.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton reload = new JButton("Reload");
		reload.setActionCommand("reload");
		reload.addActionListener(playModeAL);
		reload.setFont(new Font("Arial", Font.PLAIN, 40));

		//JButton shoot = new JButton("<html>Shoot ball<br />(idea)</html>");
		//shoot.setFont(new Font("Arial", Font.PLAIN, 30));

		JButton buildMode = new JButton("<html>Build<br />Mode</html>");
		buildMode.setActionCommand("buildMode");
		buildMode.addActionListener(playModeAL);
		buildMode.setFont(new Font("Arial", Font.PLAIN, 30));

		buttonMenu.add(start);
		buttonMenu.add(stop);
		buttonMenu.add(tick);
		buttonMenu.add(reload);
		//buttonMenu.add(shoot);
		buttonMenu.add(buildMode);

	}

	public JPanel getMenu() {
		return buttonMenu;
	}

}
