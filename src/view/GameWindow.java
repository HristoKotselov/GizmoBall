package view;

import javax.swing.JFrame;
import controller.SaveFileListener;
import controller.LoadFileListener;
import model.IMainEngine;
import model.MainEngine;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import controller.WindowListener;

public class GameWindow implements IGameWindow {
	private JFrame window1, window2;

	private IMenu buildmenu, playmenu;
	private IBoard buildboard, playboard;
	private LoadFileListener loadFileAL;
	private SaveFileListener saveFileAL;
	private IMainEngine m;
	/* other GUI components */
	private PlayBoard gameBoard;
	private BuildBoard buildBoard;

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
	public void initialize() {
		loadFileAL = new LoadFileListener(m);
		saveFileAL = new SaveFileListener(m);
		window1 = new JFrame("Play Mode");
		window1.setBounds(100, 100, 720, 600);
		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//start of drop menu
		JMenuBar menuBar = new JMenuBar();
		window1.setJMenuBar(menuBar);

		JMenu mnFolio = new JMenu("Game");
		menuBar.add(mnFolio);

		JMenuItem mntmOpen = new JMenuItem("Load");
		mntmOpen.setActionCommand("load");
		mntmOpen.addActionListener(loadFileAL);
		mnFolio.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setActionCommand("save");
		mntmSave.addActionListener(saveFileAL);
		mnFolio.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFolio.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFolio.add(mntmExit);
		window1.getContentPane().setLayout(new FlowLayout());
		//end of menu bar

		JSeparator separator1 = new JSeparator();
		window1.getContentPane().add(separator1);

		// TODO
		buildmenu = new BuildMenu();
		buildboard = new BuildBoard();

		window1.add(buildmenu.getMenu());
		window1.add(buildboard.getBoard());

		JLabel tips = new JLabel("Action Tip:");
		tips.setFont(new Font("Arial", 1, 12));
		tips.setForeground(Color.BLUE);
		window1.add(tips);

		JTextArea textarea = new JTextArea(1, 50);
		textarea.setBackground(Color.WHITE);
		textarea.setEditable(false);
		window1.add(textarea);

	}
}
