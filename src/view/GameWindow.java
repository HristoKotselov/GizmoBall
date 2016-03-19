package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import controller.BuildModeMouseListener;
import controller.LoadFileListener;
import controller.SaveFileListener;
import model.IMainEngine;

public class GameWindow implements IGameWindow {

	/* GUI components */
	private JFrame gameWindow;

	private JPanel sidebarPanel;

	private BuildMenu buildmenu;
	private PlayMenu playmenu;
	private GameBoard board;

	private JLabel coords;

	/* Controllers */
	private LoadFileListener loadFileAL;
	private SaveFileListener saveFileAL;

	/* Model */
	private IMainEngine model;


	/**
	 * Initialize the contents of the frame.
	 */
	public GameWindow(IMainEngine m) {
		model = m;
		loadFileAL = new LoadFileListener(this, model);
		saveFileAL = new SaveFileListener(this, model);

		initialiseBuildWindow();
	}

	private void initialiseBuildWindow() {
		gameWindow = new JFrame("Build Mode");
		gameWindow.setBounds(100, 100, 750, 500);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// start of drop menu
		JMenuBar menuBar = new JMenuBar();
		gameWindow.setJMenuBar(menuBar);

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
		gameWindow.getContentPane().setLayout(new FlowLayout());
		// end of menu bar

		JSeparator separator1 = new JSeparator();
		gameWindow.add(separator1);


		sidebarPanel = new JPanel(new CardLayout());

		buildmenu = new BuildMenu(model, this);
		playmenu = new PlayMenu(model, this);

		sidebarPanel.add(buildmenu.getMenu(), "Build Mode");
		sidebarPanel.add(playmenu.getMenu(), "Play Mode");


		board = new GameBoard(model);
		BuildModeMouseListener l = new BuildModeMouseListener(this, model, buildmenu);
		board.addMouseListener(l);
		board.addMouseMotionListener(l);

		gameWindow.add(sidebarPanel);
		gameWindow.add(new JSeparator());
		gameWindow.add(board);

		JLabel tips = new JLabel("Action Tip:");
		tips.setFont(new Font("Arial", 1, 12));
		tips.setForeground(Color.BLUE);
		gameWindow.add(tips);

		JTextArea textarea = new JTextArea(1, 45);
		textarea.setBackground(Color.WHITE);
		textarea.setEditable(false);
		gameWindow.add(textarea);

		coords = new JLabel("X: 100 (10), Y: 100 (10)");
		gameWindow.add(coords);

		gameWindow.setVisible(true);
	}

	@Override
	public void setMode(String mode) {
		CardLayout cl = (CardLayout) sidebarPanel.getLayout();
		cl.show(sidebarPanel, mode);

		gameWindow.setTitle(mode);
		setCoords(0, 0);
	}

	@Override
	public void setCoords(int x, int y) {
		if (gameWindow.getTitle().equals("Build Mode")) {
			String xP = String.format("%03d", x);
			String yP = String.format("%03d", y);
			String xG = String.format("%02d", x / model.getLInPixels());
			String yG = String.format("%02d", y / model.getLInPixels());

			coords.setText("X: " + xG + " (" + xP + "), Y: " + yG + " (" + yP + ")");
		} else {
			coords.setText("");
		}
	}

	@Override
	public String getFile(String buttonText) {
		JFileChooser f = new JFileChooser();
		f.showDialog(gameWindow, buttonText);

		File file = f.getSelectedFile();

		if (file != null) {
			return file.getAbsolutePath();
		} else {
			return null;
		}
	}
}