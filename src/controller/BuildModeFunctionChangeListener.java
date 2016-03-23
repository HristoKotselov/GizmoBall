package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import model.ActionTipDialogue;
import view.IBuildMenu;
import view.IGameWindow;

public class BuildModeFunctionChangeListener implements ItemListener {
	private IBuildMenu bm;
	/**
	 * Need to change ActionTips that involves selecting an dropdown command in Build Mode
	 **/
	private IGameWindow gw;

	public BuildModeFunctionChangeListener(IBuildMenu bm, IGameWindow gw) {
		this.bm = bm;
		this.gw = gw;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// e.getItem() retrieve the command name that will get swapped out, and
		// then again when a new command name is swapped into the JComboBox
		changeActionTips((String) e.getItem());

		bm.setCard();
	}

	private void changeActionTips(String s) {
		switch (s) {
			case "Add Gizmo":
				gw.setActionTipsTextArea(ActionTipDialogue.addGizmoActionTip());
				break;

			case "Remove Gizmo":
				gw.setActionTipsTextArea(ActionTipDialogue.deleteGizmoActionTip());
				break;

			case "Rotate Gizmo":
				gw.setActionTipsTextArea(ActionTipDialogue.rotateGizmoActionTip());
				break;

			case "Move Gizmo":
				gw.setActionTipsTextArea(ActionTipDialogue.moveGizmoActionTip());
				break;

			case "Add Ball":
				gw.setActionTipsTextArea(ActionTipDialogue.addBallActionTip());
				break;

			case "Connect Gizmos":
				gw.setActionTipsTextArea(ActionTipDialogue.connectGizmoActionTip());
				break;

			case "Bind Key":
				gw.setActionTipsTextArea(ActionTipDialogue.connectKeyToGizmoActionTip());
				break;

			case "Set Physics Constants":
				gw.setActionTipsTextArea(ActionTipDialogue.physicsActionTip());
				break;
		}
	}
}