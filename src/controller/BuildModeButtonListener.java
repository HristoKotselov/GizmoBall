package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.AMovingGizmo;
import model.ActionTipDialogue;
import model.IMainEngine;
import model.IPhysicsConfig;
import physics.Angle;
import physics.Vect;
import view.IBuildMenu;
import view.IGameWindow;

public class BuildModeButtonListener implements ActionListener {
	private IMainEngine model;
	private IGameWindow gameWindow;
	private IBuildMenu buildMenu;

	/* All the pop-up dialogue boxes */
	private final JOptionPane resetDialogue;

	public BuildModeButtonListener(IMainEngine model, IGameWindow gameWindow, IBuildMenu bm) {
		this.model = model;
		this.gameWindow = gameWindow;
		this.buildMenu = bm;

		/* Set-up of the pop-up dialogue boxes */
		Object[] jOptionPane_Options = { "Yes", "No" }; // Default options for JOptionPane

		resetDialogue = new JOptionPane(
				"Are you sure you want to clear the board?",
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION,
				null, // uses default Icon for Yes_No_Option
				jOptionPane_Options,
				jOptionPane_Options[1]); // default selected option = "No"
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCmd = e.getActionCommand();

		switch (actionCmd) {
			/* Cardboard layout commands */
			case "setPhysics":
				try {
					IPhysicsConfig phys = model.getPhysicsConfig();
					phys.setFrictionCoef1(buildMenu.getFrictionCoef1FromGUI());
					phys.setFrictionCoef2(buildMenu.getFrictionCoef2FromGUI() / IMainEngine.L);
					phys.setGravity(buildMenu.getGravityFromGUI() * IMainEngine.L);

					// Op successful!
					gameWindow.setActionTipsTextArea(ActionTipDialogue.physicsActionTip());
				} catch (NumberFormatException ex) {}

				break;

			case "setBall":
				try {
					AMovingGizmo ball = model.getBall();

					double r = Math.toRadians(buildMenu.getBallDirectionFromGUI());
					double s = buildMenu.getBallSpeedFromGUI() * IMainEngine.L;

					Vect v = new Vect(new Angle(r), s);

					if (ball != null) {
						ball.setInitialVelo(v);
						model.reset();
					}

					// Op successful!
					gameWindow.setActionTipsTextArea(ActionTipDialogue.ballActionTip());
				} catch (NumberFormatException ex) {
					gameWindow.setActionTipsTextArea(ActionTipDialogue.ballActionTipError());
					gameWindow.setActionTipsTextAreaColour(Color.RED);
				}

				break;

			/* General commands */
			case "resetBoard":
				int response = JOptionPane.showOptionDialog(resetDialogue, resetDialogue.getMessage(),
						"Clear Board", // Option Dialogue's Title
						resetDialogue.getOptionType(), resetDialogue.getMessageType(), resetDialogue.getIcon(), resetDialogue.getOptions(), resetDialogue.getInitialValue());

				if (response == JOptionPane.OK_OPTION) {
					model.clearAllGizmos();
				}

				break;

			case "exit":
				System.exit(0);
				break;
		}

	}

}
