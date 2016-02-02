package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class PlayMenu implements IMenu{


	private JPanel buttonMenu;
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		buttonMenu = new JPanel();
		buttonMenu.setPreferredSize(new Dimension(170, 500));
		buttonMenu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
		buttonMenu.setLayout(new GridLayout(0,1));
		
		JButton start = new JButton("Start");
		start.setFont(new Font("Arial", Font.PLAIN, 40));
		JButton tick = new JButton("Tick");
		tick.setFont(new Font("Arial", Font.PLAIN, 40));
		JButton stop = new JButton("Stop");
		stop.setFont(new Font("Arial", Font.PLAIN, 40));
		JButton reload = new JButton("Reload");
		reload.setFont(new Font("Arial", Font.PLAIN, 40));
		//JButton shoot = new JButton("<html>Shoot ball<br />(idea)</html>");
		//shoot.setFont(new Font("Arial", Font.PLAIN, 30));
		JButton buildMode = new JButton("<html>Build<br />Mode</html>");
		buildMode.setFont(new Font("Arial", Font.PLAIN, 30));

		buttonMenu.add(start);
		buttonMenu.add(stop);
		buttonMenu.add(tick);
		buttonMenu.add(reload);
		//buttonMenu.add(shoot);
		buttonMenu.add(buildMode);
	
	}
	
	public JPanel getMenu(){
		return buttonMenu;
	}

}
