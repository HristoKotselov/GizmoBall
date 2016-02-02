package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class GameWindow{

	private JFrame window1, window2;
	private IMenu buildmenu, playmenu;
	private IBoard buildboard, playboard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.initialize();
					window.window1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		window1 = new JFrame("Play Mode");
		window1.setBounds(100, 100, 720, 600);
		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//start of drop menu
		JMenuBar menuBar = new JMenuBar();
		window1.setJMenuBar(menuBar);

		JMenu mnFolio = new JMenu("Game");
		menuBar.add(mnFolio);

		JMenuItem mntmOpen = new JMenuItem("Load");
		mnFolio.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFolio.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFolio.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFolio.add(mntmExit);
		window1.getContentPane().setLayout(new FlowLayout());
		//end of menu bar
		
		JSeparator separator1 = new JSeparator();
		window1.getContentPane().add(separator1);
		
		buildmenu = new PlayMenu();
		buildboard = new PlayBoard();
		
		buildmenu.initialize();
		buildboard.initialize();
		
		window1.add(buildmenu.getMenu());
		window1.add(buildboard.getBoard());
		
		JLabel tips = new JLabel("Action Tip:");
		tips.setFont(new Font("Arial",1,12));
		tips.setForeground(Color.BLUE);
		window1.add(tips);
		
		JTextArea textarea = new JTextArea(1,50);
		textarea.setBackground(Color.WHITE);
		textarea.setEditable(false);
		window1.add(textarea);
		
	}
}