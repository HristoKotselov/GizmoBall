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

	/* Model */
	private IMainEngine model;

	/* Controller */
	private PlayModeButtonListener playModeAL;
	// private StartGameListener startGameAL;
	// private StopGameListener stopGameAL;

	private JCheckBox dynamicEdit;

	public PlayMenu(IMainEngine model) {
		this.model = model;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		playModeAL = new PlayModeButtonListener(model);

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
		buttonMenu.add(dynamicEdit);

	}

	public JPanel getMenu() {
		return buttonMenu;
	}

	@Override
	public boolean isDynamicEditEnabled() {
		return dynamicEdit.isSelected();
	}
	
	public void pauseModel(){
		playModeAL.pauseModel();
	}

}
