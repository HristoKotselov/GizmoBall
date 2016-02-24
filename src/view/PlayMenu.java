package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import controller.WindowListener;
import model.IMainEngine;

public class PlayMenu implements IMenu {

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

		JButton start = new JButton("Start");
		start.setActionCommand("start");
		start.addActionListener(wdolis);
		start.setFont(new Font("Arial", Font.PLAIN, 40));
		JButton tick = new JButton("Tick");
		tick.setActionCommand("tick");
		tick.addActionListener(wdolis);
		tick.setFont(new Font("Arial", Font.PLAIN, 40));
		JButton stop = new JButton("Stop");
		stop.setActionCommand("stop");
		stop.addActionListener(wdolis);
		stop.setFont(new Font("Arial", Font.PLAIN, 40));
		JButton reload = new JButton("Reload");
		reload.setActionCommand("reload");
		reload.addActionListener(wdolis);
		reload.setFont(new Font("Arial", Font.PLAIN, 40));
		//JButton shoot = new JButton("<html>Shoot ball<br />(idea)</html>");
		//shoot.setFont(new Font("Arial", Font.PLAIN, 30));
		JButton buildMode = new JButton("<html>Build<br />Mode</html>");
		buildMode.setActionCommand("buildMode");
		buildMode.addActionListener(wdolis);
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
