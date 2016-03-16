package view;

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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import controller.BuildModeMouseListener;
import controller.LoadFileListener;
import controller.SaveFileListener;
import model.IMainEngine;

public class GameWindow implements IGameWindow {
	
	/* GUI components */
	private JFrame buildModeWindow, playModeWindow;

	private BuildMenu buildmenu;
	private BuildBoard buildboard;
	private PlayMenu playmenu;
	private PlayBoard playboard;
	
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
		initialisePlayWindow();
	}
	
	private void initialiseBuildWindow(){
		buildModeWindow = new JFrame("Build Mode");
		buildModeWindow.setBounds(100, 100, 750, 500);
		buildModeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// start of drop menu
		JMenuBar menuBar = new JMenuBar();
		buildModeWindow.setJMenuBar(menuBar);

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
		buildModeWindow.getContentPane().setLayout(new FlowLayout());
		// end of menu bar

		JSeparator separator1 = new JSeparator();
		buildModeWindow.add(separator1);

		// TODO might need to change later
		buildmenu = new BuildMenu(model, this);
		buildboard = new BuildBoard(model);

		BuildModeMouseListener l = new BuildModeMouseListener(model, buildmenu);

		buildboard.addMouseListener(l);
		// buildboard.addMouseMotionListener(l);

		buildModeWindow.add(buildmenu.getMenu());
		buildModeWindow.add(new JSeparator());
		buildModeWindow.add(buildboard);

		JLabel tips = new JLabel("Action Tip:");
		tips.setFont(new Font("Arial", 1, 12));
		tips.setForeground(Color.BLUE);
		buildModeWindow.add(tips);

		JTextArea textarea = new JTextArea(1, 45);
		textarea.setBackground(Color.WHITE);
		textarea.setEditable(false);
		buildModeWindow.add(textarea);

		buildModeWindow.setVisible(true);
	}
	
	private void initialisePlayWindow(){
		playModeWindow = new JFrame("Play Mode");
		playModeWindow.setBounds(100, 100, 750, 500);
		playModeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// start of drop menu
		JMenuBar menuBar = new JMenuBar();
		playModeWindow.setJMenuBar(menuBar);

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
		playModeWindow.getContentPane().setLayout(new FlowLayout());
		// end of menu bar

		JSeparator separator1 = new JSeparator();
		playModeWindow.add(separator1);
		
		// TODO might need to change later
		playmenu = new PlayMenu(model, this);
		playboard = new PlayBoard(model);

		playModeWindow.add(playmenu.getMenu());
		playModeWindow.add(new JSeparator());
		playModeWindow.add(playboard);

		JLabel tips = new JLabel("Action Tip:");
		tips.setFont(new Font("Arial", 1, 12));
		tips.setForeground(Color.BLUE);
		playModeWindow.add(tips);

		JTextArea textarea = new JTextArea(1, 45);
		textarea.setBackground(Color.WHITE);
		textarea.setEditable(false);
		playModeWindow.add(textarea);

		playModeWindow.setVisible(true);
	}
	
	@Override
	public void switchToPlayMode(){
		buildModeWindow.setVisible(false);
	}

	@Override
	public String getFile() {
		// TODO change to allow save button using string param
		JFileChooser f = new JFileChooser();
		f.showOpenDialog(buildModeWindow);

		File file = f.getSelectedFile();

		if (file != null) {
			return file.getAbsolutePath();
		} else {
			return null;
		}
	}
}