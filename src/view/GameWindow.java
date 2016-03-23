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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.BuildModeButtonListener;
import controller.BuildModeMouseListener;
import controller.LoadFileListener;
import controller.MagicKeyListenerWrapper;
import controller.PlayModeKeyListener;
import controller.SaveFileListener;
import model.ActionTipDialogue;
import model.IMainEngine;

public class GameWindow implements IGameWindow {

	/* GUI components */
	private JFrame gameWindow;
	private JTabbedPane sidebarPanel;
	private BuildMenu buildmenu;
	private PlayMenu playmenu;
	private GameBoard board;

	private JLabel coords;
	private JTextArea actionTipsTextArea;

	/* Controllers */
	/** Just there for New Board command **/
	private BuildModeButtonListener buildModeAL;
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
		gameWindow = new JFrame("Gizmoball");
		gameWindow.setBounds(100, 100, 750, 500);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buildModeAL = new BuildModeButtonListener(model, this, buildmenu);

		// start of drop menu
		JMenuBar menuBar = new JMenuBar();
		gameWindow.setJMenuBar(menuBar);

		JMenu mnFolio = new JMenu("Game");
		menuBar.add(mnFolio);

		JMenuItem mntmReset = new JMenuItem("New");
		mntmReset.setActionCommand("resetBoard");
		mntmReset.addActionListener(buildModeAL);
		mnFolio.add(mntmReset);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setActionCommand("load");
		mntmOpen.addActionListener(loadFileAL);
		mnFolio.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setActionCommand("save");
		mntmSave.addActionListener(saveFileAL);
		mnFolio.add(mntmSave);

		JMenu edFolio = new JMenu("Edit");
//		menuBar.add(edFolio);

		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.setActionCommand("undo");
		mntmUndo.addActionListener(buildModeAL);
		edFolio.add(mntmUndo);

		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.setActionCommand("redo");
		mntmRedo.addActionListener(buildModeAL);
		edFolio.add(mntmRedo);


		JSeparator separator = new JSeparator();
		mnFolio.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setActionCommand("exit");
		mnFolio.add(mntmExit);
		mntmExit.addActionListener(buildModeAL);
		gameWindow.getContentPane().setLayout(new FlowLayout());
		// end of menu bar

		JSeparator separator1 = new JSeparator();
		gameWindow.add(separator1);


		sidebarPanel = new JTabbedPane();
		sidebarPanel.setFocusable(false);

		buildmenu = new BuildMenu(model, this);
		playmenu = new PlayMenu(model);

		sidebarPanel.add(buildmenu.getMenu(), "Build Mode");
		sidebarPanel.add(playmenu.getMenu(), "Play Mode");
		sidebarPanel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    if(pane.getSelectedIndex() == 0){
                    	actionTipsTextArea.setText(ActionTipDialogue.buildModeActionTip());
                    	
                    	if(!playmenu.isDynamicEditEnabled()){
                    		playmenu.pauseModel();
                    	}
                    }
                    if(pane.getSelectedIndex() == 1){
                    	actionTipsTextArea.setText(ActionTipDialogue.playModeActionTip());
                    }
                }
            }
        });


		board = new GameBoard(model, buildmenu, playmenu, this);
		BuildModeMouseListener l = new BuildModeMouseListener(board, model, buildmenu, playmenu, this);
		board.addMouseListener(l);
		board.addMouseMotionListener(l);

		PlayModeKeyListener kl = new PlayModeKeyListener(model);
		MagicKeyListenerWrapper mkl = new MagicKeyListenerWrapper(kl);
		board.addKeyListener(mkl);

		gameWindow.add(sidebarPanel);
		gameWindow.add(new JSeparator());
		gameWindow.add(board);

		JLabel tips = new JLabel("Action Tip:");
		tips.setFont(new Font("Arial", 1, 12));
		tips.setForeground(Color.BLUE);
		gameWindow.add(tips);

		actionTipsTextArea = new JTextArea(1, 45);
		actionTipsTextArea.setBackground(Color.WHITE);
		actionTipsTextArea.setEditable(false);
		actionTipsTextArea.setFocusable(false);
		gameWindow.add(actionTipsTextArea);
		// Default ActionTip when loaded up
		setActionTipsTextArea(ActionTipDialogue.addGizmoActionTip());

		coords = new JLabel("X: 100 (10), Y: 100 (10)");
		gameWindow.add(coords);

		gameWindow.setVisible(true);
	}

	public void updateCoordsLabel(int x, int y) {
		if (isBuildMode() || playmenu.isDynamicEditEnabled()) {
			String xP = String.format("%03d", x);
			String yP = String.format("%03d", y);
			String xG = String.format("%02d", x / IMainEngine.L);
			String yG = String.format("%02d", y / IMainEngine.L);

			coords.setText("X: " + xG + " (" + xP + "), Y: " + yG + " (" + yP + ")");
		} else {
			coords.setText("");
		}
	}

	@Override
	public String getFile(String buttonText, String lastLocation) {
		JFileChooser f = new JFileChooser();

		// set JFileChooser to user's last location, for quickness
		if (lastLocation != null) {
			File fileLoc = new File(lastLocation);
			f.setCurrentDirectory(fileLoc);
		}
		f.showDialog(gameWindow, buttonText);

		File file = f.getSelectedFile();

		if (file != null) {
			return file.getAbsolutePath();
		} else {
			return null;
		}
	}

	@Override
	public void setActionTipsTextArea(String message) {
		actionTipsTextArea.setForeground(Color.black);
		actionTipsTextArea.setText(message);
	}

	@Override
	public void setActionTipsTextAreaColour(Color colour) {
		actionTipsTextArea.setForeground(colour);
	}

	public boolean isBuildMode() {
		return sidebarPanel.getSelectedIndex() == 0;
	}
}